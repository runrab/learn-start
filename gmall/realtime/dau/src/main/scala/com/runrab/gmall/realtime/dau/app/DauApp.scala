package com.runrab.gmall.realtime.dau.app

import java.util.Date
import cn.hutool.core.date.DateUtil
import com.alibaba.fastjson.JSON
import com.runrab.gmall.realtime.dau.entry.Dau
import com.runrab.gmall.realtime.dau.service.RedisService
import DauApp.MyJedis
import com.runrab.gmall.realtime.dau.util.{KafkaTools, PropertiesUtil, RedisTools}
import com.runrab.gmall.realtime.dau.util.PropertiesUtil
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.elasticsearch.spark.streaming.sparkDStreamFunctions
import org.springframework.beans.factory.annotation.Autowired
import redis.clients.jedis.Jedis
import org.springframework.stereotype.Component

import scala.collection.mutable.ListBuffer

//@component （把普通pojo实例化到spring容器中，相当于配置文件中的
//<bean id="" class=""/>）
@Component
class DauApp (@Autowired redisService: RedisService) extends Serializable {

  def run(): Unit = {
    //创建配置对象
    val conf = new SparkConf().setAppName("GMALL_REALTIME_DAU").setMaster("local[2]")
    conf.set("es.index.auto.create", "true")
    //创建StreamingContext对象
    val ssc = new StreamingContext(conf,Seconds(5))
    val dstream = KafkaTools.createDStream(ssc,PropertiesUtil.get("kafka.dau.topic"))
    val result = dstream.mapPartitions(records=>{
      val data = ListBuffer[Dau]()
      val jedis = RedisTools.getJedisClient
      records.foreach(record=> {
        val json = JSON.parseObject(record.value())
        val ts = json.getLong("ts")
        val common = json.getJSONObject("common")
        val mid = common.getString("mid")
        val date = new Date(ts)
        val dd = DateUtil.format(date, "yyyy-MM-dd")
        val dh = DateUtil.format(date, "yyyy-MM-dd:HH")
        jedis.sadd("dau:" + dh, mid, 24 * 60 * 60 * 2 + 60 * 60)
        jedis.sadd("dau:" + dd, mid, 24 * 60 * 60 * 2 + 60 * 60)
        //修正数据明细
                data.append(Dau(
                  mid,
                  common.getString("uid"),
                  common.getString("ar"),
                  common.getString("ch"),
                  common.getString("vc"),
                  dd,dh.split(":")(1), ts
                ))
              })
      data.iterator
    })
    //导入es依赖  org.elasticsearch.spark._
    //每小时的日活明细，
    // 存储至ElasticSearch，便于使用Kibana进行OLAP分析日活来源情况
    val es = Map[String,String]("es.nodes"->"localhost:9200")
    result.saveToEs("/dau_list_info/_doc",es)
    result.print()
    ssc.start()//启动
    ssc.awaitTermination()
  }

}
object DauApp{
  implicit class MyJedis(jedis: Jedis){
    def sadd(key:String,value:AnyRef,duration:Int)={
      if (jedis.exists(key)){
        jedis.sadd(key,value.toString)
      }else{
        jedis.sadd(key,value.toString)
        jedis.expire(key,duration)
      }
    }
  }
}
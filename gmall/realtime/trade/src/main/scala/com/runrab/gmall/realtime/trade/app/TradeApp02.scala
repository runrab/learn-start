package com.runrab.gmall.realtime.trade.app

import java.util.Date
import com.alibaba.fastjson.{JSON, JSONObject}
import com.runrab.gmall.realtime.trade.entry.Trade
import com.runrab.gmall.realtime.trade.util.{KafkaTools, PropertiesUtil, RedisTools}
import org.apache.kafka.clients.producer.ProducerRecord
import com.runrab.gmall.realtime.trade.util.RedisTools
import org.apache.log4j.{Level, Logger}
import org.elasticsearch.spark.streaming.sparkDStreamFunctions
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.clients.jedis.Jedis

import scala.collection.mutable.ListBuffer

object TradeApp02 {
  //创建生产者createProducer
  def run(): Unit = {
    //创建配置对象
    val conf = new SparkConf().setAppName("GMALL_REALTIME_TRADE").setMaster("local[*]")
    conf.set("es.index.auto.create", "true")
    //创建StreamingContext对象
    val ssc = new StreamingContext(conf,Seconds(5))
    val dstream = KafkaTools.createDStream(ssc,PropertiesUtil.get("kafka.trade_GMALL_ORDER_INFO.topic"))

    val result = dstream.map(records => {
      val json = JSON.parseObject(records.value())
      val datas = json.getObject("data",classOf[Array[JSONObject]])
      datas.foreach(data => {
        val datatrade = new JSONObject();
        datatrade.put("consignee",data.getString("consignee"))
        datatrade.put("consignee_tel",data.getString("consignee_tel"))
        datatrade.put("delivery_address",data.getString("delivery_address"))
        datatrade.put("trade_body",data.getString("trade_body"))
        datatrade.put("final_total_amount",data.getString("final_total_amount"))
        datatrade.put("create_time",data.getString("create_time"))

      })
      datas
    }).mapPartitions(record => {
      val datadetails = new ListBuffer[Trade]()
      val jedis = RedisTools.getJedisClient
      record.foreach(jsonObject => {
        jsonObject.foreach(json =>{
          val create_time = json.getString("create_time").split(" ")(0)
          val count = 1
          jedis.sadds("trade:" + create_time,json.getDouble("final_total_amount"),24 * 60 * 60 * 2 + 60 * 60)
          jedis.sadds("count:" + create_time,count,24 * 60 * 60 * 2 + 60 * 60)
          datadetails.append(Trade(
            json.getString("consignee"),
            json.getString("consignee_tel"),
            json.getString("delivery_address"),
            json.getString("trade_body"),
            json.getDouble("final_total_amount"), //total ammout
            json.getString("create_time") //time
          ))
        })
      })
      datadetails.iterator
    })
    val es = Map[String,String]("es.nodes"->"localhost:9200")
    result.saveToEs("/trade_list_info/_doc",es)
    result.print()
    //启动
    ssc.start()
    ssc.awaitTermination()
  }
  //Scala 隐式（implicit）
  implicit class MyJedis(jedis: Jedis){
    def sadds(key:String,value:Double,duration:Int):Unit={
      if (jedis.exists(key)){
        jedis.incrByFloat(key,value)
      }else{
        jedis.set(key,value.toString)
        jedis.expire(key,duration)
      }
    }
  }
}

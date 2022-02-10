package com.runrab.gmall.realtime.trade.app

import com.runrab.gmall.realtime.trade.util.KafkaTools
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable.ListBuffer
import com.alibaba.fastjson.JSON
import com.runrab.gmall.realtime.trade.entry.Trade
import com.runrab.gmall.realtime.trade.util.{KafkaTools, PropertiesUtil}
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.log4j.{Level, Logger}


object TradeApp01 {

  def run()= {
    //创建配置对象
    val conf = new SparkConf().setAppName("GMALL_REALTIME_TRADE").setMaster("local[2]")
    //创建StreamingContext对象
    val ssc = new StreamingContext(conf,Seconds(5))
    val dstream = KafkaTools.createDStream(ssc,PropertiesUtil.get("kafka.trade_GMALL_DB.topic"))
    val result = dstream.mapPartitions(records => {
      val datadetails = new ListBuffer[Trade]()
      records.foreach(record => {
        val jsonObject = JSON.parseObject(record.value())
        val table = jsonObject.getString("table")
        val operationtype = jsonObject.getString("type")
        val kafkaProducer = KafkaTools.createProducer
        if (table.equals("order_info") && operationtype.equals("INSERT")){
          kafkaProducer.send(new ProducerRecord[String,String]("GMALL_ORDER_INFO",jsonObject.toJSONString))
        }else{
          kafkaProducer.send(new ProducerRecord[String,String]("GMALL_OTHER",jsonObject.toJSONString))
        }
      })
      datadetails.iterator
    })
    //结果输出
    result.print
    //启动
    ssc.start()
    ssc.awaitTermination()
  }
}

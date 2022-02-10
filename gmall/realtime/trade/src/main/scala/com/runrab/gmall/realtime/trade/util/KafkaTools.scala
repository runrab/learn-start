package com.runrab.gmall.realtime.trade.util

import java.util.Properties


import org.apache.kafka.clients.admin.{AdminClient, AdminClientConfig}
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, KafkaConsumer}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.internals.Topic
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

/**
 * @author runrab
 * @date 2020/10/23 17:23
 */
object KafkaTools {
  // 创建Kafka消费者

  def createConsumer: KafkaConsumer[String, String] = {
    val props = new Properties

    props.put("bootstrap.servers", PropertiesUtil.get("kafka.bootstrap-servers"))
    //每个消费者分配独立的消费者组编号
    props.put("group.id", PropertiesUtil.get("kafka.trade.consumer.group-id"))
    //如果value合法，则自动提交偏移量
    props.put("enable.auto.commit", PropertiesUtil.get("kafka.trade.consumer.enable-auto-commit"))
    //自动重置offset
    props.put("auto.offset.reset", PropertiesUtil.get("kafka.trade.consumer.auto-offset-reset"))
    props.put("value.deserializer", PropertiesUtil.get("kafka.trade.consumer.value-deserializer"))
    props.put("key.deserializer", PropertiesUtil.get("kafka.trade.consumer.key-deserializer"))
    new KafkaConsumer[String, String](props)
  }

  /**
   *
   *  创建Kafka集群管理员对象
   *
   * @author runrab
   * @Date 2020年10月23日
   * @version 1.0.0
   * @Param servers
   */
  def createAdmin(servers: String): AdminClient = {
    val props = new Properties
    props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, servers)
    AdminClient.create(props)
  }

  /**
   *
   *  创建Kafka集群管理员对象
   *
   * @author runrab
   * @Date 2020年10月23日
   * @version 1.0.0
   */
  def createAdmin(): AdminClient = {
    createAdmin(PropertiesUtil.get("kafka.bootstrap-servers"))
  }

  /**
   *
   * 创建Kafka生产者
   *
   * @author runrab
   * @Date 2020年10月23日
   * @version 1.0.0
   * @return KafkaProducer
   */

  def createProducer: KafkaProducer[String, String] = {
    val props = new Properties
    //声明kafka的地址
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, PropertiesUtil.get("kafka.bootstrap-servers"))
    //0、1 和 all：0表示只要把消息发送出去就返回成功；1表示只要Leader收到消息就返回成功；all表示所有副本都写入数据成功才算成功
    props.put("acks", PropertiesUtil.get("kafka.trade.producer.acks"))
    //重试次数
    props.put("retries", PropertiesUtil.get("kafka.trade.producer.retries"))
    //props.put("retries", Integer.MAX_VALUE)
    //批处理的字节数
    props.put("batch.size", PropertiesUtil.get("kafka.trade.producer.batch-size"))
    //批处理的延迟时间，当批次数据未满之时等待的时间
    props.put(ProducerConfig.LINGER_MS_CONFIG, PropertiesUtil.get("kafka.trade.producer.linger-ms"))
    //用来约束KafkaProducer能够使用的内存缓冲的大小的，默认值32MB
    props.put("buffer.memory", PropertiesUtil.get("kafka.trade.producer.buffer-memory"))
    // properties.put("value.serializer",
    // "org.apache.kafka.common.serialization.ByteArraySerializer");
    // properties.put("key.serializer",
    props.put("value.serializer", PropertiesUtil.get("kafka.trade.producer.value-serializer"))
    props.put("key.serializer", PropertiesUtil.get("kafka.trade.producer.key-serializer"))
    new KafkaProducer[String, String](props)
  }

//  def sendMessage(topic: String, jsonMessages: String*): Unit = {
//    val producer = createProducer
//    for (jsonMessage <- jsonMessages) {
//      producer.send(new ProducerRecord[String, String](topic, jsonMessage))
//    }
//  }


  def createDStream(ssc:StreamingContext,topic:String): InputDStream[ConsumerRecord[String, String]] ={
    //获取数据的主题
    //val fromTopic = PropertiesUtil.get("kafka.trade.topic")
    //创建kafka连接参数
    val kafkaParams = Map[String,Object](
      //集群地址
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG->PropertiesUtil.get("kafka.bootstrap-servers"),
      //Key与VALUE的反序列化类型
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG->PropertiesUtil.get("kafka.trade.consumer.key-deserializer"),
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG->PropertiesUtil.get("kafka.trade.consumer.value-deserializer"),
      //创建消费者组
      ConsumerConfig.GROUP_ID_CONFIG->PropertiesUtil.get("kafka.trade.consumer.group-id"),
      //自动移动到最新的偏移量
      ConsumerConfig.AUTO_OFFSET_RESET_CONFIG->PropertiesUtil.get("kafka.trade.consumer.auto-offset-reset"),
      //启用自动提交，将会由Kafka来维护offset【默认为true】
      ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG->PropertiesUtil.get("kafka.trade.consumer.enable-auto-commit")
    )
    //获取DStream
    KafkaUtils.createDirectStream(
      ssc,//SparkStreaming操作对象
      LocationStrategies.PreferConsistent,//数据读取之后如何分布在各个分区上

      ConsumerStrategies.Subscribe[String,String](Array(topic),kafkaParams)
    )

  }
}

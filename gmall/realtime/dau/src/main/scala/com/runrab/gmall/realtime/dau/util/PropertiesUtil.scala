package com.runrab.gmall.realtime.dau.util

import java.util.Properties

//获取配置信息，默认加载类路径下的application.properties配置文件

object PropertiesUtil {
  val prop = new Properties
  prop.load(this.getClass.getClassLoader.getResourceAsStream("application.properties"))

  // 加载配置文件，获取配置信息

  def load(filepath:String): Properties = {
    val prop = new Properties
    prop.load(this.getClass.getClassLoader.getResourceAsStream(filepath))
    prop
  }

  // 根据key获取此配置的值

  def get(key:String): String ={
    prop.getProperty(key)
  }
  // 测试

  def main(args: Array[String]): Unit = {
    println(prop.getProperty("aaa"))
    println(prop.getProperty("a"))
    println(prop.getProperty("kafka.bootstrap-servers"))
    println(prop.getProperty("kafka.dau.topic"))
  }
}

// 获取配置信息，默认先加载yml properties文件覆盖
// 自定义加载类路径下的application.properties配置文件，得到的是Properties对象

class PropertiesUtil(val filepath:String){
  val prop = new Properties
  prop.load(this.getClass.getClassLoader.getResourceAsStream(filepath))
  prop
}
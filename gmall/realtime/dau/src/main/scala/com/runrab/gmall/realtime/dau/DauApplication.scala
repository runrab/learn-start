package com.runrab.gmall.realtime.dau

import com.runrab.gmall.realtime.dau.app.DauApp
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication

object DauApplication{
  def main(args: Array[String]): Unit = {
    val context = SpringApplication.run(classOf[DauApplication])
    val app = context.getBean(classOf[DauApp])
    val redis = context.getBean("redisTemplate")
    println(redis)
    app.run()
  }
}
@SpringBootApplication
class  DauApplication



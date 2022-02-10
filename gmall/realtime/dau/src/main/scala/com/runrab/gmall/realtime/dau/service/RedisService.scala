package com.runrab.gmall.realtime.dau.service

import java.time.Duration
import java.{io, lang, util}

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service


@Service
class RedisService(@Autowired redisTemplate: RedisTemplate[String,AnyRef]) extends Serializable {

  def exits(keyName:String): Boolean = redisTemplate.hasKey(keyName)

  def sadd(key:String,value:AnyRef): lang.Long = {
    redisTemplate.opsForSet().add(key,value)
  }

  def sadd(key:String,value:AnyRef,duration:Long): io.Serializable with Comparable[_ >: lang.Long with lang.Boolean <: io.Serializable with Comparable[_ >: lang.Long with lang.Boolean]] ={
    if(redisTemplate.hasKey(key)){
      redisTemplate.opsForSet().add(key,value)
    }else{
      redisTemplate.opsForSet().add(key,value)
      redisTemplate.expire(key,Duration.ofSeconds(duration))
    }

  }
  def expire(key:String,duration:Long): Boolean =redisTemplate.expire(key,Duration.ofSeconds(duration))
  def getExpire(key:String): Long= redisTemplate.getExpire(key)
  def sget(key:String): util.Set[AnyRef] =redisTemplate.opsForSet().members(key)

}

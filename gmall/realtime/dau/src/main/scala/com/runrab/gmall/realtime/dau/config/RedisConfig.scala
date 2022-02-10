package com.runrab.gmall.realtime.dau.config

import com.fasterxml.jackson.annotation.{JsonAutoDetect, PropertyAccessor}
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.{HashOperations, ListOperations, RedisTemplate, SetOperations, ValueOperations, ZSetOperations}
import org.springframework.data.redis.serializer.{Jackson2JsonRedisSerializer, StringRedisSerializer}


@Configuration
@EnableCaching
class RedisConfig {

  @Bean def redisTemplate(factory:RedisConnectionFactory): RedisTemplate[String, AnyRef] = {
    val redisTemplate = new RedisTemplate[String, AnyRef] with Serializable
    // 配置连接工厂
    redisTemplate.setConnectionFactory(factory)
    //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
    val jacksonSeial = new Jackson2JsonRedisSerializer(classOf[AnyRef])
    val om = new ObjectMapper
    // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
    // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
    om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL)
    jacksonSeial.setObjectMapper(om)

    // 值采用json序列化
    redisTemplate.setValueSerializer(jacksonSeial)
    //使用StringRedisSerializer来序列化和反序列化redis的key值
    redisTemplate.setKeySerializer(new StringRedisSerializer)

    // 设置hash key 和value序列化模式
    redisTemplate.setHashKeySerializer(new StringRedisSerializer)
    redisTemplate.setHashValueSerializer(jacksonSeial)
    redisTemplate.afterPropertiesSet()
    redisTemplate
  }

  //对hash类型的数据操作

  @Bean def hashOperations(redisTemplate: RedisTemplate[String,AnyRef]): HashOperations[String, String,AnyRef] = redisTemplate.opsForHash[String,AnyRef]

  //对redis字符串类型数据操作

  @Bean def valueOperations(redisTemplate: RedisTemplate[String,AnyRef]): ValueOperations[String,AnyRef] = redisTemplate.opsForValue

  // 对链表类型的数据操作

  @Bean def listOperations(redisTemplate: RedisTemplate[String,AnyRef]): ListOperations[String,AnyRef] = redisTemplate.opsForList

  //对无序集合类型的数据操作

  @Bean def setOperations(redisTemplate: RedisTemplate[String,AnyRef]): SetOperations[String,AnyRef] = redisTemplate.opsForSet

  //对有序集合类型的数据操作

  @Bean def zSetOperations(redisTemplate: RedisTemplate[String,AnyRef]): ZSetOperations[String,AnyRef] = redisTemplate.opsForZSet
}

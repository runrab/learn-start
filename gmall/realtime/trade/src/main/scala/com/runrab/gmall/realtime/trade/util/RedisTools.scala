package com.runrab.gmall.realtime.trade.util

import redis.clients.jedis.{Jedis, JedisPool, JedisPoolConfig}

/**
 * @author runrab
 * @date 2020/11/1 20:55
 */
object RedisTools {
  //创建Redis连接池
  var jedisPool : JedisPool = _

  def getJedisClient: Jedis = {
    if(jedisPool == null){
      val conf = new JedisPoolConfig
      conf.setMaxIdle(PropertiesUtil.get("trade.redis.jedis.pool.max-idle").toInt)
      conf.setMinIdle(PropertiesUtil.get("trade.redis.jedis.pool.min-idle").toInt)
      conf.setMaxWaitMillis(PropertiesUtil.get("trade.redis.jedis.pool.max-wait").toInt)
      conf.setMaxTotal(PropertiesUtil.get("trade.redis.jedis.pool.max-active").toInt)
      //忙碌时是否等待
      conf.setBlockWhenExhausted(true)
      //每次获得连接进行连接的测试
      conf.setTestOnBorrow(true)
      jedisPool = new JedisPool (
        conf
        ,PropertiesUtil.get("trade.redis.host")
        ,PropertiesUtil.get("trade.redis.port").toInt
      )
    }
    //获取Redis连接对象
    jedisPool.getResource
  }

}

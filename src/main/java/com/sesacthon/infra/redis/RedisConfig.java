package com.sesacthon.infra.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory("redis", 6379);
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    return redisTemplate;
  }

  @Bean
  public StringRedisTemplate stringRedisTemplate() {
    final StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
    stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
    stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
    stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
    return stringRedisTemplate;
  }
}
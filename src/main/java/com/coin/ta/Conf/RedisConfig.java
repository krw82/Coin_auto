package com.coin.ta.Conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate( // 논블락킹 방식으로 하기위해 ReactiveRedisTemplate 설정
            ReactiveRedisConnectionFactory connectionFactory) {
        RedisSerializationContext.RedisSerializationContextBuilder<String, Object> builder = RedisSerializationContext
                .newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String, Object> context = builder
                .value(new GenericJackson2JsonRedisSerializer())
                .build();

        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }
}
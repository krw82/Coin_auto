package com.coin.ta.Redis;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.ReactiveValueOperations;

import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

    public Mono<Object> getValue(String key) {
        ReactiveValueOperations<String, Object> ops = reactiveRedisTemplate.opsForValue();
        return ops.get(key);
    }

    public Mono<Boolean> setValue(String key, Object value) {
        ReactiveValueOperations<String, Object> ops = reactiveRedisTemplate.opsForValue();
        return ops.set(key, value);
    }

    public Mono<Long> deleteValue(String key) {
        return reactiveRedisTemplate.delete(key);
    }
}
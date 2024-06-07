package com.coin.ta.Redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void setValue(String key, Object value) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value);
    }

    public void setValueWithExpiry(String key, Object value, long timeout, TimeUnit unit) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value, timeout, unit);
    }

    public Object getValue(String key) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }

    public boolean deleteValue(String key) {
        return redisTemplate.delete(key);
    }
}

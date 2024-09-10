package com.ssafy.roscamback.util;

import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class RedisUtil {

    private RedisTemplate<String, String> redisTemplate;

    private static final TimeUnit TIME_UNIT_SET = TimeUnit.SECONDS;

    public String keyGenerate(String userId, String otherId) {

        StringBuilder sb = new StringBuilder();
        sb.append(userId).append("::").append(otherId);

        return sb.toString();
    }
    public Optional<String> findValueByKey(String key){
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    public void insert(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    public void insert(String key, String value, long time){
        redisTemplate.opsForValue().set(key, value, time, TIME_UNIT_SET);
    }

    public void flushAll(){
        Set<String> keys = redisTemplate.keys("*");
        if(keys!=null && !keys.isEmpty()){
            redisTemplate.delete(keys);
        }
    }

    public boolean delete(String key){
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }


}

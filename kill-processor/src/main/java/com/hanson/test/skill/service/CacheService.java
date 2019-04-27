package com.hanson.test.skill.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("cacheService")
public class CacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static String stockTemplate = "kill:%s:productNumber";


    public void setStock(String killId,int number){
        redisTemplate.opsForValue().set(String.format(stockTemplate, killId),number);
    }

    public Integer getStock(String killId){
        Object obj = redisTemplate.opsForValue().get(String.format(stockTemplate, killId));
        if(obj == null){
            return 0;
        }
        return (Integer)obj;
    }
}

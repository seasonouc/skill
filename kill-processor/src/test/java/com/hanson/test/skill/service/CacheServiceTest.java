package com.hanson.test.skill.service;

import com.hanson.test.skill.ProcessorApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProcessorApplication.class)
public class CacheServiceTest {

    @Resource
    private CacheService cacheService;

    @Test
    public void testStock(){
        cacheService.setStock("12312",123);
        int stock = cacheService.getStock("12312");
        Assert.assertEquals(123,stock);
    }
}
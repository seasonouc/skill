package com.hanson.test.skill.dao;

import com.hanson.test.skill.ProcessorApplication;
import com.hanson.test.skill.model.KillOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProcessorApplication.class)
public class KillProcessMapperTest {

    @Resource
    private KillProcessMapper mapper;

    @Resource
    private KillOrderMapper orderMapper;

    @Test
    public void testProcess(){
        mapper.decreaseStock(1L);
    }

    @Test
    public void testOrder(){
        orderMapper.selectByPrimaryKey(1L);
    }
}
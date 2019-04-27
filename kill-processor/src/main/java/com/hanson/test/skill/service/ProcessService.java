package com.hanson.test.skill.service;

import com.hanson.test.common.request.SKillBuyRequestVO;
import com.hanson.test.skill.dao.KillOrderMapper;
import com.hanson.test.skill.dao.KillProcessMapper;
import com.hanson.test.skill.model.KillOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("processService")
public class ProcessService {

    @Resource
    private KillProcessMapper processMapper;

    @Resource
    private KillOrderMapper orderMapper;

    @Resource
    private CacheService cacheService;

    public void process(SKillBuyRequestVO requestVO){

//        cacheService.setStock();
        String killIStr = requestVO.getKillId();
        int stock = cacheService.getStock(killIStr);
        if(stock <= 0){
            return;
        }
        Long killId = Long.parseLong(killIStr);
        int res = processMapper.decreaseStock(killId);
        if(res == 0) return;
        KillOrder killOrder = new KillOrder();
        killOrder.setNumber(1);
        killOrder.setOderDate(new Date());
        killOrder.setPayStatus(false);
        killOrder.setProductId(Long.parseLong(requestVO.getProductId()));
        killOrder.setUserId(requestVO.getUserId());
        orderMapper.insert(killOrder);
        stock = processMapper.getStock(killId);
        cacheService.setStock(killIStr,stock);
    }
}

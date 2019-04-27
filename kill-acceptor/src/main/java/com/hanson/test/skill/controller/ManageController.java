package com.hanson.test.skill.controller;


import com.hanson.test.common.request.SKillPrepareRequestVO;
import com.hanson.test.common.response.ResponseVO;
import com.hanson.test.skill.dao.KillProductMapper;
import com.hanson.test.skill.model.KillProduct;
import com.hanson.test.skill.service.CacheService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("/manage")
public class ManageController {

    @Resource
    private KillProductMapper mapper;

    @Resource
    private CacheService cacheService;

    @RequestMapping(value = "/killPrepare",method = RequestMethod.POST)
    public ResponseVO killPrepare(@RequestBody SKillPrepareRequestVO requestVO){
        ResponseVO responseVO = new ResponseVO();
        KillProduct killProduct = new KillProduct();
        killProduct.setRequestId(requestVO.getRequestId());
        killProduct.setProductId(requestVO.getProductId());
        killProduct.setProductName(requestVO.getProductName());
        killProduct.setProductNumber(requestVO.getProductNumber());
        killProduct.setProductPrice(BigDecimal.valueOf(requestVO.getProductPrice()));
        killProduct.setEndTime(requestVO.getStartTime());
        killProduct.setStartTime(requestVO.getStartTime());
        mapper.insert(killProduct);
        KillProduct ins = mapper.selectByPrimaryKey(killProduct.getRequestId());
        if(ins != null) {
            cacheService.setKillProduct(ins);
        }
        return responseVO;
    }
}

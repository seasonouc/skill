package com.hanson.test.skill.dao;

import com.hanson.test.skill.model.KillProduct;

public interface KillProductMapper {
    int deleteByPrimaryKey(String requestId);

    int insert(KillProduct record);

    int insertSelective(KillProduct record);

    KillProduct selectByPrimaryKey(String requestId);

    int updateByPrimaryKeySelective(KillProduct record);

    int updateByPrimaryKey(KillProduct record);
}
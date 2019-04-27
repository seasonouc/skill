package com.hanson.test.skill.dao;

import com.hanson.test.skill.model.KillOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KillOrderMapper {
    int deleteByPrimaryKey(Long oderId);

    int insert(KillOrder record);

    int insertSelective(KillOrder record);

    KillOrder selectByPrimaryKey(Long oderId);

    int updateByPrimaryKeySelective(KillOrder record);

    int updateByPrimaryKey(KillOrder record);
}
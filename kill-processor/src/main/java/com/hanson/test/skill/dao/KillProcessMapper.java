package com.hanson.test.skill.dao;


import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface KillProcessMapper {

    int decreaseStock(Long killId);

    int getStock(Long killId);
}

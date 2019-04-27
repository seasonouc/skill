package com.hanson.test.skill;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hanson.test.skill.dao")
public class AcceptorApplication {
    public static void main(String args[]){
        SpringApplication.run(AcceptorApplication.class,args);
    }
}

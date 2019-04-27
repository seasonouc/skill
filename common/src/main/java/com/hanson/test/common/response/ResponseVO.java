package com.hanson.test.common.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseVO {
    private String requestId;

    private int responseCode;

    private String message;
}

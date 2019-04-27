package com.hanson.test.common.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class SKillPrepareRequestVO {

    private String requestId;

    private String productName;

    private double productPrice;

    private int productNumber;

    private Date startTime;

    private Date endTime;

    private long productId;
}

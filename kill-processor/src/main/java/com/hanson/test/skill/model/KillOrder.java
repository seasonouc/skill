package com.hanson.test.skill.model;

import java.util.Date;

public class KillOrder {
    private Long oderId;

    private String userId;

    private Long productId;

    private Integer number;

    private Boolean payStatus;

    private Date oderDate;

    public Long getOderId() {
        return oderId;
    }

    public void setOderId(Long oderId) {
        this.oderId = oderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Boolean payStatus) {
        this.payStatus = payStatus;
    }

    public Date getOderDate() {
        return oderDate;
    }

    public void setOderDate(Date oderDate) {
        this.oderDate = oderDate;
    }
}
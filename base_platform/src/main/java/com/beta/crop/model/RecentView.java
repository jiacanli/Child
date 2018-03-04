package com.beta.crop.model;

import java.util.Date;

public class RecentView {
    private Long id;

    private String userId;

    private String productId;

    private Date viewTime;

    private Integer isCommunicated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public Date getViewTime() {
        return viewTime;
    }

    public void setViewTime(Date viewTime) {
        this.viewTime = viewTime;
    }

    public Integer getIsCommunicated() {
        return isCommunicated;
    }

    public void setIsCommunicated(Integer isCommunicated) {
        this.isCommunicated = isCommunicated;
    }
}
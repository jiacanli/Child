package com.beta.crop.model;

import java.util.Date;

public class Product {
    private Long id;

    private String productId;

    private String productName;

    private String l3Code;

    private Integer minUnit;

    private Integer singleFactor;

    private String creatPath;

    private String imgId;

    private Date creatTime;

    private Date modifyTime;

    private String description;

    private String userId;

    private Double productPrice;

    private int isDeliveryFree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getL3Code() {
        return l3Code;
    }

    public void setL3Code(String l3Code) {
        this.l3Code = l3Code == null ? null : l3Code.trim();
    }

    public Integer getMinUnit() {
        return minUnit;
    }

    public void setMinUnit(Integer minUnit) {
        this.minUnit = minUnit;
    }

    public Integer getSingleFactor() {
        return singleFactor;
    }

    public void setSingleFactor(Integer singleFactor) {
        this.singleFactor = singleFactor;
    }

    public String getCreatPath() {
        return creatPath;
    }

    public void setCreatPath(String creatPath) {
        this.creatPath = creatPath == null ? null : creatPath.trim();
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId == null ? null : imgId.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public int getIsDeliveryFree() {
        return isDeliveryFree;
    }

    public void setIsDeliveryFree(int isDeliveryFree) {
        this.isDeliveryFree = isDeliveryFree;
    }
}
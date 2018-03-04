package com.beta.crop.model;

public class FeatureDetail {
    private Long id;

    private String featureDetailName;

    private String featureCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeatureDetailName() {
        return featureDetailName;
    }

    public void setFeatureDetailName(String featureDetailName) {
        this.featureDetailName = featureDetailName == null ? null : featureDetailName.trim();
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode == null ? null : featureCode.trim();
    }
}
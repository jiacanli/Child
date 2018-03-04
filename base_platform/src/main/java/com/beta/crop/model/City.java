package com.beta.crop.model;

import java.util.List;

public class City {
    private Long id;

    private String cityCode;

    private String cityName;

    private String referenceProvinceCode;
    
    private List<Town> towns;

    public List<Town> getTowns() {
		return towns;
	}

	public void setTowns(List<Town> towns) {
		this.towns = towns;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getReferenceProvinceCode() {
        return referenceProvinceCode;
    }

    public void setReferenceProvinceCode(String referenceProvinceCode) {
        this.referenceProvinceCode = referenceProvinceCode == null ? null : referenceProvinceCode.trim();
    }
}
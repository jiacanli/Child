package com.beta.crop.model;

public class Company {
    private Long id;

    private String name;

    private String description;

    private String image;

    private String isPass;

    private Long industry;

    private Long scale;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass == null ? null : isPass.trim();
    }

    public Long getIndustry() {
        return industry;
    }

    public void setIndustry(Long industry) {
        this.industry = industry;
    }

    public Long getScale() {
        return scale;
    }

    public void setScale(Long scale) {
        this.scale = scale;
    }
}
package com.beta.crop.model;

public class ProductCategory {
    private String l3Code;

    private String l1Code;

    private String l1Name;

    private String l2Code;

    private String l2Name;

    private String l3Name;

    public String getL3Code() {
        return l3Code;
    }

    public void setL3Code(String l3Code) {
        this.l3Code = l3Code == null ? null : l3Code.trim();
    }

    public String getL1Code() {
        return l1Code;
    }

    public void setL1Code(String l1Code) {
        this.l1Code = l1Code == null ? null : l1Code.trim();
    }

    public String getL1Name() {
        return l1Name;
    }

    public void setL1Name(String l1Name) {
        this.l1Name = l1Name == null ? null : l1Name.trim();
    }

    public String getL2Code() {
        return l2Code;
    }

    public void setL2Code(String l2Code) {
        this.l2Code = l2Code == null ? null : l2Code.trim();
    }

    public String getL2Name() {
        return l2Name;
    }

    public void setL2Name(String l2Name) {
        this.l2Name = l2Name == null ? null : l2Name.trim();
    }

    public String getL3Name() {
        return l3Name;
    }

    public void setL3Name(String l3Name) {
        this.l3Name = l3Name == null ? null : l3Name.trim();
    }
}
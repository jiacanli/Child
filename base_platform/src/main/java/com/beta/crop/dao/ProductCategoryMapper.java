package com.beta.crop.dao;

import com.beta.crop.model.ProductCategory;

public interface ProductCategoryMapper {
    ProductCategory selectByPrimaryKey(String l3Code);
}
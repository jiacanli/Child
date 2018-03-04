package com.beta.crop.dao;

import com.beta.crop.model.ProductService;

public interface ProductServiceMapper {
    ProductService selectByPrimaryKey(Integer id);
}
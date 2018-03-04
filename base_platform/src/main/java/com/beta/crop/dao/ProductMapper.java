package com.beta.crop.dao;

import com.beta.crop.model.Product;

public interface ProductMapper {
    Product selectByPrimaryKey(Long id);
}
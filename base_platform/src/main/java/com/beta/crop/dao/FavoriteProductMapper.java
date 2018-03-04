package com.beta.crop.dao;

import com.beta.crop.model.FavoriteProduct;

public interface FavoriteProductMapper {
    FavoriteProduct selectByPrimaryKey(Long id);
}
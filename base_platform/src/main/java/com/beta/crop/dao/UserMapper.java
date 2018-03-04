package com.beta.crop.dao;

import com.beta.crop.model.User;

public interface UserMapper {
    User selectByPrimaryKey(Long id);
}
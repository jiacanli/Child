package com.beta.crop.dao;

import com.beta.crop.model.Company;

public interface CompanyMapper {
    Company selectByPrimaryKey(Long id);
}
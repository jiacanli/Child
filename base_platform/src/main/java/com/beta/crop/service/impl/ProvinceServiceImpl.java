package com.beta.crop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beta.crop.dao.ProvinceMapper;
import com.beta.crop.model.Province;
import com.beta.crop.service.ProvinceService;
@Service
public class ProvinceServiceImpl implements ProvinceService {
	@Autowired
	private ProvinceMapper mapper;
	public ProvinceServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<Province> selectAllProvince() {
		// TODO Auto-generated method stub
		return mapper.selectAllProvince();

	}

}

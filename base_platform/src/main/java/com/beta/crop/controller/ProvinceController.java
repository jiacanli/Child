package com.beta.crop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beta.crop.model.DataModel;
import com.beta.crop.model.Province;
import com.beta.crop.service.ProvinceService;
import com.beta.crop.util.ResultMapUtils;










@RestController
@RequestMapping("/province")
public class ProvinceController {
	@Autowired
	private ProvinceService service;
	@RequestMapping("/getPro")
	public DataModel<Object> testctrl() {
		List<Province> list = service.selectAllProvince();
		return ResultMapUtils.getResultMap(list);
		
	}
}

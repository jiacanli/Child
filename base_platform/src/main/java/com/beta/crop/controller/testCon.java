package com.beta.crop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beta.crop.model.Province;
import com.beta.crop.service.ProvinceService;










@RestController
@RequestMapping("/test")
public class testCon {
	@Autowired
	private ProvinceService service;
	@RequestMapping("/testctrl")
	public List<Province> testctrl() {
		return service.selectAllProvince();
		
	}
}

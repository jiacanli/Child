package com.beta.corp.casetest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

public class sjon {

	public sjon() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		String json;
		try {
			json = FileUtils.readFileToString(new File("D:\\work\\crop\\province.txt"));
			JSONObject jo = new JSONObject(json);
			Iterator<String> province_codes = jo.keys(); // 
			while(province_codes.hasNext()){
				String code = province_codes.next();
				String province_name = jo.getJSONObject(code).getString("name");
				JSONObject citys = jo.getJSONObject(code).getJSONObject("child");
				System.out.println("ss");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

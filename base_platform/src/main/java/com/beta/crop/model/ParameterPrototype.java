package com.beta.crop.model;

public class ParameterPrototype {
	
	private String token;
	private String service;
	private String code;
	private String data;
	private String version;
	private String mobile;
	private String userid;
	private String tenantid;
	private String deviceid;
	
	
	
	public ParameterPrototype(
			String token,
			String service,
			String code,
			String data,
			String version,
			String mobile,
			String userid,
			String tenantid,
			String deviceid) {
		
		this.token = token;
		this.service = service;
		this.code = code;
		this.data = data;
		this.version = version;
		this.mobile = mobile;
		this.userid = userid;
		this.tenantid = tenantid;
		this.deviceid = deviceid;	
		
	}
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTenantid() {
		return tenantid;
	}

	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}


	
	public ParameterPrototype() {
		// TODO Auto-generated constructor stub
	}

}

package com.beta.crop.redis.model;

public class RegisgerResponse  {

	public RegisgerResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public String type;
	public Long gtime;
	
	public RegisgerResponse(String type,Long time){
		this.type = type;
		this.gtime = time;
	}
	


	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public Long getGtime() {
		return gtime;
	}

	public void setGtime(Long gtime) {
		this.gtime = gtime;
	}



	
	

}

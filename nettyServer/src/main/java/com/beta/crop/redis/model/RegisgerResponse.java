package com.beta.crop.redis.model;

public class RegisgerResponse  {

	public RegisgerResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public RegisgerResponse(int msg_num,Long time){
		this.msg_num = msg_num;
		this.msg_time_pointer = time;
	}
	
	public int msg_num;
	public Long msg_time_pointer;
	public int getMsg_num() {
		return msg_num;
	}
	public void setMsg_num(int msg_num) {
		this.msg_num = msg_num;
	}
	public Long getMsg_time_pointer() {
		return msg_time_pointer;
	}
	public void setMsg_time_pointer(Long msg_time_pointer) {
		this.msg_time_pointer = msg_time_pointer;
	}
	
	

}

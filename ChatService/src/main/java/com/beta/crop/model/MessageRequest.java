package com.beta.crop.model;

public class MessageRequest {

	public MessageRequest() {
		// TODO Auto-generated constructor stub
	}
	
	private String userid;
	private String username;
	private String usericon;
	private String recieverid;
	private String data;
	private String guid;
	private int type;
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}

	
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsericon() {
		return usericon;
	}
	public void setUsericon(String usericon) {
		this.usericon = usericon;
	}
	public String getRecieverid() {
		return recieverid;
	}
	public void setRecieverid(String recieverid) {
		this.recieverid = recieverid;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}

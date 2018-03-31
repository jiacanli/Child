package com.beta.crop.redis.model;

import java.io.Serializable;

public class Message implements Serializable,Comparable<Message> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8073841548419451027L;



	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	private String user_icon;
	private String sender_id;
	private String sender_name;
	private String reciever_id;
	private String content;
	private String guid;
	private Long time_stamp;
	private int type;
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}

	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUser_icon() {
		return user_icon;
	}
	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
	public String getReciever_id() {
		return reciever_id;
	}
	public void setReciever_id(String reciever_id) {
		this.reciever_id = reciever_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(Long time_stamp) {
		this.time_stamp = time_stamp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public int compareTo(Message o) {
		// TODO Auto-generated method stub
		if(this.time_stamp<o.getTime_stamp())
			return -1;
		else
			return 1;
		
	}
	
	
	
}

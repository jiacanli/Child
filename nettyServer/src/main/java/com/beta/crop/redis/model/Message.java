package com.beta.crop.redis.model;

import java.io.Serializable;

public class Message implements Serializable,Comparable<Message> {

	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public String sender;
	public String reciever;
	public String content;
	public Long time_stamp;
	
	
	
	public String getSender() {
		return sender;
	}



	public void setSender(String sender) {
		this.sender = sender;
	}



	public String getReciever() {
		return reciever;
	}



	public void setReciever(String reciever) {
		this.reciever = reciever;
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



	public int compareTo(Message o) {
		// TODO Auto-generated method stub
		if(this.time_stamp<o.getTime_stamp())
			return -1;
		else
			return 1;
		
	}
	
	
	
}

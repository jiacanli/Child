package com.beta.crop.client;

import java.util.LinkedList;
import java.util.Queue;

public class id {

	public id() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main(String[] args) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("asodgnfndgidsn"+"1", );
		
		Queue<String> queue= new LinkedList<String>();
		queue.offer("1234556");
		queue.offer("2556666");
		
		System.out.println(queue.poll());
		
				
	}

}

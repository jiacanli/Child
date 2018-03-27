package com.beta.crop.server.casetest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.beta.crop.redis.model.Message;
import com.beta.crop.redis.model.testmodel;

import redis.clients.jedis.Jedis;

public class serialtest {

	public serialtest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("13.124.243.54",7379);
		jedis.auth("ljcc");
		
//		testmodel testmodel = new testmodel(5);
//		Message message = new Message();
//		message.setTime_stamp(11111L);
		List<Long> list = new ArrayList<Long>();
		list.add(12455L);
		jedis.set("1".getBytes(), serializeList(list));
		
	}
	
    public static List<?> unserializeList(byte[] bytes) {  
        if (bytes == null) {  
            return null;  
        }  
  
        List<Object> list = new ArrayList<Object>();  
        ByteArrayInputStream bais = null;  
        ObjectInputStream ois = null;  
        try {  
            // 反序列化  
            bais = new ByteArrayInputStream(bytes);  
            ois = new ObjectInputStream(bais);  
            while (bais.available() > 0) {  
                Object obj = (Object) ois.readObject();  
                if (obj == null) {  
                    break;  
                }  
                list.add(obj);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return list;  
    } 
	
	
	
    public static byte[] serializeList(List<?> list) {  
    	  
        if (list.isEmpty()) {  
            return null;  
        }  
        ObjectOutputStream oos = null;  
        ByteArrayOutputStream baos = null;  
        byte[] bytes = null;  
        try {  
            baos = new ByteArrayOutputStream();  
            oos = new ObjectOutputStream(baos);  
            for (Object obj : list) {  
                oos.writeObject(obj);  
            }  
            bytes = baos.toByteArray();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
        	try {
				oos.close();
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }  
        return bytes;  
    }  

}

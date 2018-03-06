package com.beta.crop.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;


public class KafkaProducerListener implements ProducerListener<String,String> {
	 protected final Logger LOG = LoggerFactory.getLogger(KafkaProducerListener.class);
	public KafkaProducerListener() {
		// TODO Auto-generated constructor stub
	}

	public boolean isInterestedInSuccess() {
		// TODO Auto-generated method stub
		return false;
	}

	public void onError(String arg0, Integer arg1, String arg2, String arg3, Exception arg4) {
		// TODO Auto-generated method stub
		
		LOG.debug("error", arg4);
		
		
	}

	public void onSuccess(String arg0, Integer arg1, String arg2, String arg3, RecordMetadata arg4) {
		// TODO Auto-generated method stub
		System.out.println("ssssss");
		LOG.debug("success", arg4);
		
	}

}

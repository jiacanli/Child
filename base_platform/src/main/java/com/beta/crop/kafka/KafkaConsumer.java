package com.beta.crop.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import com.beta.crop.service.ProvinceService;

public class KafkaConsumer implements MessageListener<String, String> {
	@Autowired
	private ProvinceService service;
	protected final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);
	public KafkaConsumer() {
		// TODO Auto-generated constructor stub
	}

	public void onMessage(ConsumerRecord<String, String> arg0) {
		// TODO Auto-generated method stub
		LOG.debug(arg0.key());
		LOG.debug(arg0.value());
	}

}

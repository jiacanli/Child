package com.beta.crop.service.impl;

import java.util.List;

import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.beta.crop.service.KafkaService;

@Service
public class KafkaServiceImpl implements KafkaService {
	
	@Autowired
	KafkaTemplate<String, String> kafka ;
	public KafkaServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public void sendMessage(String msg) {
		// TODO Auto-generated method stub
		List<PartitionInfo> part = kafka.partitionsFor("test");
		
		kafka.send("test", msg);
	}

}

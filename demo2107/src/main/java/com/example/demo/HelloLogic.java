package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloLogic {
	Logger logger = LogManager.getLogger(HelloLogic.class);
	
	public void hello() {
		logger.info("hello 호출 성공");
	}
}

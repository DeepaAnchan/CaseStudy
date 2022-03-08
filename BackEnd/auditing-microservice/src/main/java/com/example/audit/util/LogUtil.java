package com.example.audit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogUtil {

	private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);
	
	public void logInfo(String message) {
		logger.info(message);
	}
}

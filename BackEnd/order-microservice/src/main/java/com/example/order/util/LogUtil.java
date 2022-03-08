package com.example.order.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogUtil {

	private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);
	
	public void logInfo(String message) {
		logger.info(message);
	}
	
	public void logError(String message) {
		logger.error(message);
	}
}

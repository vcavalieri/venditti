package com.garage.utils;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4jManager extends Logger{
	
	static final Logger logger = LogManager.getLogger(Log4jManager.class.getName());
	
	protected Log4jManager(String name) {
		super(name);
	}
	
	public static void log(Level level, String message) {
		
		switch (level.toInt()) {
		case 5000:
			trace(message);
			break;
		case 10000:
			debug(message);
			break;
		case 20000:
			info(message);
			break;
		case 30000:
			warn(message);
			break;
		case 40000:
			error(message);
			break;
		case 40001:
			millis(message);
			break;
		case 50000:
			fatal(message);
			break;
		default:
			break;
		}
	}
	
	private static void trace(String message) {
		logger.log(Level.TRACE, message);
	}
	
	private static void debug(String message) {
		logger.log(Level.DEBUG, message);
	}
	
	private static void info(String message) {
		logger.log(Level.INFO, message);
	}
	
	private static void warn(String message) {
		logger.log(Level.WARN, message);
	}
	
	private static void error(String message) {
		logger.log(Level.ERROR, message);
	}
	
	private static void fatal(String message) {
		logger.log(Level.FATAL, message);
	}
	
	private static void millis(String message) {
		logger.log(Level.toLevel(400001), message);
	}
}

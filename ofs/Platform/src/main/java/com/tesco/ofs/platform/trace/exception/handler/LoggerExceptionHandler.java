package com.tesco.ofs.platform.trace.exception.handler;

import com.tesco.ofs.platform.trace.logger.OFSPlatformLogger;

public class LoggerExceptionHandler implements ILoggerExceptionHandler {
	
	private static final OFSPlatformLogger logger = OFSPlatformLogger.getLogger(LoggerExceptionHandler.class);
	private static final String LINE_SEPARATOR = "line.separator";
	
	public void logException(Class<?> clazz, Throwable t) {
		// TODO Auto-generated method stub
		String lineSeparator = System.getProperty(LINE_SEPARATOR);
		StringBuilder sb = (new StringBuilder("Exception occured in "))
				                          .append(clazz)
				                          .append(lineSeparator)
				                          .append(t.getMessage())
				                          .append(lineSeparator)
				                          .append(t.toString())
				                          .append(lineSeparator);
				                                                          
		
		logger.error(sb.toString(), t);		
		
	}
					
}

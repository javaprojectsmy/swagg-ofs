package com.tesco.ofs.platform.trace.exception.handler;

public interface ILoggerExceptionHandler {
	
	public void logException(Class<?> clazz,Throwable t);
	
}

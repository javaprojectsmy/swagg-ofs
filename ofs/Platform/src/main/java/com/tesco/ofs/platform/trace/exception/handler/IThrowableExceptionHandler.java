package com.tesco.ofs.platform.trace.exception.handler;
import java.util.Map;

import com.tesco.ofs.platform.trace.exception.OFSPlatformException;
import com.tesco.ofs.platform.trace.exception.TescoExceptionType;


public interface IThrowableExceptionHandler {
	
	public void throwCheckedException(String message, Throwable cause) throws OFSPlatformException;	
	public void throwRunTimeException(String message, Throwable cause);
	public void throwRunTimeException(String message, String errorDescription, TescoExceptionType exceptionType, String additionalInfo, Map params, Throwable cause);
	public void throwCheckedException(String message, String errorDescription, TescoExceptionType exceptionType, String additionalInfo, Map params, Throwable cause) throws OFSPlatformException;
}

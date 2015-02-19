package com.tesco.ofs.platform.trace.exception.handler;

import java.util.Map;

import com.tesco.ofs.platform.trace.exception.OFSPlatformException;
import com.tesco.ofs.platform.trace.exception.OFSPlatformRunTimeException;
import com.tesco.ofs.platform.trace.exception.TescoExceptionType;

public class ThrowableExceptionHandler implements IThrowableExceptionHandler {
		
	@Override
	public void throwRunTimeException(String message, Throwable cause) {					
		OFSPlatformRunTimeException ofsRunTimeEx = new OFSPlatformRunTimeException(message, cause);
		throw ofsRunTimeEx;
				
	}

	@Override
	public void throwCheckedException(String message, Throwable cause) throws OFSPlatformException {
		// TODO Auto-generated method stub
		
		OFSPlatformException ofsEx = new OFSPlatformException(message, cause);
		throw ofsEx;
	}
	
	@Override
	public void throwRunTimeException(String message, String errorDescription, TescoExceptionType exceptionType, String additionalInfo, Map params, Throwable cause) {					
		OFSPlatformRunTimeException ofsRunTimeEx = new OFSPlatformRunTimeException(message, cause);
		ofsRunTimeEx.setErrorDescription(errorDescription);
		ofsRunTimeEx.setErrorType(exceptionType);
		ofsRunTimeEx.setErrorCode(exceptionType);
		ofsRunTimeEx.setAdditionalInformation(additionalInfo);
		ofsRunTimeEx.setParams(params);
		
		throw ofsRunTimeEx;
				
	}

	
	@Override
	public void throwCheckedException(String message, String errorDescription, TescoExceptionType exceptionType, String additionalInfo, Map params, Throwable cause) throws OFSPlatformException {
		// TODO Auto-generated method stub
		
		OFSPlatformException ofsEx = new OFSPlatformException(message, cause);
		
		ofsEx.setErrorDescription(errorDescription);
		ofsEx.setErrorType(exceptionType);
		ofsEx.setErrorCode(exceptionType);
		ofsEx.setAdditionalInformation(additionalInfo);
		ofsEx.setParams(params);
		
		throw ofsEx;
	}
	
}

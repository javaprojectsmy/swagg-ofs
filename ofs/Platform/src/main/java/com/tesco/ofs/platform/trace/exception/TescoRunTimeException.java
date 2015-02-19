package com.tesco.ofs.platform.trace.exception;

import java.util.Map;

public class TescoRunTimeException extends RuntimeException {
	
	private static final long serialVersionUID = -1240848867584723187L;
	
	private String errorType;
	private String errorCode;
	private String errorDescription;
	private String additionalInformation;
	private Map<String, Object> params;
	
	public String getErrorType() {
		return errorType;
	}


	public void setErrorType(TescoExceptionType exceptionType) {
		this.errorType = exceptionType.getErrorType();
	}


	public void setErrorCode(TescoExceptionType exceptionType) {
		this.errorCode = exceptionType.getErrorType();
	}


	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}


	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}


	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	public TescoRunTimeException() {
        super();
    }

	
	public TescoRunTimeException(String message)
	{
		super(message);
	}
	
	public TescoRunTimeException(String message, Throwable cause)
	{
		super(message, cause);
	}
					
	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public String toString()
	{
		String msg = String.format("Error Message: %s\nErrorDescription: %s\nErrorType: %s\nErrorCode: %s\nAdditionalInformation: %s\nParams: %s\n" ,
				                    super.getMessage(), getErrorDescription(), getErrorType(), getErrorCode(), getAdditionalInformation(), getParams());
		
		return msg; 		
	}
	
	

}

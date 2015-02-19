package com.tesco.ofs.platform.trace.exception;

import java.util.Map;

public class OFSPlatformRunTimeException extends TescoRunTimeException {
	
	private static final long serialVersionUID = -5450577523440743900L;
	
	String jsonInput;
	String resource;
	String requestID;
		
	
	public OFSPlatformRunTimeException(String message)
	{
		super(message);
		
	}
	
	public OFSPlatformRunTimeException(String message, Throwable cause )
	{
		super(message,cause);
		
	}
	
	public String getJsonInput() {
		return jsonInput;
	}
	
	public void setJsonInput(String jsonInput) {
		this.jsonInput = jsonInput;
	}
	
	@Override
	public String toString()
	{
		String msg = String.format("%s\njson input: %s\n" ,
                super.toString(), getJsonInput());

		return msg; 
	}
	
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

}

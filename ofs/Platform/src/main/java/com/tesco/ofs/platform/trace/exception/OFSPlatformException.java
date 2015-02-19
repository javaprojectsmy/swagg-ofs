package com.tesco.ofs.platform.trace.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class OFSPlatformException extends TescoBaseException {

	private static final long serialVersionUID = -5834433609549738372L;
	
	@JsonProperty("JsonInput")
	private String jsonInput;
	
	@JsonProperty("Resource")
	private String resource;
	
	@JsonProperty("RequestID")
	private String requestID;
			
	public OFSPlatformException(String message)
	{
		super(message);		
	}
	
	public OFSPlatformException(String message, Throwable cause )
	{
		super(message, cause);		
	}
	
	@JsonProperty("JsonInput")
	public String getJsonInput() {
		return jsonInput;
	}
	
	@JsonProperty("JsonInput")
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
	
	@JsonProperty("Resource")
	public String getResource() {
		return resource;
	}

	@JsonProperty("Resource")
	public void setResource(String resource) {
		this.resource = resource;
	}

	@JsonProperty("RequestID")
	public String getRequestID() {
		return requestID;
	}

	@JsonProperty("RequestID")
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	
}

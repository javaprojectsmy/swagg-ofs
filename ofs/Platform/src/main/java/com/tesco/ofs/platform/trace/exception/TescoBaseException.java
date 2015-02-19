package com.tesco.ofs.platform.trace.exception;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"cause", "stackTrace", "localizedMessage","suppressed","message"})
@JsonInclude(Include.NON_NULL)
public class TescoBaseException extends Exception {
	
	private static final long serialVersionUID = 7310162454684913686L;
	
	@JsonProperty("ErrorType")
	private String errorType;
	
	@JsonProperty("ErrorCode")
	private String errorCode;
	
	@JsonProperty("ErrorDescription")
	private String errorDescription;
	
	@JsonProperty("AdditionalInformation")
	private String additionalInformation;
	
	@JsonProperty("Params")
	private Map<String, Object> params;
	
	public TescoBaseException(String message)
	{
		super(message);
	}
	
	public TescoBaseException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	@JsonProperty("ErrorType")
	public String getErrorType() {
		return errorType;
	}


	@JsonProperty("ErrorType")
	public void setErrorType(TescoExceptionType exceptionType) {
		this.errorType = exceptionType.getErrorType();
	}

	@JsonProperty("ErrorCode")
	public void setErrorCode(TescoExceptionType exceptionType) {
		this.errorCode = exceptionType.getErrorCode();
	}

	@JsonProperty("ErrorDescription")
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	@JsonProperty("AdditionalInformation")
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	@JsonProperty("Params")
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	public TescoBaseException() {
        super();
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

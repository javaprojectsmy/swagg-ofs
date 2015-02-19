package com.tesco.ofs.platform.notification;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorBean {
	
	@JsonProperty("Type")
	public String type;
	
	@JsonProperty("ErrorCode")
	public String errorCode;
	
	@JsonProperty("ErrorMessage")
	public String errorMessage;
	
	@JsonProperty("GUID")
	public String gUID;
	
	@JsonProperty("ChannelOrderID")
	public String channelOrderID;
	
	public String getgUID() {
		return gUID;
	}

	public void setGUID(String gUID) {
		this.gUID = gUID;
	}

	public String getChannelOrderID() {
		return channelOrderID;
	}

	public void setChannelOrderID(String channelOrderID) {
		this.channelOrderID = channelOrderID;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	
	@JsonProperty("BusinessUnit")
	public String businessUnit;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getTimeOfFailure() {
		return timeOfFailure;
	}

	public void setTimeOfFailure(String timeOfFailure) {
		this.timeOfFailure = timeOfFailure;
	}

	@JsonProperty("EnterpriseCode")
	public String enterpriseCode;
	
	@JsonProperty("TimeOfFailure")
	public String timeOfFailure;
	
}

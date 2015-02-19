package com.tesco.ofs.order.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Extn")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Extn {
		
	@XmlAttribute(name = "ExtnDeliveryOption")
	protected String extnDeliveryOption;
		
	@XmlAttribute(name = "ExtnFulfillerInfo")
	protected String extnFulfillerInfo;
	
	@XmlAttribute(name = "ExtnClubCardNumber")
	protected String extnClubCardNumber;
	
	@XmlAttribute(name = "ExtnIsResiliency")
	protected String extnIsResiliency;
	
	@XmlAttribute(name = "ExtnCustomerOrderID")
	protected String extnCustomerOrderID;
	
	@XmlAttribute(name = "ExtnPassedFraudFlag")
	protected String extnPassedFraudFlag;	
	
	public String getExtnCustomerOrderID() {
		return extnCustomerOrderID;
	}

	public void setExtnCustomerOrderID(String extnCustomerOrderID) {
		this.extnCustomerOrderID = extnCustomerOrderID;
	}

	public String getExtnPassedFraudFlag() {
		return extnPassedFraudFlag;
	}

	public void setExtnPassedFraudFlag(String extnPassedFraudFlag) {
		this.extnPassedFraudFlag = extnPassedFraudFlag;
	}
		
	public String getExtnClubCardNumber() {
		return extnClubCardNumber;
	}

	public void setExtnClubCardNumber(String extnClubCardNumber) {
		this.extnClubCardNumber = extnClubCardNumber;
	}

	

	public String getExtnFulfillerInfo() {
		return extnFulfillerInfo;
	}

	public void setExtnFulfillerInfo(String extnFulfillerInfo) {
		this.extnFulfillerInfo = extnFulfillerInfo;
	}

	public String getExtnIsResiliency() {
		return extnIsResiliency;
	}

	public void setExtnIsResiliency(String extnIsResiliency) {
		this.extnIsResiliency = extnIsResiliency;
	}

	public String getExtnDeliveryOption() {
		return extnDeliveryOption;
	}

	public void setExtnDeliveryOption(String extnDeliveryOption) {
		this.extnDeliveryOption = extnDeliveryOption;
	}

}

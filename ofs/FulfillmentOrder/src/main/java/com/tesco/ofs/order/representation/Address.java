package com.tesco.ofs.order.representation;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
		
	@JsonProperty("AddressLine1")
	private String addressLine1;
	
	@JsonProperty("AddressLine2")
    private String addressLine2;
	
	@JsonProperty("AddressLine3")
    private String addressLine3;
	
	@JsonProperty("AddressLine4")
    private String addressLine4;
	
	@JsonProperty("AddressLine5")
    private String addressLine5;
	
	@JsonProperty("AddressLine6")
    private String addressLine6;
			
	@JsonProperty("Region")
    private String region;
	
	@JsonProperty("City")
    private String city;
		
	@JsonProperty("Country")
	@NotNull(message="country should not be null")
    @NotEmpty(message="Country is empty")
    private String country;
	
	/*@JsonProperty("GridRef")
    private String gridRef;
	
	@JsonProperty("GeoCord")
    private String geoCord;*/
	
	@JsonProperty("BuildingType")
    private String buildingType;
	
	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public String getAddressLine6() {
		return addressLine6;
	}

	public void setAddressLine6(String addressLine6) {
		this.addressLine6 = addressLine6;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/*@JsonProperty("IntersectionOrLandmark")
    private String intersectionOrLandmark;
	*/
	@JsonProperty("PostalCode")
    private String postalCode;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressLine4() {
		return addressLine4;
	}

	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}

	public String getAddressLine5() {
		return addressLine5;
	}

	public void setAddressLine5(String addressLine5) {
		this.addressLine5 = addressLine5;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/*public String getGridRef() {
		return gridRef;
	}

	public void setGridRef(String gridRef) {
		this.gridRef = gridRef;
	}

	public String getGeoCord() {
		return geoCord;
	}

	public void setGeoCord(String geoCord) {
		this.geoCord = geoCord;
	}

	public String getIntersectionOrLandmark() {
		return intersectionOrLandmark;
	}

	public void setIntersectionOrLandmark(String intersectionOrLandmark) {
		this.intersectionOrLandmark = intersectionOrLandmark;
	}*/

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}

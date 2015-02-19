package com.tesco.ofs.order.representation;

import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {

	@JsonProperty("FirstName")
	private String firstName;

	@JsonProperty("LastName")
	@NotEmpty(message = "Last Name cannot be empty")
	private String lastName;

	@JsonProperty("MiddleName")
	private String middleName;

	@JsonProperty("DayPhone")
	private String dayPhone;

	@JsonProperty("AlternatePhone")
	private String otherPhone;

	@JsonProperty("Mobile")
	private String mobilePhone;

	public String getFirstName() {		
		return firstName;
	}

	/**
	 * Sets the value of the firstName property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setFirstName(String value) {
		this.firstName = value;
	}

	/**
	 * Gets the value of the isCommercialAddress property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */

	/**
	 * Gets the value of the lastName property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the value of the lastName property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setLastName(String value) {
		this.lastName = value;
	}

	/**
	 * Gets the value of the middleName property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets the value of the middleName property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setMiddleName(String value) {
		this.middleName = value;
	}

	/**
	 * Gets the value of the mobilePhone property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * Sets the value of the mobilePhone property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setMobilePhone(String value) {
		this.mobilePhone = value;
	}

	/**
	 * Gets the value of the otherPhone property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getOtherPhone() {
		return otherPhone;
	}

	/**
	 * Sets the value of the otherPhone property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setOtherPhone(String value) {
		this.otherPhone = value;
	}
	/**
	 * Gets the value of the dayPhone property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getDayPhone() {
		return dayPhone;
	}

	/**
	 * Sets the value of the dayPhone property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setDayPhone(String value) {
		this.dayPhone = value;
	}
}


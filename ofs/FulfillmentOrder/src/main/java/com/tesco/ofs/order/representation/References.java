package com.tesco.ofs.order.representation;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Reference" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="Name" use="required" type="{http://www.sterlingcommerce.com/documentation/types}Text-40" />
 *                 &lt;attribute name="Value" type="{http://www.sterlingcommerce.com/documentation/types}Text-254" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"reference"
})
public  class References {

	@XmlElement(name = "Reference")
	protected List<References.Reference> reference;

	/**
	 * Gets the value of the reference property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the reference property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getReference().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link References.Reference }
	 * 
	 * 
	 */
	public List<References.Reference> getReference() {
		if (reference == null) {
			reference = new ArrayList<References.Reference>();
		}
		return this.reference;
	}


	/**
	 * <p>Java class for anonymous complex type.
	 * 
	 * <p>The following schema fragment specifies the expected content contained within this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;attribute name="Name" use="required" type="{http://www.sterlingcommerce.com/documentation/types}Text-40" />
	 *       &lt;attribute name="Value" type="{http://www.sterlingcommerce.com/documentation/types}Text-254" />
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "")
	public static class Reference {

		@XmlAttribute(name = "Name", required = true)
		@JsonProperty("Name")
		protected String name;
		@XmlAttribute(name = "Value")
		@JsonProperty("Value")
		protected String value;

		/**
		 * Gets the value of the name property.
		 * 
		 * @return
		 *     possible object is
		 *     {@link String }
		 *     
		 */
		public String getName() {
			return name;
		}

		/**
		 * Sets the value of the name property.
		 * 
		 * @param value
		 *     allowed object is
		 *     {@link String }
		 *     
		 */
		public void setName(String value) {
			this.name = value;
		}

		/**
		 * Gets the value of the value property.
		 * 
		 * @return
		 *     possible object is
		 *     {@link String }
		 *     
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Sets the value of the value property.
		 * 
		 * @param value
		 *     allowed object is
		 *     {@link String }
		 *     
		 */
		public void setValue(String value) {
			this.value = value;
		}

	}

}
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.25 at 03:34:08 AM GMT 
//


package com.tesco.ofs.order.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{http://www.sterlingcommerce.com/documentation/YFS/createOrder/input}OrderTag" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "OrderTags")
public class OrderTags {

    @XmlElement(name = "OrderTag")
    protected OrderTag orderTag;

    /**
     * Gets the value of the orderTag property.
     * 
     * @return
     *     possible object is
     *     {@link OrderTag }
     *     
     */
    public OrderTag getOrderTag() {
        return orderTag;
    }

    /**
     * Sets the value of the orderTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderTag }
     *     
     */
    public void setOrderTag(OrderTag value) {
        this.orderTag = value;
    }

}
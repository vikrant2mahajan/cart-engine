//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.07.20 at 10:22:20 AM IST 
//


package com.mmt.car.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.mmt.services.product.IResponse;


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
 *         &lt;element name="ResultsList" type="{http://carrental.makemytrip.com/webservices}ResultsList"/>
 *         &lt;element name="Status" type="{http://carrental.makemytrip.com/webservices}Status"/>
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
    "resultsList",
    "status"
})
@XmlRootElement(name = "GetOutstationCarListResponse")
public class GetOutstationCarListResponse implements IResponse{

    @XmlElement(name = "ResultsList", required = true)
    protected ResultsList resultsList;
    @XmlElement(name = "Status", required = true)
    protected Status status;

    /**
     * Gets the value of the resultsList property.
     * 
     * @return
     *     possible object is
     *     {@link ResultsList }
     *     
     */
    public ResultsList getResultsList() {
        return resultsList;
    }

    /**
     * Sets the value of the resultsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultsList }
     *     
     */
    public void setResultsList(ResultsList value) {
        this.resultsList = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
    }

}

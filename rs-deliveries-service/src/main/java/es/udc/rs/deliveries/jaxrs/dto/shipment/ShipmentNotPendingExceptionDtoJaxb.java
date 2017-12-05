package es.udc.rs.deliveries.jaxrs.dto.shipment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ShipmentNotPendingException")
@XmlType(name = "shipmentNotPendingExceptionType")
public class ShipmentNotPendingExceptionDtoJaxb {

	@XmlElement(required = true)
	private String message;

	public ShipmentNotPendingExceptionDtoJaxb() {
	}

	public ShipmentNotPendingExceptionDtoJaxb(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
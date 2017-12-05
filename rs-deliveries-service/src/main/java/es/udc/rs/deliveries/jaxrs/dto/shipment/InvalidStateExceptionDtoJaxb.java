package es.udc.rs.deliveries.jaxrs.dto.shipment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "InvalidStateException")
@XmlType(name = "invalidStateExceptionType")
public class InvalidStateExceptionDtoJaxb {

	@XmlElement(required = true)
	private String message;

	public InvalidStateExceptionDtoJaxb() {
	}

	public InvalidStateExceptionDtoJaxb(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
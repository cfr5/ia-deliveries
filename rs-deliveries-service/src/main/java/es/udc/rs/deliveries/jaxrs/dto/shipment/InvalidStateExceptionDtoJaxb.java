package es.udc.rs.deliveries.jaxrs.dto.shipment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "InvalidStateException")
@XmlType(name = "invalidStateExceptionType", propOrder = { "currentState", "newState" })
public class InvalidStateExceptionDtoJaxb {

	@XmlElement(required = true)
	private String currentState;
	@XmlElement(required = true)
	private String newState;

	public InvalidStateExceptionDtoJaxb() {
	}

	public InvalidStateExceptionDtoJaxb(String currentState, String newState) {
		this.currentState = currentState;
		this.newState = newState;
	}

	public String getCurrentState() {
		return currentState;
	}

	public String getNewState() {
		return newState;
	}

}
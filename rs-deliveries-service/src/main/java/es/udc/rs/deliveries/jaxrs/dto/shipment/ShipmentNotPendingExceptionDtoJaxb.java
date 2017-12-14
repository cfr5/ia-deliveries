package es.udc.rs.deliveries.jaxrs.dto.shipment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ShipmentNotPendingException")
@XmlType(name = "shipmentNotPendingExceptionType")
public class ShipmentNotPendingExceptionDtoJaxb {

	@XmlElement(required = true)
	private Long shipmentId;

	public ShipmentNotPendingExceptionDtoJaxb() {
	}

	public ShipmentNotPendingExceptionDtoJaxb(Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	public Long getMessage() {
		return shipmentId;
	}

	public void setMessage(Long shipmentId) {
		this.shipmentId = shipmentId;
	}

}
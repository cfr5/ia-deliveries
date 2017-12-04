package es.udc.rs.deliveries.jaxrs.dto.shipment;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "shipmentState")
@XmlEnum
public enum ShipmentStateDtoJaxb {
	PENDING, SENT, DELIVERED, REJECTED, CANCELLED
};

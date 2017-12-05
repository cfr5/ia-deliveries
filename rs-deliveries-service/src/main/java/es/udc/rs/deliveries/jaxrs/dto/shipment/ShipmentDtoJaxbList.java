package es.udc.rs.deliveries.jaxrs.dto.shipment;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "shipments")
@XmlType(name = "shipmentListType")
public class ShipmentDtoJaxbList {

	@XmlElement(name = "shipment")
	private List<ShipmentDtoJaxb> shipments = null;

	public ShipmentDtoJaxbList() {
		this.shipments = new ArrayList<ShipmentDtoJaxb>();
	}

	public ShipmentDtoJaxbList(List<ShipmentDtoJaxb> shipments) {
		this.shipments = shipments;
	}

	public List<ShipmentDtoJaxb> getShipments() {
		return shipments;
	}

	public void setShipments(List<ShipmentDtoJaxb> shipments) {
		this.shipments = shipments;
	}

}
package es.udc.rs.deliveries.jaxrs.dto.shipment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import es.udc.rs.deliveries.model.shipment.ShipmentState;

@XmlRootElement(name = "shipmentState")
@XmlType(name = "shipmentStateType", propOrder = { "state" })
public class ShipmentStateDtoJaxb {

	@XmlElement(required = true)
	private ShipmentState state;

	public ShipmentStateDtoJaxb() {
	}

	public ShipmentStateDtoJaxb(ShipmentState state) {
		this.state = state;
	}

	public ShipmentState getShipmentState() {
		return state;
	}

	public void setShipmentState(ShipmentState state) {
		this.state = state;
	}

}
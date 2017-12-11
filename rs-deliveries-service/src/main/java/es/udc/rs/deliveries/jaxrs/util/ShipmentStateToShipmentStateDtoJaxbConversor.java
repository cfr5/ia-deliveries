package es.udc.rs.deliveries.jaxrs.util;

import es.udc.rs.deliveries.jaxrs.dto.shipment.ShipmentStateDtoJaxb;
import es.udc.rs.deliveries.model.shipment.ShipmentState;

public class ShipmentStateToShipmentStateDtoJaxbConversor {

	public static ShipmentState toShipmentState(ShipmentStateDtoJaxb shipmentStateDto) {
		return shipmentStateDto.getShipmentState();
	}

	public static ShipmentStateDtoJaxb toShipmentStateDtoJaxb(ShipmentState state) {
		return new ShipmentStateDtoJaxb(state);
	}

}

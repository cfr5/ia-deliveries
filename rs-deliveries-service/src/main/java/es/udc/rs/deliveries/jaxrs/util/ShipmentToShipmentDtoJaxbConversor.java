package es.udc.rs.deliveries.jaxrs.util;

import java.util.ArrayList;
import java.util.List;

import es.udc.rs.deliveries.jaxrs.dto.shipment.DateDtoJaxb;
import es.udc.rs.deliveries.jaxrs.dto.shipment.ShipmentDtoJaxb;
import es.udc.rs.deliveries.jaxrs.dto.shipment.ShipmentDtoJaxbList;
import es.udc.rs.deliveries.jaxrs.dto.shipment.ShipmentStateDtoJaxb;
import es.udc.rs.deliveries.model.shipment.Shipment;

public class ShipmentToShipmentDtoJaxbConversor {

	public static ShipmentDtoJaxb toShipmentDtoJaxb(Shipment shipment) {

		DateDtoJaxb creationDate = CalendarToCalendarDtoJaxbConversor.toDateDtoJaxb(shipment.getCreationDate());
		DateDtoJaxb maxDeliveryDate = CalendarToCalendarDtoJaxbConversor.toDateDtoJaxb(shipment.getMaxDeliveryDate());
		ShipmentStateDtoJaxb shipmentState = ShipmentStateToShipmentStateDtoJaxbConversor
				.toShipmentStateDtoJaxb(shipment.getState());

		return new ShipmentDtoJaxb(shipment.getShipmentId(), shipment.getCustomerId(), shipment.getPackageReference(),
				shipment.getAddress(), shipmentState, creationDate, maxDeliveryDate);
	}

	public static ShipmentDtoJaxbList toShipmentDtoJaxbList(List<Shipment> shipments) {

		List<ShipmentDtoJaxb> shipmentDtoList = new ArrayList<>();
		
		for (Shipment shipment : shipments) {
			shipmentDtoList.add(toShipmentDtoJaxb(shipment));
		}

		return  new ShipmentDtoJaxbList(shipmentDtoList);
	}

}

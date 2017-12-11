package es.udc.rs.deliveries.jaxrs.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.udc.rs.deliveries.jaxrs.dto.shipment.DateDtoJaxb;
import es.udc.rs.deliveries.jaxrs.dto.shipment.ShipmentDtoJaxb;
import es.udc.rs.deliveries.jaxrs.dto.shipment.ShipmentDtoJaxbList;
import es.udc.rs.deliveries.model.shipment.Shipment;

public class ShipmentToShipmentDtoJaxbConversor {

	private static Long hoursToDelivery(Calendar maxDeliveryDate) {
		long dateInMilis = maxDeliveryDate.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
		return dateInMilis / (60 * 60 * 1000);
	}

	public static ShipmentDtoJaxb toShipmentDtoJaxb(Shipment shipment) {

		DateDtoJaxb creationDate = CalendarToCalendarDtoJaxbConversor.toDateDtoJaxb(shipment.getCreationDate());
		DateDtoJaxb deliveryDate = CalendarToCalendarDtoJaxbConversor.toDateDtoJaxb(shipment.getDeliveryDate());

		return new ShipmentDtoJaxb(shipment.getShipmentId(), shipment.getCustomerId(), shipment.getPackageReference(),
				shipment.getAddress(), shipment.getState(), creationDate, deliveryDate,
				hoursToDelivery(shipment.getMaxDeliveryDate()));
	}

	public static ShipmentDtoJaxbList toShipmentDtoJaxbList(List<Shipment> shipments) {

		List<ShipmentDtoJaxb> shipmentDtoList = new ArrayList<>();

		for (Shipment shipment : shipments) {
			shipmentDtoList.add(toShipmentDtoJaxb(shipment));
		}

		return new ShipmentDtoJaxbList(shipmentDtoList);
	}

}

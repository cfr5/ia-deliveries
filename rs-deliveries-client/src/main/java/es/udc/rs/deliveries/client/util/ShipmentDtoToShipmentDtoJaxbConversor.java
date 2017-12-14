package es.udc.rs.deliveries.client.util;

import java.util.ArrayList;
import java.util.List;

import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ShipmentDtoJaxb;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ShipmentDtoJaxbList;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ShipmentStateClientDto;

public class ShipmentDtoToShipmentDtoJaxbConversor {

	public static List<ClientShipmentDto> toShipmentDtos(ShipmentDtoJaxbList shipments) {
		List<ClientShipmentDto> cShipments = new ArrayList<>();

		for (ShipmentDtoJaxb shipmentDto : shipments.getShipments()) {
			cShipments.add(toShipmentDto(shipmentDto));
		}

		return cShipments;
	}

	private static ClientShipmentDto toShipmentDto(ShipmentDtoJaxb shipmentDto) {
		return new ClientShipmentDto(shipmentDto.getShipmentId(), shipmentDto.getCustomerId(),
				shipmentDto.getPackageReference(), shipmentDto.getAddress(),
				ShipmentStateClientDto.valueOf(shipmentDto.getState().toString()), XMLGregorianCalendarConversor.toCalendar(shipmentDto.getCreationDate()),
				XMLGregorianCalendarConversor.toCalendar(shipmentDto.getDeliveryDate()), shipmentDto.getHoursToDelivery());
	}

}

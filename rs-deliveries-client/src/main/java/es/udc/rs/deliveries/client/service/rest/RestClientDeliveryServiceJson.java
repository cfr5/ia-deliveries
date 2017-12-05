package es.udc.rs.deliveries.client.service.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;

import es.udc.rs.deliveries.client.service.rest.dto.customer.ClientCustomerDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentStateDto;

public class RestClientDeliveryServiceJson extends RestClientDeliveryService {

	@Override
	protected MediaType getMediaType() {
		return MediaType.APPLICATION_JSON_TYPE;
	}

}

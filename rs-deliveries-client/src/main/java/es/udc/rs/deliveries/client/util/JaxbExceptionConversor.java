package es.udc.rs.deliveries.client.util;

import es.udc.rs.deliveries.client.service.rest.dto.customer.CustomerWithShipmentsExceptionDtoJaxb;
import es.udc.rs.deliveries.client.service.rest.dto.customer.InputValidationExceptionDtoJaxb;
import es.udc.rs.deliveries.client.service.rest.dto.customer.InstanceNotFoundExceptionDtoJaxb;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.InvalidStateExceptionDtoJaxb;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ShipmentNotPendingExceptionDtoJaxb;
import es.udc.rs.deliveries.exceptions.CustomerWithShipmentsException;
import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
import es.udc.rs.deliveries.exceptions.InvalidStateException;
import es.udc.rs.deliveries.exceptions.ShipmentNotPendingException;

public class JaxbExceptionConversor {

	public static InstanceNotFoundException toInstanceNotFoundException(InstanceNotFoundExceptionDtoJaxb exDto) {
		return new InstanceNotFoundException(exDto.getInstanceId(), exDto.getInstanceType());
	}

	public static InputValidationException toInputValidationException(InputValidationExceptionDtoJaxb exDto) {
		return new InputValidationException(exDto.getMessage());
	}

	public static ShipmentNotPendingException toShipmentnotPendingException(ShipmentNotPendingExceptionDtoJaxb exDto) {
		return new ShipmentNotPendingException(exDto.getShipmentId());
	}

	public static InvalidStateException toInvalidStateException(InvalidStateExceptionDtoJaxb exDto) {
		return new InvalidStateException(exDto.getCurrentState(), exDto.getNewState());
	}

	public static CustomerWithShipmentsException toCustomerWithShipmentsException(
			CustomerWithShipmentsExceptionDtoJaxb exDto) {
		return new CustomerWithShipmentsException(exDto.getCustomerId());
	}

}

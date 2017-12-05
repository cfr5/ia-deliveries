package es.udc.rs.deliveries.jaxrs.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.udc.rs.deliveries.exceptions.ShipmentNotPendingException;
import es.udc.rs.deliveries.jaxrs.dto.shipment.ShipmentNotPendingExceptionDtoJaxb;

@Provider
public class ShipmentNotPendingExceptionMapper implements ExceptionMapper<ShipmentNotPendingException> {

	@Override
	public Response toResponse(ShipmentNotPendingException ex) {
		return Response.status(Response.Status.BAD_REQUEST).entity(new ShipmentNotPendingExceptionDtoJaxb()).build();

	}

}
package es.udc.rs.deliveries.jaxrs.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.udc.rs.deliveries.exceptions.InvalidStateException;
import es.udc.rs.deliveries.jaxrs.dto.shipment.InvalidStateExceptionDtoJaxb;

@Provider
public class InvalidStateExceptionMapper implements ExceptionMapper<InvalidStateException> {

	@Override
	public Response toResponse(InvalidStateException ex) {
		return Response.status(Response.Status.BAD_REQUEST).entity(new InvalidStateExceptionDtoJaxb()).build();

	}

}
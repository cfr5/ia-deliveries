package es.udc.rs.deliveries.jaxrs.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
import es.udc.rs.deliveries.jaxrs.dto.customer.InstanceNotFoundExceptionDtoJaxb;

@Provider
public class InstanceNotFoundExceptionMapper implements ExceptionMapper<InstanceNotFoundException> {

	@Override
	public Response toResponse(InstanceNotFoundException ex) {
		return Response.status(Response.Status.NOT_FOUND)
				.entity(new InstanceNotFoundExceptionDtoJaxb(ex.getInstanceId(), ex.getInstanceType())).build();

	}

}
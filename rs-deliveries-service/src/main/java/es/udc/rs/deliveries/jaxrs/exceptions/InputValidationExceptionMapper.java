package es.udc.rs.deliveries.jaxrs.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.jaxrs.dto.customer.InputValidationExceptionDtoJaxb;

@Provider
public class InputValidationExceptionMapper implements ExceptionMapper<InputValidationException> {

	@Override
	public Response toResponse(InputValidationException ex) {
		return Response.status(Response.Status.BAD_REQUEST).entity(new InputValidationExceptionDtoJaxb(ex.getMessage()))
				.build();
	}

}

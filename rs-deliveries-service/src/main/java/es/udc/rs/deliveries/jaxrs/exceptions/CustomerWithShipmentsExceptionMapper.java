package es.udc.rs.deliveries.jaxrs.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.udc.rs.deliveries.exceptions.CustomerWithShipmentsException;
import es.udc.rs.deliveries.jaxrs.dto.customer.CustomerWithShipmentsExceptionDtoJaxb;

@Provider
public class CustomerWithShipmentsExceptionMapper implements ExceptionMapper<CustomerWithShipmentsException> {

	@Override
	public Response toResponse(CustomerWithShipmentsException ex) {
		return Response.status(Response.Status.CONFLICT).entity(new CustomerWithShipmentsExceptionDtoJaxb(ex.getCustomerId())).build();

	}

}
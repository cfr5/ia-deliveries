package es.udc.rs.deliveries.jaxrs.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import es.udc.rs.deliveries.exceptions.CustomerWithShipmentsException;
import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
import es.udc.rs.deliveries.jaxrs.dto.customer.CustomerDtoJaxb;
import es.udc.rs.deliveries.jaxrs.dto.customer.CustomerDtoJaxbList;
import es.udc.rs.deliveries.jaxrs.util.CustomerToCustomerDtoJaxbConversor;
import es.udc.rs.deliveries.model.customer.Customer;
import es.udc.rs.deliveries.model.deliveryservice.DeliveryServiceFactory;

@Path("/customers")
public class CustomerResource {

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addCustomer(@QueryParam("name") String name, @QueryParam("cif") String cif,
			@QueryParam("address") String address, @Context UriInfo ui)
			throws InstanceNotFoundException, InputValidationException {

		Customer customer = DeliveryServiceFactory.getService().addCustomer(name, cif, address);

		CustomerDtoJaxb customerDto = CustomerToCustomerDtoJaxbConversor.toCustomerDtoJaxb(customer);

		String requestUri = ui.getRequestUri().toString();
		return Response
				.created(URI.create(requestUri + (requestUri.endsWith("/") ? "" : "/") + customer.getCustomerId()))
				.entity(customerDto).build();

	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public void updateCustomer(@FormParam("customerId") Long customerId, @FormParam("name") String name,
			@FormParam("cif") String cif, @FormParam("address") String address, @Context UriInfo ui)
			throws InstanceNotFoundException, InputValidationException {

		DeliveryServiceFactory.getService().updateCustomer(customerId, name, cif, address);

	}

	@DELETE
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public void removeCustomer(@FormParam("customerId") Long customerId, @Context UriInfo ui)
			throws InstanceNotFoundException, InputValidationException, CustomerWithShipmentsException {

		DeliveryServiceFactory.getService().removeCustomer(customerId);

	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public CustomerDtoJaxb findCustomerById(@PathParam("customerId") Long customerId)
			throws InputValidationException, InstanceNotFoundException {

		Customer customer = DeliveryServiceFactory.getService().findCustomerById(customerId);

		return CustomerToCustomerDtoJaxbConversor.toCustomerDtoJaxb(customer);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public CustomerDtoJaxbList findCustomersByName(@QueryParam("keywords") String keywords, @Context UriInfo ui)
			throws InputValidationException {

		List<Customer> customers = DeliveryServiceFactory.getService().findCustomersByName(keywords);

		return CustomerToCustomerDtoJaxbConversor.toCustomerDtoJaxbList(customers);

	}

}

package es.udc.rs.deliveries.jaxrs.resources;

import javax.ws.rs.Consumes;
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

import es.udc.rs.deliveries.jaxrs.dto.shipment.ShipmentDtoJaxb;
import es.udc.rs.deliveries.jaxrs.dto.shipment.ShipmentDtoJaxbList;
import es.udc.rs.deliveries.jaxrs.dto.shipment.ShipmentStateDtoJaxb;

@Path("/shipments")
public class ShipmentResource {

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addShipment(ShipmentDtoJaxb shipmentDto, @Context UriInfo ui) {
		return null;

	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public void updateShipmentState(@FormParam("shipmentId") Long shipmentId,
			@FormParam("state") ShipmentStateDtoJaxb shipmentState, @Context UriInfo ui) {

	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public void cancelShipmentState(@FormParam("shipmentId") Long shipmentId, @Context UriInfo ui) {

	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ShipmentDtoJaxb findShipmentById(@PathParam("id") String id) {
		return null;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ShipmentDtoJaxbList findShipmentsByCustomer(@QueryParam("customerId") String keywords,
			@QueryParam("start") Long start, @QueryParam("count") Long count) {
		return null;
	}

}

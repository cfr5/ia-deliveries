package es.udc.rs.deliveries.jaxrs.resources;

import java.net.URI;
import java.util.List;

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

import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
import es.udc.rs.deliveries.exceptions.InvalidStateException;
import es.udc.rs.deliveries.exceptions.ShipmentNotPendingException;
import es.udc.rs.deliveries.jaxrs.dto.shipment.ShipmentDtoJaxb;
import es.udc.rs.deliveries.jaxrs.dto.shipment.ShipmentDtoJaxbList;
import es.udc.rs.deliveries.jaxrs.dto.shipment.ShipmentStateDtoJaxb;
import es.udc.rs.deliveries.jaxrs.util.ShipmentStateToShipmentStateDtoJaxbConversor;
import es.udc.rs.deliveries.jaxrs.util.ShipmentToShipmentDtoJaxbConversor;
import es.udc.rs.deliveries.model.deliveryservice.DeliveryServiceFactory;
import es.udc.rs.deliveries.model.shipment.Shipment;
import es.udc.rs.deliveries.model.shipment.ShipmentState;

@Path("/shipments")
public class ShipmentResource {

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addShipment(@FormParam("customerId") Long customerId,
			@FormParam("packageReference") Long packageReference, @FormParam("address") String address,
			@Context UriInfo ui) throws InputValidationException, InstanceNotFoundException {

		Shipment shipment = DeliveryServiceFactory.getService().addShipment(customerId, packageReference, address);

		ShipmentDtoJaxb shipmentDto = ShipmentToShipmentDtoJaxbConversor.toShipmentDtoJaxb(shipment);

		String requestUri = ui.getRequestUri().toString();
		return Response
				.created(URI.create(requestUri + (requestUri.endsWith("/") ? "" : "/") + shipment.getShipmentId()))
				.entity(shipmentDto).build();

	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public void updateShipmentState(@PathParam("shipmentId") Long shipmentId,
			@QueryParam("state") ShipmentStateDtoJaxb shipmentStateDto, @Context UriInfo ui)
			throws InputValidationException, InstanceNotFoundException, InvalidStateException {

		ShipmentState shipmentState = ShipmentStateToShipmentStateDtoJaxbConversor.toShipmentState(shipmentStateDto);

		DeliveryServiceFactory.getService().updateShipmentState(shipmentId, shipmentState);

	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public void cancelShipmentState(@PathParam("shipmentId") Long shipmentId, @Context UriInfo ui)
			throws InputValidationException, InstanceNotFoundException, ShipmentNotPendingException {

		DeliveryServiceFactory.getService().cancelShipment(shipmentId);

	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ShipmentDtoJaxb findShipmentById(@PathParam("shipmentId") Long shipmentId)
			throws InputValidationException, InstanceNotFoundException {

		Shipment shipment = DeliveryServiceFactory.getService().findShipmentById(shipmentId);

		return ShipmentToShipmentDtoJaxbConversor.toShipmentDtoJaxb(shipment);

	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ShipmentDtoJaxbList findShipmentsByCustomer(@QueryParam("customerId") Long customerId,
			@QueryParam("start") Long start, @QueryParam("count") Long count, @Context UriInfo ui)
			throws InputValidationException {

		List<Shipment> shipments = DeliveryServiceFactory.getService().findShipmentsByCustomer(customerId, start,
				count);

		return ShipmentToShipmentDtoJaxbConversor.toShipmentDtoJaxbList(shipments);

	}

}

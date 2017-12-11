package es.udc.rs.deliveries.jaxrs.resources;

import java.net.URI;
import java.util.List;

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
import es.udc.rs.deliveries.jaxrs.util.ShipmentToShipmentDtoJaxbConversor;
import es.udc.rs.deliveries.model.deliveryservice.DeliveryServiceFactory;
import es.udc.rs.deliveries.model.shipment.Shipment;
import es.udc.rs.deliveries.model.shipment.ShipmentState;

@Path("/shipments")
public class ShipmentResource {

	@POST
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
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{shipmentId}")
	public void updateShipmentState(@PathParam("shipmentId") String shipmentId,
			@FormParam("state") String shipmentState, @Context UriInfo ui)
			throws InputValidationException, InstanceNotFoundException, InvalidStateException {

		try {
			DeliveryServiceFactory.getService().updateShipmentState(Long.parseLong(shipmentId),
					ShipmentState.valueOf(shipmentState));
		} catch (NumberFormatException e) {
			throw new InputValidationException("Invalid Request: unable to parse shipment id '" + shipmentId + "'");
		} catch (IllegalArgumentException e) {
			throw new InputValidationException(
					"Invalid Request: unable to parse shipment state'" + shipmentState + "'");
		}

	}

	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{shipmentId}")
	public void cancelShipmentState(@PathParam("shipmentId") String shipmentId, @Context UriInfo ui)
			throws InputValidationException, InstanceNotFoundException, ShipmentNotPendingException {

		try {
			DeliveryServiceFactory.getService().cancelShipment(Long.parseLong(shipmentId));
		} catch (NumberFormatException e) {
			throw new InputValidationException("Invalid Request: unable to parse shipment id '" + shipmentId + "'");
		}
	}

	@GET
	@Path("/{shipmentId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ShipmentDtoJaxb findShipmentById(@PathParam("shipmentId") String shipmentId)
			throws InputValidationException, InstanceNotFoundException {

		try {
			Shipment shipment = DeliveryServiceFactory.getService().findShipmentById(Long.parseLong(shipmentId));
			return ShipmentToShipmentDtoJaxbConversor.toShipmentDtoJaxb(shipment);
		} catch (NumberFormatException e) {
			throw new InputValidationException("Invalid Request: unable to parse shipment id '" + shipmentId + "'");
		}

	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ShipmentDtoJaxbList findShipmentsByCustomer(@QueryParam("customerId") String customerId,
			@QueryParam("start") Long start, @QueryParam("count") Long count, @Context UriInfo ui)
			throws InputValidationException {

		try {
			List<Shipment> shipments = DeliveryServiceFactory.getService()
					.findShipmentsByCustomer(Long.parseLong(customerId), start, count);
			return ShipmentToShipmentDtoJaxbConversor.toShipmentDtoJaxbList(shipments);
		} catch (NumberFormatException e) {
			throw new InputValidationException("Invalid Request: unable to parse customer id '" + customerId + "'");
		}
	}

}

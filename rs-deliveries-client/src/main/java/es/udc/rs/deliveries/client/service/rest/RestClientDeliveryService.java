package es.udc.rs.deliveries.client.service.rest;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.udc.rs.deliveries.client.service.ClientDeliveryService;
import es.udc.rs.deliveries.client.service.rest.dto.customer.CustomerDtoJaxb;
import es.udc.rs.deliveries.client.service.rest.dto.customer.CustomerWithShipmentsExceptionDtoJaxb;
import es.udc.rs.deliveries.client.service.rest.dto.customer.InputValidationExceptionDtoJaxb;
import es.udc.rs.deliveries.client.service.rest.dto.customer.InstanceNotFoundExceptionDtoJaxb;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentStateDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.InvalidStateExceptionDtoJaxb;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ShipmentDtoJaxbList;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ShipmentNotPendingExceptionDtoJaxb;
import es.udc.rs.deliveries.client.util.JaxbExceptionConversor;
import es.udc.rs.deliveries.client.util.ShipmentDtoToShipmentDtoJaxbConversor;
import es.udc.rs.deliveries.exceptions.CustomerWithShipmentsException;
import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
import es.udc.rs.deliveries.exceptions.InvalidStateException;
import es.udc.rs.deliveries.exceptions.ShipmentNotPendingException;
import es.udc.ws.util.configuration.ConfigurationParametersManager;

public abstract class RestClientDeliveryService implements ClientDeliveryService {

	private static javax.ws.rs.client.Client client = null;

	private final static String ENDPOINT_ADDRESS_PARAMETER = "RestClientDeliveryService.endpointAddress";
	private WebTarget endPointWebTarget = null;

	/*
	 * Client instances are expensive resources. It is recommended a configured
	 * instance is reused for the creation of Web resources. The creation of Web
	 * resources, the building of requests and receiving of responses are
	 * guaranteed to be thread safe. Thus a Client instance and WebTarget
	 * instances may be shared between multiple threads.
	 */
	private static Client getClient() {
		if (client == null) {
			client = ClientBuilder.newClient();
		}
		return client;
	}

	private WebTarget getEndpointWebTarget() {
		if (endPointWebTarget == null) {
			endPointWebTarget = getClient()
					.target(ConfigurationParametersManager.getParameter(ENDPOINT_ADDRESS_PARAMETER));
		}
		return endPointWebTarget;
	}

	protected abstract MediaType getMediaType();

	@Override
	public Long addCustomer(String name, String cif, String address)
			throws InstanceNotFoundException, InputValidationException {
		WebTarget wt = getEndpointWebTarget().path("customers");
		Form form = new Form();
		form.param("name", name);
		form.param("cif", cif);
		form.param("address", address);
	
		Response response = wt.request().accept(this.getMediaType()).post(Entity.form(form));
		try {
			validateResponse(Response.Status.CREATED.getStatusCode(), response);
			CustomerDtoJaxb customer = response.readEntity(CustomerDtoJaxb.class);
			return customer.getCustomerId();
		} catch (InputValidationException | InstanceNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

	@Override
	public void deleteCustomer(Long customerId)
			throws InstanceNotFoundException, InputValidationException, CustomerWithShipmentsException {
		WebTarget wt = getEndpointWebTarget().path("customers/{id}").resolveTemplate("id", customerId);
		Response response = wt.request().accept(this.getMediaType()).delete();
		try {
			validateResponse(Response.Status.NO_CONTENT.getStatusCode(), response);
		} catch (InstanceNotFoundException | InputValidationException | CustomerWithShipmentsException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}

	}

	@Override
	public void changeState(Long shipmentId, ClientShipmentStateDto newState)
			throws InstanceNotFoundException, InputValidationException, InvalidStateException {
		WebTarget wt = getEndpointWebTarget().path("shipments/{id}").resolveTemplate("id", shipmentId);
		Form form = new Form();
		form.param("state", newState.name());
		Response response = wt.request().accept(this.getMediaType()).put(Entity.form(form));
		try {
			validateResponse(Response.Status.NO_CONTENT.getStatusCode(), response);
		} catch (InstanceNotFoundException | InputValidationException | InvalidStateException e) {
			throw e;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}

	}

	@Override
	public List<ClientShipmentDto> findByCustomer(Long customerId, Long start, Long amount)
			throws InputValidationException {
		WebTarget wt = getEndpointWebTarget().path("shipments").queryParam("customerId", customerId)
				.queryParam("start", start).queryParam("count", amount);
		Response response = wt.request().accept(this.getMediaType()).get();
		try {
			validateResponse(Response.Status.OK.getStatusCode(), response);
			ShipmentDtoJaxbList shipments = response.readEntity(ShipmentDtoJaxbList.class);
			return ShipmentDtoToShipmentDtoJaxbConversor.toShipmentDtos(shipments);
		} catch (InputValidationException e) {
			throw e;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

	private void validateResponse(int expectedStatusCode, Response response)
			throws InstanceNotFoundException, InputValidationException, ShipmentNotPendingException,
			InvalidStateException, CustomerWithShipmentsException {

		Response.Status statusCode = Response.Status.fromStatusCode(response.getStatus());
		String contentType = response.getMediaType() != null ? response.getMediaType().toString() : null;
		boolean expectedContentType = this.getMediaType().toString().equalsIgnoreCase(contentType);
		if (!expectedContentType && statusCode.getStatusCode() >= Response.Status.BAD_REQUEST.getStatusCode()) {
			throw new RuntimeException("HTTP error; status code = " + statusCode);
		}
		switch (statusCode) {
		case NOT_FOUND: {
			InstanceNotFoundExceptionDtoJaxb exDto = response.readEntity(InstanceNotFoundExceptionDtoJaxb.class);
			throw JaxbExceptionConversor.toInstanceNotFoundException(exDto);
		}
		case BAD_REQUEST: {
			InputValidationExceptionDtoJaxb exDto = response.readEntity(InputValidationExceptionDtoJaxb.class);
			throw JaxbExceptionConversor.toInputValidationException(exDto);
		}
		case CONFLICT: {
			CustomerWithShipmentsExceptionDtoJaxb exDto = response
					.readEntity(CustomerWithShipmentsExceptionDtoJaxb.class);
			throw JaxbExceptionConversor.toCustomerWithShipmentsException(exDto);
		}
		case FORBIDDEN: {
			InvalidStateExceptionDtoJaxb exDto = response.readEntity(InvalidStateExceptionDtoJaxb.class);
			throw JaxbExceptionConversor.toInvalidStateException(exDto);
		}
		case UNAUTHORIZED: {
			ShipmentNotPendingExceptionDtoJaxb exDto = response.readEntity(ShipmentNotPendingExceptionDtoJaxb.class);
			throw JaxbExceptionConversor.toShipmentnotPendingException(exDto);
		}
		default:
			if (statusCode.getStatusCode() != expectedStatusCode) {
				throw new RuntimeException("HTTP error; status code = " + statusCode);
			}
			break;
		}
	}

}

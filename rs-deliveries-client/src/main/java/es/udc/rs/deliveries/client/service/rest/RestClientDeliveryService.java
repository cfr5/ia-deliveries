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
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentStateDto;
import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
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
	public Long addCustomer(String name, String Cif, String address) {
		WebTarget wt = getEndpointWebTarget().path("customers");
		Form form = new Form();
		form.param("name", name);
		form.param("Cif", Cif);
		form.param("address", address);

		Response response = wt.request().accept(this.getMediaType()).post(Entity.form(form));
		return 0l;
//		try {
//			//validateResponse(Response.Status.CREATED.getStatusCode(), response);
//			CustomerDtoJaxb customer = response.readEntity(CustomerDtoJaxb.class);
//			return customer.getCustomerId();
//		} catch (InputValidationException | InstanceNotFoundException ex) {
//			throw ex;
//		} catch (Exception ex) {
//			throw new RuntimeException(ex);
//		} finally {
//			if (response != null) {
//				response.close();
//			}
//		}
	}

	@Override
	public void deleteCustomer(Long customerId) {
		WebTarget wt = getEndpointWebTarget().path("customers/{id}").resolveTemplate("id", customerId);
		Response response = wt.request().accept(this.getMediaType()).delete();
		response.close();
//		try {
//			//validateResponse(Response.Status.NO_CONTENT.getStatusCode(), response);
//		} catch (InstanceNotFoundException ex) {
//			throw ex;
//		} catch (Exception ex) {
//			throw new RuntimeException(ex);
//		} finally {
//			if (response != null) {
//				response.close();
//			}
//		}

	}

	@Override
	public void changeState(Long shipmentId, ClientShipmentStateDto newState) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ClientShipmentDto> findByCustomer(Long customerId, Long start, Long amount) {
		// TODO Auto-generated method stub
		return null;
	}
}

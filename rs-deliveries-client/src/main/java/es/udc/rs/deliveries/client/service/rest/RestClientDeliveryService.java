package es.udc.rs.deliveries.client.service.rest;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import es.udc.rs.deliveries.client.service.ClientDeliveryService;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentStateDto;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(Long parseLong) {
		// TODO Auto-generated method stub

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

package es.udc.rs.deliveries.client.service.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;

import es.udc.rs.deliveries.client.service.rest.dto.customer.ClientCustomerDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentStateDto;

public class RestClientDeliveryServiceJson extends RestClientDeliveryService {

	@Override
	protected MediaType getMediaType() {
		return MediaType.APPLICATION_JSON_TYPE;
	}

	@Override
	public Long addCustomer(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCustomer(Long customerId, String name, String Cif, String address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientCustomerDto findCustomer(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClientCustomerDto> findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(Long parseLong) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long addShipment(Long customerId, Long packageReference, String address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeState(Long shipmentId, ClientShipmentStateDto newState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelShipment(Long shipmentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientShipmentDto findShipment(Long shipmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClientShipmentDto> findByCustomer(Long customerId, Long start, Long amount) {
		// TODO Auto-generated method stub
		return null;
	}

}

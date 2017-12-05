package es.udc.rs.deliveries.client.service;

import java.util.List;

import es.udc.rs.deliveries.client.service.rest.dto.customer.ClientCustomerDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentStateDto;

public interface ClientDeliveryService {

	public Long addCustomer(String string, String string2, String string3);

	public void updateCustomer(Long customerId, String name, String Cif, String address);

	public ClientCustomerDto findCustomer(Long customerId);

	public List<ClientCustomerDto> findByKeyword(String keyword);

	public void deleteCustomer(Long parseLong);

	public Long addShipment(Long customerId, Long packageReference, String address);

	public void changeState(Long shipmentId, ClientShipmentStateDto newState);

	public void cancelShipment(Long shipmentId);

	public ClientShipmentDto findShipment(Long shipmentId);

	public List<ClientShipmentDto> findByCustomer(Long customerId, Long start, Long amount);

}

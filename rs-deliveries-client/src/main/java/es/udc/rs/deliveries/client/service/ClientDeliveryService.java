package es.udc.rs.deliveries.client.service;

import java.util.List;

import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentStateDto;

public interface ClientDeliveryService {

	public Long addCustomer(String name, String Cif, String address);

	public void deleteCustomer(Long parseLong);

	public void changeState(Long shipmentId, ClientShipmentStateDto newState);

	public List<ClientShipmentDto> findByCustomer(Long customerId, Long start, Long amount);

}

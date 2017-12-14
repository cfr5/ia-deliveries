package es.udc.rs.deliveries.client.service;

import java.util.List;

import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentStateDto;
import es.udc.rs.deliveries.exceptions.CustomerWithShipmentsException;
import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
import es.udc.rs.deliveries.exceptions.InvalidStateException;

public interface ClientDeliveryService {

	public Long addCustomer(String name, String Cif, String address) throws InstanceNotFoundException, InputValidationException;

	public void deleteCustomer(Long parseLong) throws InstanceNotFoundException, InputValidationException, CustomerWithShipmentsException;

	public void changeState(Long shipmentId, ClientShipmentStateDto newState) throws InstanceNotFoundException, InputValidationException, InvalidStateException;

	public List<ClientShipmentDto> findByCustomer(Long customerId, Long start, Long amount) throws InputValidationException;

}

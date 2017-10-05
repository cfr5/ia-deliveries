package es.udc.rs.deliveries.model.deliveryservice;

import java.util.List;

import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
import es.udc.rs.deliveries.exceptions.InvalidStateException;
import es.udc.rs.deliveries.exceptions.ShipmentNotPendingException;
import es.udc.rs.deliveries.model.customer.Customer;
import es.udc.rs.deliveries.model.shipment.Shipment;
import es.udc.rs.deliveries.model.shipment.ShipmentState;

public interface DeliveryService {

	public Customer addCustomer(String name, String cif, String address) throws InputValidationException;

	public Customer updateCustomer(Long customerId, String name, String cif, String address)
			throws InputValidationException, InstanceNotFoundException;

	public void removeCustomer(Long customerId) throws InstanceNotFoundException;

	public Customer findCustomerById(Long customerId) throws InstanceNotFoundException;

	public List<Customer> findCustomersByName(String keywords) throws InputValidationException;

	public Shipment addShipment(Long customerId, Long packageReference, String address)
			throws InputValidationException, InstanceNotFoundException;

	public Shipment updateShipmentState(Long shipmentId, ShipmentState state)
			throws InputValidationException, InstanceNotFoundException, InvalidStateException;

	public void cancelShipment(Long shipmentId) throws InputValidationException, InstanceNotFoundException, ShipmentNotPendingException;

	public Shipment findShipmentById(Long shipmentId) throws InputValidationException, InstanceNotFoundException;

	public List<Shipment> findShipmentsByCustomer(Long customerId, Long start, Long count)
			throws InputValidationException;

}

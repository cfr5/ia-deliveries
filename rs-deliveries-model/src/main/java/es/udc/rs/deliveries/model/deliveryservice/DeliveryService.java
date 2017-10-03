package es.udc.rs.deliveries.model.deliveryservice;

import java.util.List;

import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
import es.udc.rs.deliveries.model.customer.Customer;
import es.udc.rs.deliveries.model.shipment.Shipment;
import es.udc.rs.deliveries.model.shipment.ShipmentState;

public interface DeliveryService {

	public Customer addCustomer(Customer customer) throws InputValidationException;

	public Customer updateCustomer(Customer customer) throws InputValidationException, InstanceNotFoundException;

	public void removeCustomer(Long customerId) throws InstanceNotFoundException;

	public Customer findCustomerById(Long customerId) throws InstanceNotFoundException;

	public List<Customer> findCustomersByName(String keywords) throws InputValidationException;

	public Shipment addShipment(Shipment shipment) throws InputValidationException;

	public Shipment updateShipmentState(Long shipmentId, ShipmentState state)
			throws InputValidationException, InstanceNotFoundException;

	public void cancelShipment(Long shipmentId) throws InputValidationException, InstanceNotFoundException;

	public Shipment findShipmentById(Long shipmentId) throws InputValidationException, InstanceNotFoundException;

	public List<Shipment> findShipmentsByCustomer(Long customerId, Long firstElement, Long maxShipments)
			throws InputValidationException;

}

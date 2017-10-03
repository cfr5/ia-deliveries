package es.udc.rs.deliveries.model.deliveryservice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
import es.udc.rs.deliveries.model.customer.Customer;
import es.udc.rs.deliveries.model.shipment.Shipment;
import es.udc.rs.deliveries.model.shipment.ShipmentState;

public class MockDeliveryService implements DeliveryService {

	private static Map<Long,Customer> customersMap = new LinkedHashMap<Long,Customer>();
	private static Map<Long,Shipment> shipmentsMap = new LinkedHashMap<Long,Shipment>();
	private static Map<Long,List<Shipment>> shipmentsByUserMap = new LinkedHashMap<Long,List<Shipment>>();

	private static long lastCustomerId = 0;
	private static long lastShippingId = 0;
	

	private static synchronized long getNextCustomertId() {
		return ++lastCustomerId;
	}
	
	private static synchronized long getNextShippingId() {
		return ++lastShippingId;
	}

	@Override
	public Customer addCustomer(Customer customer) throws InputValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws InputValidationException, InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCustomer(Long customerId) throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer findCustomerById(Long customerId) throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findCustomersByName(String keywords) throws InputValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shipment addShipment(Shipment shipment) throws InputValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shipment updateShipmentState(Long shipmentId, ShipmentState state)
			throws InputValidationException, InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelShipment(Long shipmentId) throws InputValidationException, InstanceNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shipment findShipmentById(Long shipmentId) throws InputValidationException, InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shipment> findShipmentsByCustomer(Long customerId, Long firstElement, Long maxShipments)
			throws InputValidationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

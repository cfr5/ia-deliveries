package es.udc.rs.deliveries.model.deliveryservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
import es.udc.rs.deliveries.model.customer.Customer;
import es.udc.rs.deliveries.model.shipment.Shipment;
import es.udc.rs.deliveries.model.shipment.ShipmentState;
import es.udc.rs.deliveries.validation.PropertyValidator;

public class MockDeliveryService implements DeliveryService {

	private static Map<Long,Customer> customersMap = new LinkedHashMap<Long,Customer>();
	private static Map<Long,Shipment> shipmentsMap = new LinkedHashMap<Long,Shipment>();
	private static Map<Long,List<Shipment>> shipmentsByUserMap = new LinkedHashMap<Long,List<Shipment>>();

	private static long lastCustomerId = 0;
	private static long lastShippingId = 0;
	
	private final static int MININT = 0;
	private final static int MAXINT = 999999999;

	private static synchronized long getNextCustomertId() {
		return ++lastCustomerId;
	}
	
	private static synchronized long getNextShippingId() {
		return ++lastShippingId;
	}
	
	private static void validateCustomer(Customer customer) throws InputValidationException{
		PropertyValidator.validateLong("CustomerId", customer.getCustomerId(),MININT , MAXINT);
		PropertyValidator.validateMandatoryString("Name",customer.getName());
		PropertyValidator.validateMandatoryString("Cif", customer.getCif());
		PropertyValidator.validateMandatoryString("address", customer.getCif());
		PropertyValidator.validatePastDate("creationDate", customer.getCreationDate());
		
	}
	
	private static void validateShipment(Shipment shipment) throws InputValidationException{
		PropertyValidator.validateLong("shipmentId", shipment.getShipmentId(), MININT, MAXINT);
		PropertyValidator.validateLong("CustomerId", shipment.getCustomerId(),MININT , MAXINT);
		PropertyValidator.validateLong("packageReference", shipment.getCustomerId(),MININT , MAXINT);
	    PropertyValidator.validateMandatoryString("address", shipment.getAddress());
	    //state;
		PropertyValidator.validatePastDate("creationDate", shipment.getCreationDate());
	    //maxDeliveryDate;
	    //deliveryDate;
	}

	@Override
	public Customer addCustomer(Customer customer) throws InputValidationException {
		validateCustomer(customer);
		customer.setCustomerId(getNextCustomertId());
		customersMap.put(customer.getCustomerId(), customer);
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws InputValidationException, InstanceNotFoundException {
		if (!customersMap.containsKey(customer.getCustomerId())){
			throw new InstanceNotFoundException(customer.getCustomerId(), Customer.class.getName());
		}
		validateCustomer(customer);
		customersMap.put(customer.getCustomerId(), customer);
		
		return customer;
	}

	@Override
	public void removeCustomer(Long customerId) throws InstanceNotFoundException {
		Customer customer = customersMap.remove(customerId);
		if (customer == null){
			throw new InstanceNotFoundException(customerId, Customer.class.getName());
		}
	}

	@Override
	public Customer findCustomerById(Long customerId) throws InstanceNotFoundException {
		Customer customer = customersMap.get(customerId);
		if (customer == null){
			throw new InstanceNotFoundException(customerId, Customer.class.getName());
		}
		return customer;
	}

	@Override
	public List<Customer> findCustomersByName(String keywords) throws InputValidationException {
		PropertyValidator.validateMandatoryString("keywords", keywords);
		List<Customer> foundCustomers = new ArrayList<Customer>();
		String searchKeywords = keywords != null ? keywords.toLowerCase() : "";
		for (Customer customer : customersMap.values()){
			if (customer.getName().toLowerCase().contains(searchKeywords)){
				foundCustomers.add(customer);
			}
		}
		return foundCustomers;
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

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
	
	@Override
	public Customer addCustomer(String name, String cif, String address) throws InputValidationException {
		Customer customer = new Customer(0l, name, cif, address,Calendar.getInstance() );
		PropertyValidator.validateMandatoryString("name", name);
		PropertyValidator.validateMandatoryString("cif",cif);
		PropertyValidator.validateMandatoryString("address", address);
		customer.setCustomerId(getNextCustomertId());
		customersMap.put(customer.getCustomerId(), customer);
		return customer;
	}

	@Override
	public Customer updateCustomer(Long customerId,String name, String cif, String address) throws  InstanceNotFoundException, InputValidationException {
		
		PropertyValidator.validateLong("customerId", customerId, MININT, MAXINT);
		if (!customersMap.containsKey(customerId)){
			throw new InstanceNotFoundException(customerId, Customer.class.getName());
		}
		Customer customer = customersMap.get(customerId);
		try {
			PropertyValidator.validateMandatoryString("name", name);
			customer.setName(name);
		} catch (InputValidationException e) {
		}
		try {
			PropertyValidator.validateMandatoryString("cif", cif);
			customer.setCif(cif);
		} catch (InputValidationException e) {
		}
		try {
			PropertyValidator.validateMandatoryString("address", address);
			customer.setAddress(address);
		} catch (InputValidationException e) {
		}
		customersMap.put(customer.getCustomerId(), customer);
		
		return customer;
	}

	@Override
	public void removeCustomer(Long customerId) throws InstanceNotFoundException, InputValidationException {
		PropertyValidator.validateLong("customerId", customerId, MININT, MAXINT);
		Customer customer = customersMap.remove(customerId);
		if (customer == null){
			throw new InstanceNotFoundException(customerId, Customer.class.getName());
		}
	}

	@Override
	public Customer findCustomerById(Long customerId) throws InstanceNotFoundException, InputValidationException {
		PropertyValidator.validateLong("customerId", customerId, MININT, MAXINT);
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
	public Shipment addShipment(Long customerId,Long packageReference,String address) throws InputValidationException {
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

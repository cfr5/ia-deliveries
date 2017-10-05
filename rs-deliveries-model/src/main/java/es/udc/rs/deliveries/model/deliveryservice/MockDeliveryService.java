package es.udc.rs.deliveries.model.deliveryservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
import es.udc.rs.deliveries.exceptions.InvalidStateException;
import es.udc.rs.deliveries.exceptions.ShipmentNotPendingException;
import es.udc.rs.deliveries.model.customer.Customer;
import es.udc.rs.deliveries.model.shipment.Shipment;
import es.udc.rs.deliveries.model.shipment.ShipmentState;
import es.udc.rs.deliveries.validation.PropertyValidator;

public class MockDeliveryService implements DeliveryService {

	private static Map<Long, Customer> customersMap = new LinkedHashMap<Long, Customer>();
	private static Map<Long, Shipment> shipmentsMap = new LinkedHashMap<Long, Shipment>();
	private static Map<Long, List<Shipment>> shipmentsByUserMap = new LinkedHashMap<Long, List<Shipment>>();

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

		Customer customer = new Customer(0l, name, cif, address, Calendar.getInstance());
		PropertyValidator.validateMandatoryString("name", name);
		PropertyValidator.validateMandatoryString("cif", cif);
		PropertyValidator.validateMandatoryString("address", address);
		customer.setCustomerId(getNextCustomertId());
		customersMap.put(customer.getCustomerId(), customer);
		return customer;
	}

	@Override
	public Customer updateCustomer(Long customerId, String name, String cif, String address)
			throws InstanceNotFoundException, InputValidationException {

		PropertyValidator.validateLong("customerId", customerId, MININT, MAXINT);
		if (!customersMap.containsKey(customerId)) {
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
		if (customer == null) {
			throw new InstanceNotFoundException(customerId, Customer.class.getName());
		}
	}

	@Override
	public Customer findCustomerById(Long customerId) throws InstanceNotFoundException, InputValidationException {
		PropertyValidator.validateLong("customerId", customerId, MININT, MAXINT);
		Customer customer = customersMap.get(customerId);
		if (customer == null) {
			throw new InstanceNotFoundException(customerId, Customer.class.getName());
		}
		return customer;
	}

	@Override
	public List<Customer> findCustomersByName(String keywords) throws InputValidationException {
		PropertyValidator.validateMandatoryString("keywords", keywords);
		List<Customer> foundCustomers = new ArrayList<Customer>();
		String searchKeywords = keywords != null ? keywords.toLowerCase() : "";
		for (Customer customer : customersMap.values()) {
			if (customer.getName().toLowerCase().contains(searchKeywords)) {
				foundCustomers.add(customer);
			}
		}
		return foundCustomers;
	}

	@Override
	public Shipment addShipment(Long customerId, Long packageReference, String address)
			throws InputValidationException, InstanceNotFoundException {

		PropertyValidator.validateLong("customerId", customerId, MININT, MAXINT);
		PropertyValidator.validateLong("packageReference", packageReference, MININT, MAXINT);
		PropertyValidator.validateMandatoryString("address", address);

		if (!customersMap.containsKey(customerId))
			throw new InstanceNotFoundException(customerId, Shipment.class.getName());

		Calendar creationDate = Calendar.getInstance();
		Calendar maxDeliveryDate = Calendar.getInstance();
		maxDeliveryDate.add(Calendar.HOUR_OF_DAY, 48);

		Shipment shipment = new Shipment(getNextShippingId(), customerId, packageReference, address,
				ShipmentState.PENDING, creationDate, maxDeliveryDate);

		shipmentsMap.put(shipment.getShipmentId(), shipment);

		List<Shipment> userShipments = shipmentsByUserMap.get(customerId);

		if (userShipments == null) {
			userShipments = new ArrayList<>();
		}

		userShipments.add(shipment);

		return shipment;
	}

	@Override
	public Shipment updateShipmentState(Long shipmentId, ShipmentState newState)
			throws InputValidationException, InstanceNotFoundException, InvalidStateException {

		PropertyValidator.validateLong("shipmentId", shipmentId, MININT, MAXINT);

		if (newState == null)
			throw new InputValidationException("Invalid 'state' value (it cannot be null neither empty): " + newState);

		Shipment shipment = shipmentsMap.get(shipmentId);

		if (shipment == null)
			throw new InstanceNotFoundException(shipmentId, Shipment.class.getName());

		ShipmentState currentState = shipment.getState();

		if (((newState == ShipmentState.SENT) && (currentState != ShipmentState.PENDING))
				|| ((newState == ShipmentState.DELIVERED) && (currentState != ShipmentState.SENT)))
			throw new InvalidStateException(currentState.toString(), newState.toString());

		shipment.setState(newState);

		return shipment;
	}

	@Override
	public void cancelShipment(Long shipmentId)
			throws InputValidationException, InstanceNotFoundException, ShipmentNotPendingException {
		PropertyValidator.validateLong("shipmentId", shipmentId, MININT, MAXINT);

		Shipment shipment = shipmentsMap.get(shipmentId);

		if (shipment == null)
			throw new InstanceNotFoundException(shipmentId, Shipment.class.getName());

		ShipmentState currentState = shipment.getState();

		if (currentState != ShipmentState.PENDING)
			throw new ShipmentNotPendingException(shipmentId);

		shipment.setState(ShipmentState.CANCELLED);

	}

	@Override
	public Shipment findShipmentById(Long shipmentId) throws InputValidationException, InstanceNotFoundException {

		PropertyValidator.validateLong("shipmentId", shipmentId, MININT, MAXINT);

		Shipment shipment = shipmentsMap.get(shipmentId);

		if (shipment == null)
			throw new InstanceNotFoundException(shipmentId, Shipment.class.getName());

		return shipment;
	}

	@Override
	public List<Shipment> findShipmentsByCustomer(Long customerId, Long start, Long count)
			throws InputValidationException {

		PropertyValidator.validateLong("shipmentId", customerId, MININT, MAXINT);
		PropertyValidator.validateLong("start", start, MININT, MAXINT);
		PropertyValidator.validateLong("count", count, MININT, MAXINT);

		List<Shipment> shipments = shipmentsByUserMap.get(customerId);

		if (shipments == null)
			return new ArrayList<>();

		return shipments;
	}

}

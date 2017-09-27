package es.udc.rs.deliveries.model.deliveryservice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import es.udc.rs.deliveries.model.customer.Customer;
import es.udc.rs.deliveries.model.shipment.Shipment;

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
	
	
}

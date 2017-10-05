package es.udc.rs.deliveries.exceptions;

@SuppressWarnings("serial")
public class ShipmentNotPendingException extends Exception {

	public ShipmentNotPendingException(Long shipmentId) {
		super("The shipment '" + shipmentId + "' is not 'PENDING'");
	}
}

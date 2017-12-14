package es.udc.rs.deliveries.exceptions;

@SuppressWarnings("serial")
public class ShipmentNotPendingException extends Exception {

	private Long shipmentId;

	public ShipmentNotPendingException(Long shipmentId) {
		super("The shipment '" + shipmentId + "' is not 'PENDING'");
		this.shipmentId = shipmentId;
	}
	
	public Long getShipmentId(){
		return this.shipmentId;
	}
}

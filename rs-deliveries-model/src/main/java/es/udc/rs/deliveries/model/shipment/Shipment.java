package es.udc.rs.deliveries.model.shipment;

import java.util.Calendar;

public class Shipment {

    private Long shipmentId;
    private Long customerId;
    private Long packageReference;
    private String address;
    private ShipmentState state;
    private Calendar creationDate;
    private Calendar maxDeliveryDate;
    private Calendar deliveryDate;
    
	public Shipment(Long shipmentId, Long customerId, Long packageReference, String address, ShipmentState state,
			Calendar creationDate, Calendar maxDeliveryDate) {
		super();
		this.shipmentId = shipmentId;
		this.customerId = customerId;
		this.packageReference = packageReference;
		this.address = address;
		this.state = ShipmentState.PENDING;
		this.creationDate = creationDate;
		this.maxDeliveryDate = maxDeliveryDate;
	}

	public Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getPackageReference() {
		return packageReference;
	}

	public void setPackageReference(Long packageReference) {
		this.packageReference = packageReference;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ShipmentState getState() {
		return state;
	}

	public void setState(ShipmentState state) {
		this.state = state;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public Calendar getMaxDeliveryDate() {
		return maxDeliveryDate;
	}

	public void setMaxDeliveryDate(Calendar maxDeliveryDate) {
		this.maxDeliveryDate = maxDeliveryDate;
	}

	public Calendar getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Calendar deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	

}
package es.udc.rs.deliveries.client.service.rest.dto.shipment;

import java.util.Calendar;

public class ClientShipmentDto {

	private Long shipmentId;
	private Long customerId;
	private Long packageReference;
	private String address;
	private ClientShipmentStateDto state;
	private Calendar creationDate;
	private Calendar deliveryDate;
	private Long hoursToDelivery;

	public ClientShipmentDto(Long shipmentId, Long customerId, Long packageReference, String address,
			ClientShipmentStateDto state, Calendar creationDate, Calendar deliveryDate, Long hoursToDelivery) {
		super();
		this.shipmentId = shipmentId;
		this.customerId = customerId;
		this.packageReference = packageReference;
		this.address = address;
		this.state = state;
		this.creationDate = creationDate;
		this.deliveryDate = deliveryDate;
		this.hoursToDelivery = hoursToDelivery;
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

	public ClientShipmentStateDto getState() {
		return state;
	}

	public void setState(ClientShipmentStateDto state) {
		this.state = state;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public Calendar getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Calendar deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Long getHoursToDelivery() {
		return hoursToDelivery;
	}

	public void setHoursToDelivery(Long hoursToDelivery) {
		this.hoursToDelivery = hoursToDelivery;
	}

}

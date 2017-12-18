
package es.udc.rs.deliveries.client.service.rest.dto.shipment;

import java.text.SimpleDateFormat;
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

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append("ShipmentId: " + this.shipmentId);
		sBuilder.append("\n\tCustomerId: " + this.customerId);
		sBuilder.append("\n\tPackageReference: " + this.packageReference);
		sBuilder.append("\n\tAddress: " + this.address);
		sBuilder.append("\n\tState: " + this.state.name());
		sBuilder.append("\n\tCreationDate: " + this.formatCalendar(this.creationDate));
		sBuilder.append("\n\tDeliveryDate: " + this.formatCalendar(this.deliveryDate));
		sBuilder.append("\n\tHoursToDelivery: " + this.hoursToDelivery);
		sBuilder.append("\n");

		return new String(sBuilder);
	}

	private String formatCalendar(Calendar date) {

		if (date == null) {
			return "";
		}

		SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
		return formater.format(date.getTime());
	}

}

package es.udc.rs.deliveries.jaxrs.dto.shipment;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import es.udc.rs.deliveries.model.shipment.ShipmentState;

@XmlRootElement(name = "shipment")
@XmlType(name = "shipmentType", propOrder = { "shipmentId", "customerId", "packageReference", "address", "state",
		"creationDate", "deliveryDate", "hoursToDelivery" })
public class ShipmentDtoJaxb {

	@XmlElement(required = true)
	private Long shipmentId;
	@XmlElement(required = true)
	private Long customerId;
	@XmlElement(required = true)
	private Long packageReference;
	@XmlElement(required = true)
	private String address;
	@XmlElement(required = true)
	private ShipmentState state;
	@XmlElement(required = true)
	private Calendar creationDate;
	@XmlElement(required = true)
	private Calendar deliveryDate;
	@XmlElement(required = true)
	private Long hoursToDelivery;

	public ShipmentDtoJaxb() {

	}

	public ShipmentDtoJaxb(Long shipmentId, Long customerId, Long packageReference, String address, ShipmentState state,
			Calendar creationDate, Calendar deliveryDate, Long hoursToDelivery) {
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
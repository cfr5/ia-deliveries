package es.udc.rs.deliveries.jaxrs.dto.shipment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "shipment")
@XmlType(name = "shipmentType", propOrder = { "shipmentId", "customerId", "packageReference", "address", "state",
		"creationDate", "maxDeliveryDate", "deliveryDate" })
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
	private ShipmentStateDtoJaxb state;
	@XmlElement(required = true)
	private DateDtoJaxb creationDate;
	@XmlElement(required = true)
	private DateDtoJaxb maxDeliveryDate;
	@XmlElement(required = true)
	private DateDtoJaxb deliveryDate;

	public ShipmentDtoJaxb(){
		
	}
	
	public ShipmentDtoJaxb(Long shipmentId, Long customerId, Long packageReference, String address,
			ShipmentStateDtoJaxb state, DateDtoJaxb creationDate, DateDtoJaxb maxDeliveryDate) {
		super();
		this.shipmentId = shipmentId;
		this.customerId = customerId;
		this.packageReference = packageReference;
		this.address = address;
		this.state = ShipmentStateDtoJaxb.PENDING;
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

	public ShipmentStateDtoJaxb getState() {
		return state;
	}

	public void setState(ShipmentStateDtoJaxb state) {
		this.state = state;
	}

	public DateDtoJaxb getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateDtoJaxb creationDate) {
		this.creationDate = creationDate;
	}

	public DateDtoJaxb getMaxDeliveryDate() {
		return maxDeliveryDate;
	}

	public void setMaxDeliveryDate(DateDtoJaxb maxDeliveryDate) {
		this.maxDeliveryDate = maxDeliveryDate;
	}

	public DateDtoJaxb getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(DateDtoJaxb deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
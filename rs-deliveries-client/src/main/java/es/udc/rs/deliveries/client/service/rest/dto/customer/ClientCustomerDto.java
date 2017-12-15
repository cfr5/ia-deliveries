package es.udc.rs.deliveries.client.service.rest.dto.customer;

import java.util.Calendar;

public class ClientCustomerDto {
	private Long customerId;
	private String name;
	private String Cif;
	private String address;
	private Calendar creationDate;

	public ClientCustomerDto(Long customerId, String name, String cif, String address, Calendar creationDate) {
		super();
		this.customerId = customerId;
		this.name = name;
		Cif = cif;
		this.address = address;
		this.creationDate = creationDate;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCif() {
		return Cif;
	}

	public void setCif(String cif) {
		Cif = cif;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

}

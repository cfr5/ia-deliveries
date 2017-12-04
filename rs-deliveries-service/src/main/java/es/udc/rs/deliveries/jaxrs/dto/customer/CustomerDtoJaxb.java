package es.udc.rs.deliveries.jaxrs.dto.customer;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="customer")
@XmlType(name="customerType", propOrder = {"customerId", "name", "Cif", "address","creationDate"})
public class CustomerDtoJaxb {

	@XmlElement(required = true)
    private Long customerId;
	@XmlElement(required = true)
    private String name;
	@XmlElement(required = true)
    private String Cif;
	@XmlElement(required = true)
	private String address;
	@XmlElement(required = true)
    private DateDtoJaxb creationDate;
    
	public CustomerDtoJaxb() {
    } 
	
	public CustomerDtoJaxb(Long customerId, String name, String cif, String address, Calendar creationDate) {
		super();
		this.customerId = customerId;
		this.name = name;
		Cif = cif;
		this.address = address;
		this.creationDate = new DateDtoJaxb(creationDate);
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

	public DateDtoJaxb getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateDtoJaxb creationDate) {
		this.creationDate = creationDate;
	}

}
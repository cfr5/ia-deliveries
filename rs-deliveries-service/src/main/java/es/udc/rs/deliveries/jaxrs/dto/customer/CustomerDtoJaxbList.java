package es.udc.rs.deliveries.jaxrs.dto.customer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "customers")
@XmlType(name = "customerListType")
public class CustomerDtoJaxbList {

	@XmlElement(name = "customer")
	private List<CustomerDtoJaxb> customer = null;

	public CustomerDtoJaxbList() {
    	this.customer = new ArrayList<CustomerDtoJaxb>();
    }

	public CustomerDtoJaxbList(List<CustomerDtoJaxb> customer) {
        this.customer = customer;
    }

	public List<CustomerDtoJaxb> geCustomers() {
		return customer;
	}

	public void setCustomers(List<CustomerDtoJaxb> customer) {
		this.customer = customer;
	}

}
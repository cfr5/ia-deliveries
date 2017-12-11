package es.udc.rs.deliveries.jaxrs.util;

import java.util.ArrayList;
import java.util.List;

import es.udc.rs.deliveries.jaxrs.dto.customer.CustomerDtoJaxb;
import es.udc.rs.deliveries.jaxrs.dto.customer.CustomerDtoJaxbList;
import es.udc.rs.deliveries.model.customer.Customer;

public class CustomerToCustomerDtoJaxbConversor {

	public static CustomerDtoJaxb toCustomerDtoJaxb(Customer customer) {		
		return new CustomerDtoJaxb(customer.getCustomerId(), customer.getName(), customer.getCif(), customer.getAddress(), customer.getCreationDate());
	}

	public static CustomerDtoJaxbList toCustomerDtoJaxbList(List<Customer> customers) {
		
		List<CustomerDtoJaxb> customerDtoList = new ArrayList<>();
		
		for (Customer customer :customers){
			customerDtoList.add(toCustomerDtoJaxb(customer));
		}
		return new CustomerDtoJaxbList(customerDtoList);
	}

}


package es.udc.rs.deliveries.jaxrs.dto.customer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CustomerWithShipmentsException")
@XmlType(name = "customerWithShipmentsExceptionType")
public class CustomerWithShipmentsExceptionDtoJaxb {

	@XmlElement(required = true)
	private Long customerId;

	public CustomerWithShipmentsExceptionDtoJaxb() {
	}

	public CustomerWithShipmentsExceptionDtoJaxb(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setMessage(Long customerId) {
		this.customerId = customerId;
	}

}

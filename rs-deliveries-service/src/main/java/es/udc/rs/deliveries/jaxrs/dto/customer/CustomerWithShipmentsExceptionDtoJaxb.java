
package es.udc.rs.deliveries.jaxrs.dto.customer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CustomerWithShipmentsException")
@XmlType(name = "customerWithShipmentsExceptionType")
public class CustomerWithShipmentsExceptionDtoJaxb {

	@XmlElement(required = true)
	private String message;

	public CustomerWithShipmentsExceptionDtoJaxb() {
	}

	public CustomerWithShipmentsExceptionDtoJaxb(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

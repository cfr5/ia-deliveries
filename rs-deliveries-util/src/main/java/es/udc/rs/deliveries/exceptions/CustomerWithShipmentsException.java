package es.udc.rs.deliveries.exceptions;

@SuppressWarnings("serial")
public class CustomerWithShipmentsException extends Exception {

	private Long customerId;

	public CustomerWithShipmentsException(Long customerId) {
		super("The customer '" + customerId + "' has shipments'");
		this.customerId = customerId;
	}

	public Long getCustomerId() {
		return customerId;
	}

}

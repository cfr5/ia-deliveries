package es.udc.rs.deliveries.exceptions;

@SuppressWarnings("serial")
public class CustomerWithShipmentsException extends Exception{
	
	public CustomerWithShipmentsException(Long customerId){
		super("The customer '" + customerId + "' has shipments'");		
	}

}

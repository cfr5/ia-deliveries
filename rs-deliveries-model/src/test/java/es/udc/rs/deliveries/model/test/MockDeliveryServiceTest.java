package es.udc.rs.deliveries.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
import es.udc.rs.deliveries.model.customer.Customer;
import es.udc.rs.deliveries.model.deliveryservice.DeliveryService;
import es.udc.rs.deliveries.model.deliveryservice.DeliveryServiceFactory;
import es.udc.rs.deliveries.model.deliveryservice.MockDeliveryService;

public class MockDeliveryServiceTest {

	@Test
	public void testAddAndFindCustormer(){
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		
		try {
			Customer customer = deliveryService.addCustomer(name, cif, address);
			Customer customerfound = deliveryService.findCustomerById(customer.getCustomerId());
			assertEquals(customer,customerfound);
			
		} catch (InputValidationException | InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

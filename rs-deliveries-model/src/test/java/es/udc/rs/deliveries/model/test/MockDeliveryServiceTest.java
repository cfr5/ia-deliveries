package es.udc.rs.deliveries.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import es.udc.rs.deliveries.exceptions.CustomerWithShipmentsException;
import es.udc.rs.deliveries.exceptions.InputValidationException;
import es.udc.rs.deliveries.exceptions.InstanceNotFoundException;
import es.udc.rs.deliveries.exceptions.InvalidStateException;
import es.udc.rs.deliveries.exceptions.ShipmentNotPendingException;
import es.udc.rs.deliveries.model.customer.Customer;
import es.udc.rs.deliveries.model.deliveryservice.DeliveryService;
import es.udc.rs.deliveries.model.deliveryservice.DeliveryServiceFactory;
import es.udc.rs.deliveries.model.shipment.Shipment;
import es.udc.rs.deliveries.model.shipment.ShipmentState;

public class MockDeliveryServiceTest {

	@Test(expected = InputValidationException.class)
	public void testAddCustomerWithInvalidName() throws InputValidationException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer(null, "CIF", "address");

	}

	@Test(expected = InputValidationException.class)
	public void testAddCustomerWithEmptyName() throws InputValidationException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer("", "CIF", "address");

	}

	@Test(expected = InputValidationException.class)
	public void testAddCustomerWithInvalidCIF() throws InputValidationException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer("Name", null, "address");

	}

	@Test(expected = InputValidationException.class)
	public void testAddCustomerWithEmptyCIF() throws InputValidationException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer("Name", "", "address");

	}

	@Test(expected = InputValidationException.class)
	public void testAddCustomerWithInvalidAddress() throws InputValidationException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer("Name", "CIF", null);

	}

	@Test(expected = InputValidationException.class)
	public void testAddCustomerWithEmptyAddress() throws InputValidationException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer("Name", "CIF", "");

	}

	@Test
	public void testAddValidCustomer()
			throws InputValidationException, InstanceNotFoundException, CustomerWithShipmentsException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer("Name", "CIF", "address");
		deliveryService.removeCustomer(customer.getCustomerId());
	}

	
	
	@Test (expected = InstanceNotFoundException.class)
	public void testCustomerNotFound() throws InstanceNotFoundException, InputValidationException{
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		deliveryService.findCustomerById(999999l);
	}

	@Test
	public void testAddAndFindCustormer()
			throws InputValidationException, InstanceNotFoundException, CustomerWithShipmentsException {
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer(name, cif, address);
		Customer customerfound = deliveryService.findCustomerById(customer.getCustomerId());
		assertEquals(customer, customerfound);
		deliveryService.removeCustomer(customer.getCustomerId());
	}
	
	@Test (expected = InputValidationException.class)
	public void testFindNullCustomer() throws InstanceNotFoundException, InputValidationException{
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		deliveryService.findCustomerById(null);
	}
	
	@Test
	public void testFindCustomerByKeyword()
			throws InputValidationException, InstanceNotFoundException, CustomerWithShipmentsException {
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer(name, cif, address);
		List<Customer> customersfound = deliveryService.findCustomersByName("coyote");
		List<Customer> customersnotfound = deliveryService.findCustomersByName("correcaminos");
		assertTrue(customersfound.contains(customer));
		assertTrue(customersnotfound.isEmpty());
		deliveryService.removeCustomer(customer.getCustomerId());

	}
	
	@Test
	public void testCustomerNotFoundByKeywords() throws InputValidationException{
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		List<Customer> listaresultados = deliveryService.findCustomersByName("NombrePrueba");
		assertTrue(listaresultados.isEmpty());
	}
	
	@Test (expected = InputValidationException.class)
	public void testFindNullCustomerByKeywords() throws InputValidationException{
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		deliveryService.findCustomersByName(null);
	}
	
	@Test (expected = InputValidationException.class)
	public void testFindEmtyCustomerByKeywords() throws InputValidationException{
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		deliveryService.findCustomersByName("");
	}
	
	@Test
	public void testUpdateCustomer() throws InputValidationException{
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer(name, cif, address);
	}

	@Test
	public void testAddAndFindShipment()
			throws InputValidationException, InstanceNotFoundException, CustomerWithShipmentsException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();

		Customer customer = deliveryService.addCustomer("Pepito", "00000000", "direccion");

		Shipment shipment = deliveryService.addShipment(customer.getCustomerId(), 10l, "address");

		assertEquals(shipment, deliveryService.findShipmentById(shipment.getShipmentId()));

		deliveryService.clearMaps();
	}

	@Test(expected = InputValidationException.class)
	public void testAddShipmentWithInvalidCustomerId() throws InputValidationException, InstanceNotFoundException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();

		deliveryService.addShipment(-1l, 10l, "address");
	}

	@Test(expected = InputValidationException.class)
	public void testAddShipmentWithInvalidPackageReference()
			throws InputValidationException, InstanceNotFoundException, CustomerWithShipmentsException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();

		Customer customer = deliveryService.addCustomer("Pepito", "00000000", "direccion");

		deliveryService.addShipment(customer.getCustomerId(), -10l, "address");

		deliveryService.clearMaps();

	}

	@Test(expected = InputValidationException.class)
	public void testAddShipmentWithInvalidAddress()
			throws InputValidationException, InstanceNotFoundException, CustomerWithShipmentsException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();

		Customer customer = deliveryService.addCustomer("Pepito", "00000000", "direccion");

		deliveryService.addShipment(customer.getCustomerId(), 10l, null);

		deliveryService.clearMaps();
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testAddShipmentWithoutCustomer() throws InputValidationException, InstanceNotFoundException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();

		deliveryService.addShipment(0l, 10l, "direccion");

		deliveryService.clearShipmentsMap();
	}

	private Shipment addValidShipment() throws InputValidationException, InstanceNotFoundException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer("Pepito", "00000000", "direccion");
		return deliveryService.addShipment(customer.getCustomerId(), 10l, "address");
	}

	@Test(expected = ShipmentNotPendingException.class)
	public void testCancelShipment() throws InputValidationException, InstanceNotFoundException,
			ShipmentNotPendingException, CustomerWithShipmentsException {
		Shipment shipment = addValidShipment();

		assertEquals(shipment.getState(), ShipmentState.PENDING);

		DeliveryService deliveryService = DeliveryServiceFactory.getService();

		deliveryService.cancelShipment(shipment.getShipmentId());

		shipment = deliveryService.findShipmentById(shipment.getShipmentId());

		assertEquals(shipment.getState(), ShipmentState.CANCELLED);

		deliveryService.cancelShipment(shipment.getShipmentId());

		deliveryService.clearMaps();
	}

	@Test(expected = InputValidationException.class)
	public void testCancelInvalidShipment()
			throws InputValidationException, InstanceNotFoundException, ShipmentNotPendingException {
		DeliveryServiceFactory.getService().cancelShipment(-1l);
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testCancelNotFoundShipment()
			throws InputValidationException, InstanceNotFoundException, ShipmentNotPendingException {
		DeliveryServiceFactory.getService().cancelShipment(99l);
	}

	@Test
	public void testUpdateShipment() throws InputValidationException, InstanceNotFoundException, InvalidStateException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();

		Customer customer = deliveryService.addCustomer("customer", "cif", "address");

		//Ciclo de vida de un envio
		Shipment shipment1 = deliveryService.addShipment(customer.getCustomerId(), 1l, "address");

		assertEquals(shipment1.getState(), ShipmentState.PENDING);

		deliveryService.updateShipmentState(shipment1.getShipmentId(), ShipmentState.SENT);
		
		assertEquals(shipment1.getState(), ShipmentState.SENT);
		
		deliveryService.updateShipmentState(shipment1.getShipmentId(), ShipmentState.DELIVERED);
		
		assertEquals(shipment1.getState(), ShipmentState.DELIVERED);

		//Cancelara un envio antes de enviado
		Shipment shipment2 = deliveryService.addShipment(customer.getCustomerId(), 1l, "address");
		
		deliveryService.updateShipmentState(shipment2.getShipmentId(), ShipmentState.CANCELLED);

		assertEquals(shipment2.getState(), ShipmentState.CANCELLED);
		
		//Rechazar un envio
		Shipment shipment3 = deliveryService.addShipment(customer.getCustomerId(), 1l, "address");
		
		deliveryService.updateShipmentState(shipment3.getShipmentId(), ShipmentState.SENT);

		deliveryService.updateShipmentState(shipment3.getShipmentId(), ShipmentState.REJECTED);
		
		assertEquals(shipment3.getState(), ShipmentState.REJECTED);
		
		deliveryService.clearMaps();
	}

	@Test
	public void findShipmentsByCustomerId() throws InputValidationException, InstanceNotFoundException {
		DeliveryService service = DeliveryServiceFactory.getService();
		Customer customer = service.addCustomer("asdas", "cif1", "address1");
		Shipment shipment = DeliveryServiceFactory.getService().addShipment(customer.getCustomerId(), 123l,
				"address123");

		List<Shipment> shipmnetsFound = DeliveryServiceFactory.getService()
				.findShipmentsByCustomer(customer.getCustomerId(), 0l, 0l);

		assertTrue(shipmnetsFound.size() == 1);

		service.clearMaps();
	}

}

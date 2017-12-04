package es.udc.rs.deliveries.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import es.udc.rs.deliveries.model.deliveryservice.MockDeliveryService;
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
	public void testAddValidCustomer() throws InputValidationException, InstanceNotFoundException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer("Name", "CIF", "address");
		((MockDeliveryService) deliveryService).clearCustomersMap();
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testCustomerNotFound() throws InstanceNotFoundException, InputValidationException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		deliveryService.findCustomerById(999999l);
	}

	@Test
	public void testAddAndFindCustormer() throws InputValidationException, InstanceNotFoundException {
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer(name, cif, address);
		Customer customerfound = deliveryService.findCustomerById(customer.getCustomerId());
		assertEquals(customer, customerfound);
		((MockDeliveryService) deliveryService).clearCustomersMap();
	}

	@Test(expected = InputValidationException.class)
	public void testRemoveCustomerInvalidId()
			throws InstanceNotFoundException, InputValidationException, CustomerWithShipmentsException {
		DeliveryService service = DeliveryServiceFactory.getService();
		service.removeCustomer(-1l);
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testRemoveNonExistentCustomer()
			throws InstanceNotFoundException, InputValidationException, CustomerWithShipmentsException {
		DeliveryService service = DeliveryServiceFactory.getService();
		service.removeCustomer(0l);
	}

	@Test(expected = InputValidationException.class)
	public void testRemoveNullCustomer()
			throws InstanceNotFoundException, InputValidationException, CustomerWithShipmentsException {
		DeliveryService service = DeliveryServiceFactory.getService();
		service.removeCustomer(null);
	}

	@Test
	public void testRemoveCustomer()
			throws InputValidationException, InstanceNotFoundException, CustomerWithShipmentsException {
		DeliveryService service = DeliveryServiceFactory.getService();
		Customer customer = service.addCustomer("Customer", "123456789A", "address");
		service.removeCustomer(customer.getCustomerId());

		try {
			service.findCustomerById(customer.getCustomerId());
		} catch (InstanceNotFoundException e) {
			assertTrue(true);
		} finally {
			((MockDeliveryService) service).clearMaps();
		}
	}

	@Test
	public void testRemoveCustomerWithShipments() throws InputValidationException, InstanceNotFoundException {
		DeliveryService service = DeliveryServiceFactory.getService();
		Customer customer = service.addCustomer("Customer", "123456789A", "address");
		service.addShipment(customer.getCustomerId(), 1234l, customer.getAddress());

		try {
			service.removeCustomer(customer.getCustomerId());
		} catch (CustomerWithShipmentsException e) {
			assertTrue(true);
		} finally {
			((MockDeliveryService) service).clearCustomersMap();
		}
	}

	@Test(expected = InputValidationException.class)
	public void testFindNullCustomer() throws InstanceNotFoundException, InputValidationException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		deliveryService.findCustomerById(null);
	}

	@Test
	public void testFindCustomerByKeyword() throws InputValidationException, InstanceNotFoundException {
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer(name, cif, address);
		Customer customer1 = deliveryService.addCustomer("COYOTE 1", "cif1", "address1");
		Customer customer2 = deliveryService.addCustomer("coyote2", "cif2", "address2");
		List<Customer> customersfound = deliveryService.findCustomersByName("coyote");
		assertTrue(customersfound.size() == 3);
		assertTrue(customersfound.contains(customer));
		assertTrue(customersfound.contains(customer1));
		assertTrue(customersfound.contains(customer2));
		((MockDeliveryService) deliveryService).clearCustomersMap();
	}

	@Test
	public void testCustomerNotFoundByKeywords() throws InputValidationException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		List<Customer> listaresultados = deliveryService.findCustomersByName("NombrePrueba");
		assertTrue(listaresultados.isEmpty());
	}

	@Test(expected = InputValidationException.class)
	public void testFindNullCustomerByKeywords() throws InputValidationException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		deliveryService.findCustomersByName(null);
	}

	@Test(expected = InputValidationException.class)
	public void testFindEmtyCustomerByKeywords() throws InputValidationException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		deliveryService.findCustomersByName("");
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateNullCustomer() throws InputValidationException, InstanceNotFoundException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		deliveryService.updateCustomer(null, "Jesus", "cif", "address");
	}

	@Test
	public void testUpdateCustomerWithNullName() throws InputValidationException, InstanceNotFoundException {
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer;
		customer = deliveryService.addCustomer(name, cif, address);
		try {
			deliveryService.updateCustomer(customer.getCustomerId(), null, "CIF", "address");
		} catch (InputValidationException e) {
			assertTrue(true);
		} finally {
			((MockDeliveryService) deliveryService).clearCustomersMap();
		}
	}

	@Test
	public void testUpdateCustomerWithEmptyName() throws InputValidationException, InstanceNotFoundException {
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer(name, cif, address);
		try {
			deliveryService.updateCustomer(customer.getCustomerId(), "", "NEWcif", "NEWaddress");
		} catch (InputValidationException e) {
			assertTrue(true);
		} finally {
			((MockDeliveryService) deliveryService).clearCustomersMap();
		}
	}

	@Test
	public void testUpdateCustomerWithNullCIF() throws InputValidationException, InstanceNotFoundException {
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer(name, cif, address);

		try {
			deliveryService.updateCustomer(customer.getCustomerId(), "NEWNAME", null, "NEWaddress");
		} catch (InputValidationException e) {
			assertTrue(true);
		} finally {
			((MockDeliveryService) deliveryService).clearCustomersMap();
		}
	}

	@Test
	public void testUpdateCustomerWithEmptyCIF() throws InputValidationException, InstanceNotFoundException {
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer(name, cif, address);
		try {
			deliveryService.updateCustomer(customer.getCustomerId(), "NEWNAME", "", "NEWaddress");
		} catch (InputValidationException e) {
			assertTrue(true);
		} finally {
			((MockDeliveryService) deliveryService).clearCustomersMap();
		}
	}

	@Test
	public void testUpdateCustomerWithNullAddress() throws InputValidationException, InstanceNotFoundException {
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer(name, cif, address);

		try {
			deliveryService.updateCustomer(customer.getCustomerId(), "NEWNAME", "NEWCIF", null);
		} catch (InputValidationException e) {
			assertTrue(true);
		} finally {
			((MockDeliveryService) deliveryService).clearCustomersMap();
		}
	}

	@Test
	public void testUpdateCustomerWithEmptyAddress() throws InputValidationException, InstanceNotFoundException {
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer(name, cif, address);

		try {
			deliveryService.updateCustomer(customer.getCustomerId(), "NEWNAME", "NEWCIF", "");
		} catch (InputValidationException e) {
			assertTrue(true);
		} finally {
			((MockDeliveryService) deliveryService).clearCustomersMap();
		}
	}

	@Test
	public void testUpdateNonExistantCustomer() throws InputValidationException, InstanceNotFoundException {
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer(name, cif, address);
		try {
			deliveryService.updateCustomer(12312l, "NEWNAME", "NEWCIF", "NEWaddress");
		} catch (InstanceNotFoundException e) {
			assertTrue(true);
		} finally {
			((MockDeliveryService) deliveryService).clearCustomersMap();
		}
	}

	@Test
	public void testUpdateExistantCustomer() throws InputValidationException, InstanceNotFoundException {
		String name = "Coyote";
		String cif = "CIFCoyote";
		String address = "Canyon Colorado";
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer(name, cif, address);
		deliveryService.updateCustomer(customer.getCustomerId(), "NEWNAME", "NEWCIF", "NEWaddress");
		assertEquals("NEWNAME", customer.getName());
		assertEquals("NEWCIF", customer.getCif());
		assertEquals("NEWaddress", customer.getAddress());
		((MockDeliveryService) deliveryService).clearCustomersMap();
	}

	@Test
	public void testAddAndFindShipment()
			throws InputValidationException, InstanceNotFoundException, CustomerWithShipmentsException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();

		Customer customer = deliveryService.addCustomer("Pepito", "00000000", "direccion");

		Shipment shipment = deliveryService.addShipment(customer.getCustomerId(), 10l, "address");

		assertEquals(shipment, deliveryService.findShipmentById(shipment.getShipmentId()));

		((MockDeliveryService) deliveryService).clearMaps();
	}

	@Test(expected = InputValidationException.class)
	public void testFindShipmnetByNullId() throws InputValidationException, InstanceNotFoundException {
		DeliveryServiceFactory.getService().findShipmentById(null);
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testFindUnexistentShipment() throws InputValidationException, InstanceNotFoundException {
		DeliveryServiceFactory.getService().findShipmentById(100l);
	}
	
	@Test(expected = InputValidationException.class)
	public void testAddShipmentWithInvalidCustomerId() throws InputValidationException, InstanceNotFoundException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();

		deliveryService.addShipment(-1l, 10l, "address");
	}

	@Test(expected = InputValidationException.class)
	public void testAddShipmentWithNullId() throws InputValidationException, InstanceNotFoundException {
		DeliveryServiceFactory.getService().addShipment(null, 0l, "address");
	}

	@Test(expected = InputValidationException.class)
	public void testAddShipmentWithNullPackageReference() throws InputValidationException, InstanceNotFoundException {
		DeliveryServiceFactory.getService().addShipment(1l, null, "address");
	}

	@Test(expected = InputValidationException.class)
	public void testAddShipmentWithNullAddress() throws InputValidationException, InstanceNotFoundException {
		DeliveryServiceFactory.getService().addShipment(1l, 0l, null);
	}

	@Test(expected = InputValidationException.class)
	public void testAddShipmentWithEmptyAddress() throws InputValidationException, InstanceNotFoundException {
		DeliveryServiceFactory.getService().addShipment(1l, 0l, "");
	}

	@Test
	public void testAddShipmentWithInvalidPackageReference()
			throws InputValidationException, InstanceNotFoundException, CustomerWithShipmentsException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();

		Customer customer = deliveryService.addCustomer("Pepito", "00000000", "direccion");

		try {
			deliveryService.addShipment(customer.getCustomerId(), -10l, "address");
		} catch (InputValidationException e) {
			assertTrue(true);
		} finally {
			((MockDeliveryService) deliveryService).clearMaps();
		}

	}

	@Test
	public void testAddShipmentWithInvalidAddress()
			throws InputValidationException, InstanceNotFoundException, CustomerWithShipmentsException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();

		Customer customer = deliveryService.addCustomer("Pepito", "00000000", "direccion");

		try {
			deliveryService.addShipment(customer.getCustomerId(), 10l, null);
		} catch (InputValidationException e) {
			assertTrue(true);
		} finally {
			((MockDeliveryService) deliveryService).clearMaps();
		}

	}

	@Test
	public void testAddShipmentWithoutCustomer() throws InputValidationException, InstanceNotFoundException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();

		try {
			deliveryService.addShipment(0l, 10l, "direccion");
		} catch (InstanceNotFoundException e) {
			assertTrue(true);
		} finally {
			((MockDeliveryService) deliveryService).clearShipmentsMap();
		}
	}

	private Shipment addValidShipment() throws InputValidationException, InstanceNotFoundException {
		DeliveryService deliveryService = DeliveryServiceFactory.getService();
		Customer customer = deliveryService.addCustomer("Pepito", "00000000", "direccion");
		return deliveryService.addShipment(customer.getCustomerId(), 10l, "address");
	}

	@Test
	public void testCancelShipment() throws InputValidationException, InstanceNotFoundException,
			ShipmentNotPendingException, CustomerWithShipmentsException {
		Shipment shipment = addValidShipment();

		assertEquals(shipment.getState(), ShipmentState.PENDING);

		DeliveryService deliveryService = DeliveryServiceFactory.getService();

		deliveryService.cancelShipment(shipment.getShipmentId());

		shipment = deliveryService.findShipmentById(shipment.getShipmentId());

		assertEquals(shipment.getState(), ShipmentState.CANCELLED);

		try {
			deliveryService.cancelShipment(shipment.getShipmentId());
		} catch (ShipmentNotPendingException e) {
			assertTrue(true);
		} finally {
			((MockDeliveryService) deliveryService).clearMaps();
		}

	}

	@Test(expected = InputValidationException.class)
	public void testCancelShipmentWithNullId()
			throws InputValidationException, InstanceNotFoundException, ShipmentNotPendingException {
		DeliveryServiceFactory.getService().cancelShipment(null);
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

		// Ciclo de vida de un envio
		Shipment shipment1 = deliveryService.addShipment(customer.getCustomerId(), 1l, "address");

		assertEquals(shipment1.getState(), ShipmentState.PENDING);

		deliveryService.updateShipmentState(shipment1.getShipmentId(), ShipmentState.SENT);

		assertEquals(shipment1.getState(), ShipmentState.SENT);

		deliveryService.updateShipmentState(shipment1.getShipmentId(), ShipmentState.DELIVERED);

		assertEquals(shipment1.getState(), ShipmentState.DELIVERED);

		// Cancelara un envio antes de enviado
		Shipment shipment2 = deliveryService.addShipment(customer.getCustomerId(), 1l, "address");

		deliveryService.updateShipmentState(shipment2.getShipmentId(), ShipmentState.CANCELLED);

		assertEquals(shipment2.getState(), ShipmentState.CANCELLED);

		// Rechazar un envio
		Shipment shipment3 = deliveryService.addShipment(customer.getCustomerId(), 1l, "address");

		deliveryService.updateShipmentState(shipment3.getShipmentId(), ShipmentState.SENT);

		deliveryService.updateShipmentState(shipment3.getShipmentId(), ShipmentState.REJECTED);

		assertEquals(shipment3.getState(), ShipmentState.REJECTED);

		((MockDeliveryService) deliveryService).clearMaps();
	}

	@Test(expected = InputValidationException.class)
	public void testFindShipmentsByCustomerIdInvalidId() throws InputValidationException {
		DeliveryServiceFactory.getService().findShipmentsByCustomer(-1l, 0l, 0l);
	}

	@Test(expected = InputValidationException.class)
	public void testFindShipmentsByCustomerIdInvalidStart() throws InputValidationException {
		DeliveryServiceFactory.getService().findShipmentsByCustomer(0l, -1l, 0l);
	}

	@Test(expected = InputValidationException.class)
	public void testFindShipmentsByCustomerIdInvalidCount() throws InputValidationException {
		DeliveryServiceFactory.getService().findShipmentsByCustomer(0l, 0l, 0l);
	}

	@Test(expected = InputValidationException.class)
	public void testFindShipmentsByCustomerIdNullId() throws InputValidationException {
		DeliveryServiceFactory.getService().findShipmentsByCustomer(null, 0l, 0l);
	}

	@Test(expected = InputValidationException.class)
	public void testFindShipmentsByCustomerIdNullStart() throws InputValidationException {
		DeliveryServiceFactory.getService().findShipmentsByCustomer(0l, null, 0l);
	}

	@Test(expected = InputValidationException.class)
	public void testFindShipmentsByCustomerIdNullCount() throws InputValidationException {
		DeliveryServiceFactory.getService().findShipmentsByCustomer(0l, 0l, null);
	}

	@Test
	public void findShipmentsByCustomerIdWithoutShipments() throws InputValidationException {
		Customer customer = DeliveryServiceFactory.getService().addCustomer("customer", "cif1", "address1");
		List<Shipment> shipments = DeliveryServiceFactory.getService().findShipmentsByCustomer(customer.getCustomerId(),
				0l, 1l);
		assertTrue(shipments.isEmpty());

	}

	@Test
	public void findShipmentsByCustomerId() throws InstanceNotFoundException, InputValidationException {
		DeliveryService service = DeliveryServiceFactory.getService();
		Customer customer = service.addCustomer("customer1", "cif1", "address1");
		Shipment shipment = service.addShipment(customer.getCustomerId(), 123l, "address123");
		Shipment shipment1 = service.addShipment(customer.getCustomerId(), 234l, "address123");
		Shipment shipment2 = service.addShipment(customer.getCustomerId(), 345l, "address123");

		List<Shipment> shipmnetsFound;

		shipmnetsFound = service.findShipmentsByCustomer(customer.getCustomerId(), 0l, 1l);

		assertTrue(shipmnetsFound.size() == 1);
		assertEquals(shipment, shipmnetsFound.get(0));

		// Intentamos buscar mas shipments de los que tiene el cliente
		shipmnetsFound = service.findShipmentsByCustomer(customer.getCustomerId(), 0l, 4l);

		assertTrue(shipmnetsFound.size() == 3);
		assertEquals(shipment, shipmnetsFound.get(0));

		// Intentamos buscar con un offset mayor al numero de shipments del
		// cliente
		shipmnetsFound = service.findShipmentsByCustomer(customer.getCustomerId(), 5l, 3l);

		assertTrue(shipmnetsFound.isEmpty());

		// Intentamos buscar mas shipments de los que tiene el cliente con
		// offset mayor que 0 y menor que el numero de shipments

		shipmnetsFound = service.findShipmentsByCustomer(customer.getCustomerId(), 1l, 4l);

		assertTrue(shipmnetsFound.size() == 2);
		assertTrue(shipmnetsFound.contains(shipment1));
		assertTrue(shipmnetsFound.contains(shipment2));
		assertFalse(shipmnetsFound.contains(shipment));

		Shipment shipment3 = service.addShipment(customer.getCustomerId(), 456l, "address123");
		Shipment shipment4 = service.addShipment(customer.getCustomerId(), 567l, "address123");

		// Intentamos buscar con offset mayor que 0 y menor que el tamaño
		shipmnetsFound = service.findShipmentsByCustomer(customer.getCustomerId(), 3l, 1l);

		assertTrue(shipmnetsFound.size() == 1);
		assertTrue(shipmnetsFound.contains(shipment3));
		assertFalse(shipmnetsFound.contains(shipment4));

		((MockDeliveryService) service).clearMaps();

	}

}

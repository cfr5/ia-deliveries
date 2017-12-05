package es.udc.rs.deliveries.client.ui;

import java.util.List;

import es.udc.rs.deliveries.client.service.ClientDeliveryService;
import es.udc.rs.deliveries.client.service.ClientDeliveryServiceFactory;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentDto;
import es.udc.rs.deliveries.client.service.rest.dto.shipment.ClientShipmentStateDto;

public class DeliveryServiceClient {

	// - Creación de un nuevo cliente.
	// - Eliminación de un cliente.
	// - Búsqueda de envíos de un cliente.
	// - Cambiar el estado de un envío.

	public static void main(String[] args) {

		if (args.length == 0) {
			printUsageAndExit();
		}
		ClientDeliveryService clientDeliveryService = ClientDeliveryServiceFactory.getService();
		if ("-addCustomer".equalsIgnoreCase(args[0])) {
			validateArgs(args, 3, new int[] {});

			// [-addCustomer] DeliveryServiceClient -addCustomer <name> <Cif> <address>

			try {
				Long customerId = clientDeliveryService.addCustomer(args[1], args[2], args[3]);
				System.out.println("Customer " + customerId + " " + "created sucessfully");
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-findByCustomer".equalsIgnoreCase(args[0])) {
			validateArgs(args, 2, new int[] { 1 });

			// [-findByCustomer] ClientDeliveryServiceClient -findByCustomer <customerId> <start> <amount>

			try {
				List<ClientShipmentDto> shipments = clientDeliveryService.findByCustomer(Long.parseLong(args[1]),
						Long.parseLong(args[2]), Long.parseLong(args[3]));
				for (ClientShipmentDto clientShipmentDto : shipments) {
					System.out.println(clientShipmentDto.toString());
				}
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-deleteCustomer".equalsIgnoreCase(args[0])) {
			validateArgs(args, 1, new int[] { 1 });

			// [-deleteCustomer] ClientDeliveryServiceClient -deleteCustomer <customerId>

			try {
				clientDeliveryService.deleteCustomer(Long.parseLong(args[1]));
				System.out.println("Customer " + args[1] + " deleted sucessfully");

			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-changeState".equalsIgnoreCase(args[0])) {
			validateArgs(args, 2, new int[] { 1 });

			// [-changeState] ClientDeliveryServiceClient -changeState <shipmentId1> <newstate>

			try {
				clientDeliveryService.changeState(Long.parseLong(args[1]),ClientShipmentStateDto.valueOf(args[2]));
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		}
	}

	public static void validateArgs(String[] args, int expectedArgs, int[] numericArguments) {
		if (expectedArgs != args.length) {
			printUsageAndExit();
		}
		for (int i = 0; i < numericArguments.length; i++) {
			int position = numericArguments[i];
			try {
				Double.parseDouble(args[position]);
			} catch (NumberFormatException n) {
				printUsageAndExit();
			}
		}
	}

	public static void printUsageAndExit() {
		printUsage();
		System.exit(-1);
	}

	public static void printUsage() {
		System.err.println("Usage:\n" + "    [-addCustomer]    DeliveryServiceClient -addCustomer <name> ...\n"
				+ "    [-findCustomer]   DeliveryServiceClient -findCustomer <customerId>\n" + "    ...\n");
	}

}

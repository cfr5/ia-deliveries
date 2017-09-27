package es.udc.rs.deliveries.client.ui;

import es.udc.rs.deliveries.client.service.ClientDeliveryService;
import es.udc.rs.deliveries.client.service.ClientDeliveryServiceFactory;

public class DeliveryServiceClient {

	public static void main(String[] args) {

		if (args.length == 0) {
			printUsageAndExit();
		}
		ClientDeliveryService clientDeliveryService = ClientDeliveryServiceFactory.getService();
		if ("-addCustomer".equalsIgnoreCase(args[0])) {
			validateArgs(args, 2, new int[] {});

			// [-addCustomer] DeliveryServiceClient -addCustomer <name> ...

			try {
				Long customerId = null; // Invoke method from the clientDeliveryService
				System.out.println("Customer " + customerId + " " + "created sucessfully");
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-findCustomer".equalsIgnoreCase(args[0])) {
			validateArgs(args, 2, new int[] { 1 });

			// [-findCustomer] ClientDeliveryServiceClient -findCustomer <customerId>

			try {
				// ...
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
		System.err.println(
				"Usage:\n" + "    [-addCustomer]    DeliveryServiceClient -addCustomer <name> ...\n" +
		                     "    [-findCustomer]   DeliveryServiceClient -findCustomer <customerId>\n" +
						     "    ...\n");
	}

}

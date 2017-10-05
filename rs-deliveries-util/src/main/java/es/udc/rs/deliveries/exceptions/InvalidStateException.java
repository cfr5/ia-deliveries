package es.udc.rs.deliveries.exceptions;

@SuppressWarnings("serial")
public class InvalidStateException extends Exception {

	public InvalidStateException(String currentState, String newState) {
		super("Can't change current shipments state '" + currentState + "' to '" + newState + "'");
	}
}

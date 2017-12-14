package es.udc.rs.deliveries.exceptions;

@SuppressWarnings("serial")
public class InvalidStateException extends Exception {

	private String currentState;
	private String newState;

	public InvalidStateException(String currentState, String newState) {
		super("Can't change current shipments state '" + currentState + "' to '" + newState + "'");
		this.currentState = currentState;
		this.newState = newState;
	}

	public String getCurrentState() {
		return currentState;
	}

	public String getNewState() {
		return newState;
	}

}

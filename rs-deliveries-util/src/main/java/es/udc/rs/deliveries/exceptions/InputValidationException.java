package es.udc.rs.deliveries.exceptions;

@SuppressWarnings("serial")
public class InputValidationException extends Exception {

    public InputValidationException(String message) {
        super(message);
    }
}

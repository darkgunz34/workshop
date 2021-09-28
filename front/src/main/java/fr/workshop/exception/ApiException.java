package fr.workshop.exception;

public class ApiException extends Exception{
    public ApiException(final String message) {
        super(message);
    }
}

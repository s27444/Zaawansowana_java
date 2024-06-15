package pl.pjwstk.demo.exception;

public class NoHandlerFoundException extends RuntimeException {
    public NoHandlerFoundException(String message) {
        super(message);
    }
}
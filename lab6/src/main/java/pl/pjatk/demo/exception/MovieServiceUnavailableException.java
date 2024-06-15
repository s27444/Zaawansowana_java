package pl.pjatk.demo.exception;

public class MovieServiceUnavailableException extends RuntimeException {
    public MovieServiceUnavailableException(String message) {
        super(message);
    }
}

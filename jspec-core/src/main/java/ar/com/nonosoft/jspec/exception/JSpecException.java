package ar.com.nonosoft.jspec.exception;

public class JSpecException extends RuntimeException {
	public JSpecException(String message) {
		super(message);
	}

	public JSpecException(String message, Exception e) {
		super(message, e);
	}
}

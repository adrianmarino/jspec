package ar.com.nonosoft.test.jspec.exception;

public class JSpecException extends RuntimeException {
	public JSpecException(String message) {
		super(message);
	}

	public JSpecException(String message, Exception e) {
		super(message, e);
	}
}

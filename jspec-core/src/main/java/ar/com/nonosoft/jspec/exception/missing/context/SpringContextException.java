package ar.com.nonosoft.jspec.exception.missing.context;

import ar.com.nonosoft.jspec.exception.JSpecException;

public class SpringContextException extends JSpecException {
    public SpringContextException(Exception exception) {
        super(exception.getMessage());
    }
}

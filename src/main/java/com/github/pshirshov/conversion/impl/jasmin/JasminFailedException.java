package com.github.pshirshov.conversion.impl.jasmin;

public class JasminFailedException extends RuntimeException {

    public JasminFailedException() {
        super();
    }


    public JasminFailedException(String message) {
        super(message);
    }

    public JasminFailedException(String message, Throwable cause) {
        super(message, cause);
    }


    public JasminFailedException(Throwable cause) {
        super(cause);
    }

    protected JasminFailedException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

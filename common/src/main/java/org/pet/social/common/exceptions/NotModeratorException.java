package org.pet.social.common.exceptions;

public class NotModeratorException extends Exception {
    public NotModeratorException() {
    }

    public NotModeratorException(String message) {
        super(message);
    }

    public NotModeratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotModeratorException(Throwable cause) {
        super(cause);
    }

    public NotModeratorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package org.pet.social.common.exceptions;

public class ShouldNotModerateException extends Exception {
    public ShouldNotModerateException() {
    }

    public ShouldNotModerateException(String message) {
        super(message);
    }

    public ShouldNotModerateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShouldNotModerateException(Throwable cause) {
        super(cause);
    }

    public ShouldNotModerateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

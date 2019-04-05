package org.pet.social.common.exceptions;

public class ProblemNotApprovedException extends Exception {
    public ProblemNotApprovedException() {
    }

    public ProblemNotApprovedException(String message) {
        super(message);
    }

    public ProblemNotApprovedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProblemNotApprovedException(Throwable cause) {
        super(cause);
    }

    public ProblemNotApprovedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

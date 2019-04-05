package org.pet.social.common.exceptions;

public class ProblemShouldNotApprove extends Exception {
    public ProblemShouldNotApprove() {
    }

    public ProblemShouldNotApprove(String message) {
        super(message);
    }

    public ProblemShouldNotApprove(String message, Throwable cause) {
        super(message, cause);
    }

    public ProblemShouldNotApprove(Throwable cause) {
        super(cause);
    }

    public ProblemShouldNotApprove(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

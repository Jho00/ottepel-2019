package org.pet.social.common.responses;

public class ErrorResponse extends Response {
    private static final int DEFAULT_CODE = 400;
    private static final String DEFAULT_MESSAGE = "Ошибка";

    private String error;

    public ErrorResponse(int code, String message) {
        this.error = message;
        this.code = code;
    }

    public ErrorResponse() {
        this(DEFAULT_CODE, DEFAULT_MESSAGE);
    }

    public ErrorResponse( int code) {
        this(code, DEFAULT_MESSAGE);
    }

    public String getError() {
        return error;
    }


}

package org.pet.social.common.responses;

public class ErrorResponse extends Response {
    public static final String DEFAULT_MESSAGE = "Ошибка";

    private String error;

    public ErrorResponse(int code, String message) {
        this.error = message;
        this.code = code;
    }

    public ErrorResponse() {
        this(ResponseCodes.DEFAULT_ERROR_CODE, DEFAULT_MESSAGE);
    }

    public ErrorResponse(int code) {
        this(code, DEFAULT_MESSAGE);
    }

    public ErrorResponse(String message) { this(ResponseCodes.DEFAULT_ERROR_CODE, message); }

    public String getError() {
        return error;
    }


}

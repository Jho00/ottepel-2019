package org.pet.social.common.responses;

public class SuccessResponse extends Response {
    public static final String DEFAULT_MESSAGE = "Успешно";

    private String message;
    private Object body;

    public SuccessResponse(Object object, int code, String message) {
        this.message = message;
        this.body = object;
        this.code = code;
    }

    public SuccessResponse(Object object) {
        this(object, ResponseCodes.DEFAULT_SUCCESS_CODE, DEFAULT_MESSAGE);
    }

    public SuccessResponse(Object object, int code) {
        this(object, code, DEFAULT_MESSAGE);
    }

    public String getMessage() {
        return message;
    }

    public Object getBody() {
        return body;
    }
}

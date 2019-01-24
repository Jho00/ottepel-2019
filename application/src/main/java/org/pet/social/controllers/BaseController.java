package org.pet.social.controllers;

import org.pet.social.common.responses.ErrorResponse;
import org.pet.social.common.responses.Response;
import org.pet.social.common.responses.SuccessResponse;

/**
 * Контроллер для наследование, на него реквесты не должны идти
 */

public class BaseController {

    protected Response error()  {
        return new ErrorResponse();
    }

    protected Response error(int code)  {
        return new ErrorResponse(code);
    }

    protected Response error(int code, String message)  {
        return new ErrorResponse(code,message);
    }

    protected Response success(Object object) {
        return new SuccessResponse(object);
    }

    protected Response success(Object object, int code) {
        return new SuccessResponse(object, code);
    }

    protected Response success(Object object, int code, String message) {
        return new SuccessResponse(object, code, message);
    }

}

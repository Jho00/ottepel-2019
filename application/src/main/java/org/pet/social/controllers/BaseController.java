package org.pet.social.controllers;

import org.pet.social.common.responses.ErrorResponse;
import org.pet.social.common.responses.Response;
import org.pet.social.common.responses.ResponseCodes;
import org.pet.social.common.responses.SuccessResponse;
import org.pet.social.utils.AuthUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * Контроллер для наследование, на него реквесты не должны идти
 */

public class BaseController {
    protected Response error(HttpServletResponse response)  {
        response.setStatus(400);
        return new ErrorResponse();
    }

    protected Response error(HttpServletResponse response, int code)  {
        response.setStatus(code);
        return new ErrorResponse(code);
    }

    protected Response error(HttpServletResponse response, int code, String message)  {
        response.setStatus(code);
        return new ErrorResponse(code,message);
    }

    protected Response success(HttpServletResponse response, Object object) {
        response.setStatus(200);
        return new SuccessResponse(object);
    }

    protected Response success(HttpServletResponse response, Object object, int code) {
        response.setStatus(code);
        return new SuccessResponse(object, code);
    }

    protected Response success(HttpServletResponse response, Object object, int code, String message) {
        response.setStatus(code);
        return new SuccessResponse(object, code, message);
    }

    protected Response unauthorized(HttpServletResponse response) {
        response.setStatus(ResponseCodes.AUTH_ERROR_CODE);
        return new ErrorResponse(ResponseCodes.AUTH_ERROR_CODE,"Unauthorized");
    }

}

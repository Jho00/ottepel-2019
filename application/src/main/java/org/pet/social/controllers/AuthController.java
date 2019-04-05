package org.pet.social.controllers;

import org.pet.social.BLL.contracts.UserControlInterface;
import org.pet.social.common.entity.User;
import org.pet.social.common.responses.ErrorResponse;
import org.pet.social.common.responses.Response;
import org.pet.social.common.responses.ResponseCodes;
import org.pet.social.common.responses.SuccessResponse;
import org.pet.social.viewmodels.LoginViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {
    @Autowired
    private UserControlInterface userControl;

    private static final String HTTP_AUTH_TOKEN_HEADER_NAME = "auth_token";

    public static final String AUTH_ERROR_MESSAGE = "Authentication failed";

    @GetMapping("/login")
    public ResponseEntity<Response> login(
            @RequestBody LoginViewModel loginViewModel,
            HttpServletResponse httpServletResponse) {
        Response response;

        if (httpServletResponse.containsHeader(HTTP_AUTH_TOKEN_HEADER_NAME)) {

            response = new SuccessResponse(
                    null,
                    ResponseCodes.AUTH_SUCCESS_CODE,
                    "User is already authorized");

            return ResponseEntity
                    .ok()
                    .body(response);
        }

        if (userControl.passwordValueEquals(loginViewModel.login, loginViewModel.password)) {
            response = new SuccessResponse(
                    null);

            return ResponseEntity
                    .ok()
                    .header(HTTP_AUTH_TOKEN_HEADER_NAME,
                            userControl.getToken(loginViewModel.login))
                    .body(response);
        } else {
            response = new ErrorResponse(ResponseCodes.AUTH_ERROR_CODE, AUTH_ERROR_MESSAGE);

            return ResponseEntity
                    .ok()
                    .body(response);
        }
    }
}

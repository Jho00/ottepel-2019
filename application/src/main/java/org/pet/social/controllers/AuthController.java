package org.pet.social.controllers;

import org.pet.social.BLL.contracts.UserControlInterface;
import org.pet.social.common.entity.User;
import org.pet.social.common.responses.ErrorResponse;
import org.pet.social.common.responses.Response;
import org.pet.social.common.responses.ResponseCodes;
import org.pet.social.common.responses.SuccessResponse;
import org.pet.social.viewmodels.LoginViewModel;
import org.pet.social.viewmodels.RegisterViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@RestController
public class AuthController extends BaseController {
    @Autowired
    private UserControlInterface userControl;

    private static final String HTTP_AUTH_TOKEN_HEADER_NAME = "auth_token";

    public static final String AUTH_ERROR_MESSAGE = "Authentication failed";

    @GetMapping("/auth/user")
    public Response getCurrentUser(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName() == HTTP_AUTH_TOKEN_HEADER_NAME) {
                    return new SuccessResponse(userControl.getUserByToken(cookie.getValue()));
                }
            }
        }

        return new ErrorResponse(ResponseCodes.AUTH_ERROR_CODE, "User is not logged in");
    }
    @CrossOrigin(origins="*")
    @PostMapping("/auth/login")
    public Response login(
            HttpServletResponse httpServletResponse,
            @RequestBody LoginViewModel loginViewModel) {
        Response response;

        if (httpServletResponse.containsHeader(HTTP_AUTH_TOKEN_HEADER_NAME)) {

            return success(httpServletResponse,
                    null,
                    ResponseCodes.AUTH_SUCCESS_CODE,
                    "User is already authorized");
        }

        if (userControl.passwordValueEquals(loginViewModel.email, loginViewModel.password)) {

            String token = userControl.getToken();
            Cookie cookie = new Cookie(HTTP_AUTH_TOKEN_HEADER_NAME, token);
            cookie.setMaxAge(3600);
            httpServletResponse.addCookie(cookie);
            userControl.setToken(loginViewModel.email, token);

            return success(httpServletResponse, null);
        } else {
            return error(httpServletResponse, ResponseCodes.AUTH_ERROR_CODE, AUTH_ERROR_MESSAGE);
        }
    }
    @CrossOrigin(origins="*")
    @PostMapping("/auth/register")
    public Response register(HttpServletResponse response, @RequestBody RegisterViewModel registerViewModel) {

        if (registerViewModel.password.length() < 8)
            return error(response,ResponseCodes.DEFAULT_ERROR_CODE,"Password is too short");

        if (!registerViewModel.password.equals(registerViewModel.passwordConfirmation))
            return error(response,ResponseCodes.DEFAULT_ERROR_CODE,"Password wasn`t confirmed");

        User user = new User();

        user.setFirstName(registerViewModel.firstName);
        user.setLastName(registerViewModel.lastName);
        user.setEmail(registerViewModel.email);
        user.setRegisterAt(new Timestamp(System.currentTimeMillis()));
        user.setSalt(registerViewModel.email.substring(0,2));
        user.setPasswordHash(userControl.getHash(registerViewModel.password, user.getSalt()));

        if (!userControl.validateUser(user))
            return error(response,ResponseCodes.DEFAULT_ERROR_CODE,"Register data is not valid");

        userControl.addUser(user);
        return success(response, user);
    }
}

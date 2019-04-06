package org.pet.social.controllers;

import org.pet.social.BLL.contracts.UserControlInterface;
import org.pet.social.common.consts.AuthConstHolder;
import org.pet.social.common.entity.User;
import org.pet.social.common.responses.Response;
import org.pet.social.common.responses.ResponseCodes;
import org.pet.social.utils.AuthUtils;
import org.pet.social.viewmodels.LoginViewModel;
import org.pet.social.viewmodels.RegisterViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@RestController
public class AuthController extends BaseController {
    @Autowired
    private UserControlInterface userControl;

    AuthUtils authUtils;

    @CrossOrigin(origins="*")
    @GetMapping("/auth/user")
    public Response getCurrentUser(HttpServletResponse response, HttpServletRequest request) {
        if(authUtils == null) authUtils = new AuthUtils(userControl);
        User user = authUtils.getCurrentUser(request);
        if (user != null) {
            return success(response, user);
        }

        return error(response, ResponseCodes.AUTH_ERROR_CODE, "User is not logged in");
    }

    @CrossOrigin(origins="*")
    @PostMapping("/auth/login")
    public Response login(
            HttpServletResponse httpServletResponse,
            HttpServletRequest httpServletRequest,
            @RequestBody LoginViewModel loginViewModel) {
        if(authUtils == null) authUtils = new AuthUtils(userControl);
        if (authUtils.isLogedIn(httpServletRequest)) {

            return success(httpServletResponse,
                    authUtils.getCurrentUser(httpServletRequest),
                    ResponseCodes.AUTH_SUCCESS_CODE,
                    "User is already authorized");
        }

        if (userControl.passwordValueEquals(loginViewModel.email, loginViewModel.password)) {

            String token = userControl.getToken();
            userControl.setToken(loginViewModel.email, token);

            return success(httpServletResponse, userControl.getUser());
        } else {
            return error(httpServletResponse, ResponseCodes.AUTH_ERROR_CODE, AuthConstHolder.AUTH_ERROR_MESSAGE);
        }

    }
    @CrossOrigin(origins="*")
    @PostMapping("/auth/register")
    public Response register(HttpServletResponse response, @RequestBody RegisterViewModel registerViewModel) {

        if (registerViewModel.password.length() < 8) {
            return error(response,ResponseCodes.DEFAULT_ERROR_CODE,"Пароль слишком короткий");
        }

        if (!registerViewModel.password.equals(registerViewModel.passwordConfirmation)) {
            return error(response,ResponseCodes.DEFAULT_ERROR_CODE,"Пароли не совпадают");
        }

        User user = new User();

        user.setFirstName(registerViewModel.firstName);
        user.setLastName(registerViewModel.lastName);
        user.setEmail(registerViewModel.email);
        user.setRegisterAt(new Timestamp(System.currentTimeMillis()));
        user.setSalt(registerViewModel.email.substring(0,2));
        user.setPasswordHash(userControl.getHash(registerViewModel.password, user.getSalt()));

        if (!userControl.validateUser(user)) {
            return error(response,ResponseCodes.DEFAULT_ERROR_CODE,"Неверные данные для регистрации");
        }

        userControl.addUser(user);
        return success(response, user);
    }
}

package org.pet.social.utils;

import org.pet.social.BLL.contracts.UserControlInterface;
import org.pet.social.common.consts.AuthConstHolder;
import org.pet.social.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class AuthUtils {
    @Autowired
    private static UserControlInterface userControl;

    public static boolean isLogedIn(HttpServletRequest request) {
        return request.getHeader(AuthConstHolder.HTTP_AUTH_TOKEN_HEADER_NAME) != null;
    }

    public static User getCurrentUser(HttpServletRequest request) {
        String token = request.getHeader(AuthConstHolder.HTTP_AUTH_TOKEN_HEADER_NAME);
        if (token == null) {
            return null;
        }

        return userControl.getUserByToken(token);
    }
}

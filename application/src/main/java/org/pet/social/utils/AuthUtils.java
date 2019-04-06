package org.pet.social.utils;

import org.pet.social.BLL.contracts.UserControlInterface;
import org.pet.social.common.consts.AuthConstHolder;
import org.pet.social.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

public class AuthUtils {
    private UserControlInterface userControl;

    public AuthUtils(UserControlInterface userControl){
        this.userControl = userControl;
    }

    public boolean isLogedIn(HttpServletRequest request) {
        return request.getHeader(AuthConstHolder.HTTP_AUTH_TOKEN_HEADER_NAME) != null;
    }

    public User getCurrentUser(HttpServletRequest request) {
        String token = request.getHeader(AuthConstHolder.HTTP_AUTH_TOKEN_HEADER_NAME);
        if (token == null) {
            return null;
        }
        if(userControl == null){
            return null;
        }
        return userControl.getUserByToken(token);
    }
}

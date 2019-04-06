package org.pet.social.utils;

import org.pet.social.BLL.contracts.UserControlInterface;
import org.pet.social.BLL.contracts.utils.AuthUtilsInterface;
import org.pet.social.BLL.implementation.UserControlService;
import org.pet.social.common.consts.AuthConstHolder;
import org.pet.social.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthUtilsService implements AuthUtilsInterface {
    @Autowired
    private UserControlInterface userControl;

    @Override
    public boolean isLogedIn(HttpServletRequest request) {
        return request.getHeader(AuthConstHolder.HTTP_AUTH_TOKEN_HEADER_NAME) != null;
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        String token = request.getHeader(AuthConstHolder.HTTP_AUTH_TOKEN_HEADER_NAME);
        if (token == null) {
            return null;
        }

        return userControl.getUserByToken(token);
    }
}

package org.pet.social.BLL.contracts.utils;

import org.pet.social.common.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface AuthUtilsInterface {
    public boolean isLogedIn(HttpServletRequest request);
    public User getCurrentUser(HttpServletRequest request);
}

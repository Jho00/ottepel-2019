package org.pet.social.BLL.contracts;

import org.pet.social.common.entity.User;

public interface UserControlInterface {
    public User getUserById(int id);
    public boolean passwordValueEquals(String login, String password);

    public String getToken(String login);
}

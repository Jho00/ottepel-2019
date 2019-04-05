package org.pet.social.BLL.contracts;

import org.pet.social.common.entity.User;

public interface UserControlInterface {
    public User getUserById(int id);
    public User getUserByToken(String token);
    public boolean passwordValueEquals(String email, String password);
    public void addUser(User user);

    public String getToken();
    public boolean validateUser(User user);
    public String getHash(String password, String salt);

    public void setToken(String email, String token);
}

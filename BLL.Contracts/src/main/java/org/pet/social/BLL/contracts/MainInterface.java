package org.pet.social.BLL.contracts;

import org.pet.social.common.entity.User;

public interface MainInterface {
    public String greeting();
    public Iterable<User> getAll();
    public void createUser(String name, String email);
}

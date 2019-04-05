package org.pet.social.BLL.implementation;

import org.pet.social.BLL.contracts.UserControlInterface;
import org.pet.social.DAL.contracts.UserInterface;
import org.pet.social.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserControlService implements UserControlInterface {
    @Autowired
    private UserInterface userInterface;

    @Override
    public User getUserById(int id) {
        return userInterface.findById(Integer.valueOf(id)).get();
    }

    @Override
    public boolean passwordValueEquals(String login, String password) {
        return false; //TODO: password hash comparasion
    }

    @Override
    public String getToken(String login) {
        return ""; //TODO: generate token for user with given login
    }
}

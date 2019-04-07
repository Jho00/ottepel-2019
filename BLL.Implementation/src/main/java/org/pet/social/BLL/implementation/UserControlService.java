package org.pet.social.BLL.implementation;

import org.pet.social.BLL.contracts.UserControlInterface;
import org.pet.social.DAL.contracts.UserInterface;
import org.pet.social.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserControlService implements UserControlInterface {
    @Autowired
    private UserInterface userInterface;

    private static User currentUser;

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    @Override
    public User getUserById(int id) {
        return userInterface.findById(Integer.valueOf(id)).get();
    }

    @Override
    public User getUserByToken(String token) {
        return userInterface.findByToken(token);
    }

    @Override
    public boolean passwordValueEquals(String email, String password) {
        List<User> users = userInterface.findByEmail(email);
        if (users.size() == 0) {
            return false;
        }

        User user = users.get(0);
        return ((password + user.getSalt()).hashCode() + "").equals(user.getPasswordHash());
    }

    public User getUser() {
        return currentUser;
    }

    @Override
    public void setUser(User user) {
        currentUser = user;
    }

    @Override
    public void addUser(User user) {
        userInterface.save(user);
    }

    @Override
    public String getToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean validateUser(User user) {
        Validator validator = validatorFactory.getValidator();

        return validator.validate(user).isEmpty();
    }

    @Override
    public String getHash(String password, String salt) {
        return (password + salt).hashCode() + "";
    }

    @Override
    public void setToken(String email, String token) {
        List<User> users = userInterface.findByEmail(email);
        if (users.size() == 0) {
            return;
        }

        User user = users.get(0);
        user.setToken(token);
        setUser(user);
        userInterface.save(user);
    }
}

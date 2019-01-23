package org.pet.social.BLL.implementation;

import org.pet.social.BLL.contracts.MainInterface;
import org.pet.social.DAL.contracts.UserInterface;
import social.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService implements MainInterface {
    @Autowired
    private UserInterface user;

    @Override
    public String greeting() {
        return "Hello from dependecity injection";
    }

    @Override
    public Iterable<User> getAll() {
        return  user.findAll();
    }

    @Override
    public void createUser(String name, String email) {
        User tmp = new User();
        tmp.setName(name);
        tmp.setEmail(email);
        user.save(tmp);
    }
}

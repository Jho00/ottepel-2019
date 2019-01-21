package org.pet.social.controllers;

import org.pet.social.BLL.contracts.MainInterface;
import org.pet.social.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @Autowired
    private MainInterface main;

    @RequestMapping("/")
    public String index() {
        return main.greeting();
    }


    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return main.getAll();
    }

    @GetMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {

        main.createUser(name, email);
        return "Saved";
    }

}

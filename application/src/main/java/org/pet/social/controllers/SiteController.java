package org.pet.social.controllers;

import org.pet.social.BLL.contracts.MainInterface;
import org.pet.social.common.entity.User;
import org.pet.social.common.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SiteController extends BaseController {
    @Autowired
    private MainInterface main;

    @RequestMapping("/")
    public @ResponseBody
    Response index() {
        return this.error(402);
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

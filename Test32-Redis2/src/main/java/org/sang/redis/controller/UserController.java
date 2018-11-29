package org.sang.redis.controller;


import org.sang.redis.model.User;
import org.sang.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/update")
    public User update() {
        User admin = new User(1, "admin", 33);
        userService.updateUser(admin);
        return admin;
    }

    @GetMapping("/query")
    public User query() {
        return userService.findOne(1);
    }


    @GetMapping("/delete")
    public void removeUser() {
        userService.deleteById(1);
    }
}
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

    @GetMapping("/finduser1")
    public User finduser1() {
        return userService.findUser("zhangsan");
    }

    @GetMapping("/finduser2")
    public User finduser2() {
        return userService.findUser2("lisi");
    }

    @GetMapping("/adduser2")
    public User adduser2() {
        return userService.save("lisi", 13);
    }

    @GetMapping("/delete")
    public void removeUser() {
        userService.removeUser("zhangsan");
    }
}
package org.sang.comtroller;


import org.sang.common.MapUtil;
import org.sang.model.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class HelloController {


    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/abc")
    @ResponseBody
    public List<Permission> getroles() {
        return MapUtil.findByAdminUserId(1);
    }
}

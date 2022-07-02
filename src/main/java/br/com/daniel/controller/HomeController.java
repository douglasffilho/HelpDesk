package br.com.daniel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
//    @Autowired
//    UserService userService;

    @GetMapping
//    @Unsecured
    public String home() {
//        this.userService.login("admin", "YWRtaW4=");
        return "index";
    }

};
package br.com.daniel.controller;

import br.com.daniel.annotations.Unsecured;
import br.com.daniel.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class GivingAccessHomeController {
    private final UserService userService;

    public GivingAccessHomeController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Unsecured
    public String home() {
        this.userService.login("admin", "YWRtaW4=");
        return "index";
    }

};
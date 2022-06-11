package br.com.daniel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class HomeController {
    private static final long serialVersionUID = 1L;

    @GetMapping
    public String home() {
        return "index";
    }

};
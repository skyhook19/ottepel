package net.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {

    @RequestMapping("/login")
    public String login(Map<String, Object> model) {

        return "login";
    }
}

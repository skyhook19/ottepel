package net.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class ProgrammController {
    @RequestMapping("/programm")
    public String programm(Map<String, Object> model) {
        return "programm";
    }

}

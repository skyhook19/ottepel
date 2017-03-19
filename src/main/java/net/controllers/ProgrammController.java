package net.controllers;

import net.service.ProgrammService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class ProgrammController {
    private final ProgrammService programmService;

    @Autowired
    public ProgrammController(ProgrammService programmService) {
        this.programmService = programmService;
    }

    @RequestMapping("/programm")
    public String programm(Map<String, Object> model) {
        return "programm";
    }


    @RequestMapping(value = "/sort/{programId}", method = RequestMethod.POST)
    public String sort(@PathVariable("programId") Long id, @RequestBody Map<Integer, Integer> data) {
        programmService.sortParagraf(data, id);
        System.out.println(data);
        return "redirect:programm";
    }

}

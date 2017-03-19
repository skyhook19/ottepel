package net.controllers;

import net.domain.infrastructure.Course;
import net.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class ProgrammController {
    private final CourseService courseService;

    @Autowired
    public ProgrammController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/programm/{programId}")
    public String programm(@PathVariable("programId") Long id, Map<String, Object> model) {
        Course course = courseService.getProgramById(id);
        model.put("course", course);
        return "programm";
    }


    @RequestMapping(value = "/sort/{programId}", method = RequestMethod.POST)
    public String sort(@PathVariable("programId") Long id, @RequestBody Map<Integer, Integer> data) {
        courseService.sortParagraf(data, id);
        System.out.println(data);
        return "redirect:programm";
    }

}

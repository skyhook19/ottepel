package net.controllers;

import net.service.EmployeeService;
import net.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RukController {

    private final UserService userService;
    private final EmployeeService employeeService;

    @Autowired
    public RukController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }


    // TODO /company POST




}

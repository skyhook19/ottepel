package net.controllers;

import net.dto.EmployeeDto;
import net.dto.UserDto;
import net.service.EmployeeService;
import net.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class RukController {

    private final UserService userService;
    private final EmployeeService employeeService;

    @Autowired
    public RukController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @RequestMapping(value = {"/reg_ruk"}, method = RequestMethod.GET)
    public String regRuk(Map<String, Object> mdel) {
        return "reg_ruk";
    }

    @RequestMapping(value = {"/reg_ruk"}, method = RequestMethod.POST)
    public String addRuk(Map<String, Object> mdel,
                         @RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = "login", required = true) String login,
                         @RequestParam(value = "password", required = true) String pass,
                         @RequestParam(value = "email", required = true) String email) {
        userService.addRuc(name, login, pass, email);
        return "redirect:reg_company";
    }

    // TODO /company POST

    @RequestMapping(value = {"/addEmployee"}, method = RequestMethod.POST)
    public String addEmployees(Map<String, Object> model,
                               @RequestParam(value = "name", required = true) String name,
                               @RequestParam(value = "login", required = true) String login,
                               @RequestParam(value = "password", required = true) String pass,
                               @RequestParam(value = "email", required = true) String email,
                               @RequestParam(value = "role", required = true) List<String> roles
    ) {
        userService.addCollaborator(login, pass, roles, email);
        employeeService.addEmployee(login, name);
        return "employees_list";
    }

    @RequestMapping("/employees_list")
    public String employees_list(Map<String, Object> model) {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        model.put("employees", employees);

        return "employees_list";
    }

}

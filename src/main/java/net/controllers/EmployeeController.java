package net.controllers;

import net.dto.EmployeeDto;
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
public class EmployeeController {
    private final UserService userService;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }

    // todo /edit_profile
    /*
    @RequestMapping(value = {"/edit_ruk"}, method = RequestMethod.GET)
    public String editRuk(Map<String, Object> model) {
        UserDto currentUser = userService.getCurrentUserDto();
        model.put("ruk", currentUser);
        return "edit_ruk";
    }


    // todo /edit_profile
    @RequestMapping(value = {"/edit_ruk"}, method = RequestMethod.POST)
    public String editRuk(Map<String, Object> model,
                          @RequestParam(value = "name", required = true) String name,
                          @RequestParam(value = "login", required = true) String login,
                          @RequestParam(value = "password", required = true) String pass,
                          @RequestParam(value = "old_password", required = true) String oldPass,
                          @RequestParam(value = "email", required = true) String email) {
        boolean saved = userService.editRuc(name, login, pass, email, oldPass);
        if (saved) {
            model.put("saved", "Обновлено");
            model.put("ruk", userService.getUser(login));
        }
        return "redirect:edit_ruk";
    }
    */

    // TODO /company GET запрос


    //addEmployee
    @RequestMapping(value = {"/addEmployee"}, method = RequestMethod.POST)
    public String addEmployees(Map<String, Object> model,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "login") String login,
                               @RequestParam(value = "password") String pass,
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "role") List<String> roles
    ) {
        userService.addCollaborator(login, pass, roles, email);
        employeeService.addEmployee(login, name);
        return "redirect:employees_list";
    }


    //addEmployee
    @RequestMapping("/employees_list")
    public String employeesList(Map<String, Object> model) {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        model.put("employees", employees);

        return "employees_list";
    }

    //reg_ruk
    @RequestMapping(value = {"/reg_ruk"}, method = RequestMethod.GET)
    public String regRuk(Map<String, Object> mdel) {
        return "reg_ruk";
    }

    //reg_ruk
    @RequestMapping(value = {"/reg_ruk"}, method = RequestMethod.POST)
    public String addRuk(Map<String, Object> mdel,
                         @RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = "login", required = true) String login,
                         @RequestParam(value = "password", required = true) String pass,
                         @RequestParam(value = "email", required = true) String email) {
        userService.addRuc(name, login, pass, email);
        return "redirect:reg_company";
    }

}

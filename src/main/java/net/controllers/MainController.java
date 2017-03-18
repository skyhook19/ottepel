package net.controllers;

import net.domain.users.Role;
import net.dto.UserDto;
import net.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/auth_all")
    public String auth_all(Map<String, Object> model) {
        return "auth_all";
    }

    @RequestMapping("/auth_ruk")
    public String auth_ruk(Map<String, Object> model) {
        return "auth_ruk";
    }

    @RequestMapping("/employees_list")
    public String employees_list(Map<String, Object> model) {
        return "employees_list";
    }

    @RequestMapping("/groups_list")
    public String group_list(Map<String, Object> model) {
        return "groups_list";
    }

    @RequestMapping("/programms_list")
    public String programms_list(Map<String, Object> model) {
        return "programms_list";
    }

    @RequestMapping("/settings_account")
    public String settings_account(Map<String, Object> model) {
        return "settings_account";
    }

    @RequestMapping("/students_list")
    public String student_list(Map<String, Object> model) {
        return "students_list";
    }

    @RequestMapping("/login")
    public String login(Map<String, Object> model) {
        return "login";
    }

    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public String addContact(@RequestParam("name") String name,
                             @RequestParam("lastName") String lastname,
                             Map<String, Object> model) {
        boolean saved = userService.addContact(name, lastname);
        if (!saved) {
            model.put("error", "Пользователь с таким логином уже существует.");
        } else {
            model.put("completed", "Успешно");
        }
        return "";
    }

    @RequestMapping(value = {"/addCollaborator"}, method = RequestMethod.POST)
    public String addCollaborator(@RequestParam("name") String name,
                                  @RequestParam("login") String login,
                                  @RequestParam("email") String email,
                                  @RequestParam("password") String pass,
                                  @RequestParam(value = "roles", required = false) List<String> rolesName,
                                  Map<String, Object> model) {
        boolean saved = userService.addCollaborator(login, name, pass, rolesName, email);
        if (!saved) {
            model.put("error", "Пользователь с таким логином уже существует.");
        } else {
            model.put("completed", "Успешно");
        }
//        List<UserDto> users = userService.getAllUsers(null, null);
//        model.put("users", users);
//        model.put("sort", "asc");
        return "adminka";
    }

    @RequestMapping(value = {"/adminka", "/adminka/{column}/{sort}"}, method = RequestMethod.GET)
    public String getNewsByDate(@PathVariable(value = "column", required = false) String column,
                                @PathVariable(value = "sort", required = false) String sort,
                                Map<String, Object> model) {
        List<UserDto> users = userService.getAllUsers(column, sort);
        List<Role> roles = userService.getAllRoles();
        model.put("roles", roles);
        model.put("users", users);
        model.put("sort", "asc".equals(sort) ? "desc" : "asc");
        return "adminka";
    }


}

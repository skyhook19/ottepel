package net.controllers;

import net.domain.users.Role;
import net.dto.ContactDto;
import net.dto.UserDto;
import net.service.ContactService;
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
    private final ContactService contactService;

    @Autowired
    public MainController(UserService userService, ContactService contactService) {
        this.userService = userService;
        this.contactService = contactService;
    }

    @RequestMapping("/login")
    public String login(Map<String, Object> model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String addContact(@RequestParam("name") String name,
                             @RequestParam("lastName") String lastname,
                             Map<String, Object> model) {
        boolean added = userService.addContact(name, lastname);
        return "";
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
        List<UserDto> users = userService.getAllUsers(null, null);
        model.put("users", users);
        model.put("sort", "asc");
        return "adminka";
    }


    @RequestMapping(value = {"/addContact"}, method = RequestMethod.POST)
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("lastName") String lastName,
                          Map<String, Object> model) {
        boolean saved = userService.addContact(name, lastName);
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


    @RequestMapping(value = {"/contact/{login}"}, method = RequestMethod.GET)
    public String getNewsByDate(@PathVariable(value = "login", required = false) String login,
                                Map<String, Object> model) {
        ContactDto contact = contactService.getContact(login);
        model.put("contact", contact);
        return "";
    }

    @RequestMapping(value = {"/contacts"}, method = RequestMethod.GET)
    public String getNewsByDate(Map<String, Object> model) {
        List<ContactDto> contacts = contactService.getAllContacts();
        model.put("contacts", contacts);
        return "";
    }
}

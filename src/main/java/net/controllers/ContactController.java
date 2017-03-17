package net.controllers;

import net.dto.ContactDto;
import net.service.ContactService;
import net.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public class ContactController {

    private final ContactService contactService;
    private final UserService userService;

    @Autowired
    public ContactController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
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

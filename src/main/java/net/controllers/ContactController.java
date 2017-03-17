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
        return "";
    }


    @RequestMapping(value = {"/contact/{login}"}, method = RequestMethod.GET)
    public String getContactByLogin(@PathVariable(value = "login", required = false) String login,
                                    Map<String, Object> model) {
        ContactDto contact = contactService.getContact(login);
        model.put("contact", contact);
        return "";
    }

    @RequestMapping(value = {"/contacts"}, method = RequestMethod.GET)
    public String getAllContacts(Map<String, Object> model) {
        List<ContactDto> contacts = contactService.getAllContacts();
        model.put("contacts", contacts);
        return "";
    }

    @RequestMapping(value = {"/contacts/{login}"}, method = RequestMethod.POST)
    public String updateContactInfo(@PathVariable(value = "login", required = false) String login,
                                    @RequestParam("name") String name,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("phoneNumber") String phoneNumber,
                                    @RequestParam("gender") String gender,
                                    @RequestParam("age") int age,
                                    @RequestParam("dob") String dob,//дата рождения
                                    @RequestParam("sourceOfCapital") String sourceOfCapital,
                                    @RequestParam("interests") String interests,
                                    @RequestParam("comment") String comment,
                                    //parent
                                    @RequestParam("nameParent") String nameParent,
                                    @RequestParam("phoneNumberParent") String phoneNumberParent,
                                    @RequestParam("genderParent") String genderParent,
                                    @RequestParam("ageParent") int ageParent,
                                    @RequestParam("commentParent") String commentParent,
                                    @RequestParam("positionParent") String positionParent,
                                    Map<String, Object> model)

    {
//        List<ContactDto> contacts = contactService.updateContact(login,name,);
//        model.put("contacts", contacts);
        return "";
    }
}

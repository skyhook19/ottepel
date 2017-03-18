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

    @RequestMapping(value = {"/addContact"}, method = RequestMethod.POST)
    public String updateContactInfo(@RequestParam(value = "name", required = true) String name,
                                    @RequestParam(value = "lastName", required = true) String lastName,
                                    @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                    @RequestParam(value = "gender", required = false) String gender,
                                    @RequestParam(value = "age", required = false) int age,
                                    @RequestParam(value = "dob", required = false) String dob,//дата рождения
                                    @RequestParam(value = "sourceOfCapital", required = false) String sourceOfCapital,
                                    @RequestParam(value = "interests", required = false) String interests,
                                    @RequestParam(value = "comment", required = false) String comment,
                                    //parent
                                    @RequestParam(value = "nameParent", required = false) String nameParent,
                                    @RequestParam(value = "phoneNumberParent", required = false) String phoneNumberParent,
                                    @RequestParam(value = "genderParent", required = false) String genderParent,
                                    @RequestParam(value = "ageParent", required = false) int ageParent,
                                    @RequestParam(value = "commentParent", required = false) String commentParent,
                                    @RequestParam(value = "positionParent", required = false) String positionParent,
                                    //paret2
                                    @RequestParam(value = "nameParent", required = false) String nameParent2,
                                    @RequestParam(value = "phoneNumberParent", required = false) String phoneNumberParent2,
                                    @RequestParam(value = "genderParent", required = false) String genderParent2,
                                    @RequestParam(value = "ageParent", required = false) int ageParent2,
                                    @RequestParam(value = "commentParent", required = false) String commentParent2,
                                    @RequestParam(value = "positionParent", required = false) String positionParent2,
                                    Map<String, Object> model)

    {
        contactService.addContact(name, lastName, phoneNumber, gender, age, dob, sourceOfCapital, interests, comment,
                nameParent, phoneNumberParent, genderParent, ageParent, commentParent, positionParent,
                nameParent2, phoneNumberParent2, genderParent2, ageParent2, commentParent2, positionParent2);


//        List<ContactDto> contacts = contactService.updateContact(login,name,);
//        model.put("contacts", contacts);
        return "";
    }
}

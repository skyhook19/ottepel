package net.service;

import net.dao.DaoContact;
import net.domain.contacts.Contact;
import net.domain.contacts.Gender;
import net.domain.contacts.Parent;
import net.dto.ContactDto;
import net.service.converters.ConverterContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ContactService {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd";
    private final DaoContact daoContact;
    private final UserService userService;
    private final ConverterContact converterContact;

    @Autowired
    public ContactService(DaoContact daoContact, UserService userService, ConverterContact converterContact) {
        this.daoContact = daoContact;
        this.userService = userService;
        this.converterContact = converterContact;
    }

    public List<ContactDto> getAllContacts() {
        return converterContact.convertToContactDto(daoContact.findAll());
    }

    public ContactDto getContact(String login) {
        return converterContact.convertToContactDto(daoContact.findOneByLogin(login));
    }


    public void addContact(String name, String lastName, String phoneNumber, String gender, int age, String dob, String sourceOfCapital,
                           String interests, String comment,
                           String nameParent, String phoneNumberParent, String genderParent, int ageParent,
                           String commentParent, String positionParent,
                           String nameParent2, String phoneNumberParent2, String genderParent2, int ageParent2,
                           String commentParent2, String positionParent2) {
        String login = userService.addContact(name, lastName);
        Parent parent1 = createParent(nameParent, phoneNumberParent, genderParent, ageParent, commentParent, positionParent);
        Parent parent2 = createParent(nameParent2, phoneNumberParent2, genderParent2, ageParent2, commentParent2, positionParent2);
        Contact contact = createContact(name, phoneNumber, gender, age, dob, sourceOfCapital, interests, comment, login, parent1, parent2);
        daoContact.save(contact);
    }

    private Contact createContact(String name, String phoneNumber, String gender, int age, String dob,
                                  String sourceOfCapital, String interests, String comment, String login,
                                  Parent parent1, Parent parent2) {
        return Contact.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .gender(getGenderByGenderStr(gender))
                .login(login)
                .dob(getDobByString(dob))
                .age(age)
                .sourceOfCapital(sourceOfCapital)
                .interests(interests)
                .comment(comment)
                .parents(Arrays.asList(parent1, parent2)).build();
    }

    private Parent createParent(String nameParent, String phoneNumberParent, String genderParent,
                                int ageParent, String commentParent, String positionParent) {
        return Parent.builder()
                .name(nameParent)
                .phoneNumber(phoneNumberParent)
                .gender(getGenderByGenderStr(genderParent))
                .age(ageParent)
                .position(positionParent)
                .comment(commentParent).build();
    }

    private Gender getGenderByGenderStr(String gender) {
        if (gender == null || gender.isEmpty()) {
            return null;
        }
        return Gender.valueOf(gender);
    }

    private Date getDobByString(String dob) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date date;
        try {
            date = dateTimeFormat.parse(dob);
        } catch (ParseException e) {
            date = null;
            e.printStackTrace();
        }
        return date;
    }
}

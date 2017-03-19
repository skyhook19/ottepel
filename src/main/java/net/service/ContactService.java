package net.service;

import net.dao.contacts.DaoContact;
import net.domain.Gender;
import net.domain.contacts.Contact;
import net.domain.contacts.Parent;
import net.domain.employee.Employee;
import net.domain.infrastructure.Account;
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
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd";
    private final DaoContact daoContact;
    private final UserService userService;
    private final EmployeeService employeeService;
    private final ConverterContact converterContact;

    @Autowired
    public ContactService(DaoContact daoContact, UserService userService, EmployeeService employeeService, ConverterContact converterContact) {
        this.daoContact = daoContact;
        this.userService = userService;
        this.employeeService = employeeService;
        this.converterContact = converterContact;
    }

    public List<ContactDto> getAllContacts() {
        Employee currentEmployee = employeeService.getCurrentEmployee();
        Account account = currentEmployee.getAccount();
        return converterContact.convertToContactDto(daoContact.findByAccount(account));
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

        Employee currentEmployee = employeeService.getCurrentEmployee();
        Account account = currentEmployee.getAccount();

        String login = userService.addContact(name, lastName);
        Parent parent1 = createParent(nameParent, phoneNumberParent, genderParent, ageParent, commentParent, positionParent);
        Parent parent2 = createParent(nameParent2, phoneNumberParent2, genderParent2, ageParent2, commentParent2, positionParent2);
        Contact contact = createContact(account, name, lastName, phoneNumber, gender, age, dob, sourceOfCapital, interests, comment, login, parent1, parent2);
        daoContact.save(contact);
    }

    private Contact createContact(Account account, String name, String lastName, String phoneNumber, String gender, int age, String dob,
                                  String sourceOfCapital, String interests, String comment, String login,
                                  Parent parent1, Parent parent2) {
        return Contact.builder()
                .account(account)
                .name(name)
                .phoneNumber(phoneNumber)
                .gender(getGenderByGenderStr(gender))
                .login(login)
                .dob(getDobByString(dob))
                .age(age)
                .sourceOfCapital(sourceOfCapital)
                .interests(interests)
                .comment(comment)
                .lastName(lastName)
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

    public void updateContact(String login, String name, String lastName, String phoneNumber,
                              int age,
                              /*String gender,*/ Date dob, String sourceOfCapital,
                              String interests, String comment,

                              String nameParent,
                              String phoneNumberParent,
                           //   String genderParent,
                              String commentParent,
                              String positionParent,

                              String nameParent2,
                              String phoneNumberParent2,
                         //     String genderParent2,
                              String commentParent2,
                              String positionParent2)
    {
        Contact contact = daoContact.findOneByLogin(login);
        contact.setName(name);
        contact.setLastName(lastName);
        contact.setPhoneNumber(phoneNumber);
        contact.setAge(age);
        contact.setDob(dob);
        contact.setSourceOfCapital(sourceOfCapital);
        contact.setInterests(interests);
        contact.setComment(comment);

        Parent patent1 = contact.getParents().get(0);
        patent1.setName(nameParent);
        patent1.setPhoneNumber(phoneNumberParent);
        patent1.setComment(commentParent);
        patent1.setPosition(positionParent);

        Parent patent2 = contact.getParents().get(1);
        patent2.setName(nameParent2);
        patent2.setPhoneNumber(phoneNumberParent2);
        patent2.setComment(commentParent2);
        patent2.setPosition(positionParent2);

        daoContact.save(contact);
    }
}

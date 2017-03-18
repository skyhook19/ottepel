package net;


import net.dao.DaoContact;
import net.dao.DaoUserImpl;
import net.dao.users.DaoRole;
import net.dao.users.DaoUser;
import net.domain.contacts.Contact;
import net.domain.contacts.Gender;
import net.domain.contacts.Parent;
import net.domain.users.Role;
import net.domain.users.User;
import net.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class TmpConfig {
    public static final Map<String, Role> roles;

    static {
        roles = new HashMap<>();
        roles.put("ADMIN", Role.builder().authority("ROLE_ADMIN").build());
        roles.put("USER", Role.builder().authority("ROLE_USER").build());
        roles.put("CONTACT", Role.builder().authority("ROLE_CONTACT").build());
        roles.put("RUK", Role.builder().authority("ROLE_RUK").build());
    }

    private final DaoUser daoUser;
    private final DaoRole daoRole;
    private final DaoContact daoContact;
    private final DaoUserImpl daoUserImpl;
    private final ContactService contactService;

    @Autowired
    public TmpConfig(DaoUser daoUser, DaoRole daoRole, DaoContact daoContact, DaoUserImpl daoUserImpl, ContactService contactService) {
        this.daoUser = daoUser;
        this.daoRole = daoRole;
        this.daoContact = daoContact;
        this.daoUserImpl = daoUserImpl;
        this.contactService = contactService;
    }

    @PostConstruct
    public void init() {
        initUsers();
        newContact();
    }

    private void newContact() {
        Parent mama = Parent.builder()
                .name("name")
                .phoneNumber("3242-42342-234")
                .gender(Gender.FEMALE)
                .age(30)
                .comment("comment")
                .position("????").build();

        Contact contact = Contact.builder()
                .login("login")
                .name("name")
                .lastName("lastName")
                .phoneNumber("12-231-123123")
                .gender(Gender.MALE)
                .age(12)
                .dob(new Date())
                .sourceOfCapital("mama")
                .interests("bynthtcs")
                .comment("comment")
                .parents(Arrays.asList(mama)).build();

        daoContact.save(contact);
        List<Contact> all = daoContact.findAll();
        System.out.println(all);
    }

    private void initUsers() {
        daoRole.save(roles.values());
        User user = User.builder().name("user")
                .email("user@mail.com")
                .login("user")
                .password(new BCryptPasswordEncoder().encode("user"))
                .enabled(true)
                .roles(Arrays.asList(roles.get("USER"), roles.get("ADMIN"))).build();
        daoUser.save(user);
        User contact = User.builder().name("2user")
                .email("u3ser@mail.com")
                .login("use3r")
                .password(new BCryptPasswordEncoder().encode("use3r"))
                .enabled(true)
                .roles(Arrays.asList(roles.get("CONTACT"))).build();
        daoUser.save(user);


        Iterable<User> all = daoUser.findAll();
        System.out.println(all);
    }

}

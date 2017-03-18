package net;

import net.dao.contacts.DaoContact;
import net.dao.employee.DaoEmployee;
import net.dao.users.DaoRole;
import net.dao.users.DaoUser;
import net.domain.Gender;
import net.domain.contacts.Contact;
import net.domain.contacts.Parent;
import net.domain.employee.Employee;
import net.domain.infrastructure.Account;
import net.domain.users.Role;
import net.domain.users.User;
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
//        roles.put("ADMIN", Role.builder().authority("ROLE_ADMIN").build());
//        roles.put("USER", Role.builder().authority("ROLE_USER").build());
        roles.put("CONTACT", Role.builder().authority("ROLE_CONTACT").build());
        roles.put("TEACHER", Role.builder().authority("ROLE_TEACHER").build());
        roles.put("RUK", Role.builder().authority("ROLE_RUK").build());
    }

    private final DaoUser daoUser;
    private final DaoRole daoRole;
    private final DaoContact daoContact;
    private final DaoEmployee daoEmployee;

    @Autowired
    public TmpConfig(DaoUser daoUser, DaoRole daoRole, DaoContact daoContact, DaoEmployee daoEmployee) {
        this.daoUser = daoUser;
        this.daoRole = daoRole;
        this.daoContact = daoContact;
        this.daoEmployee = daoEmployee;
    }

    @PostConstruct
    public void init() {
        initRyk();
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

    private void initRyk() {
        daoRole.save(roles.values());
        User user = User.builder()
                .email("user@mail.com")
                .login("user")
                .password(new BCryptPasswordEncoder().encode("user"))
                .enabled(true)
                .roles(Arrays.asList(roles.get("RUK"), roles.get("TEACHER"))).build();
        daoUser.save(user);
        Account account = Account.builder()
                .name("моя супер школа")
                .description("моя супер школа")
                .build();
        Employee employee = Employee.builder()
                .login("user")
                .name("Аркадий Михайлович")
                .dob(new Date())
                .phoneNumber("279-569")
                .gender(Gender.MALE)
                .lastName("Гаврилов")
                .account(account)
                .build();
        daoEmployee.save(employee);


        User contact = User.builder()
                .email("u3ser@mail.com")
                .login("use3r")
                .password(new BCryptPasswordEncoder().encode("use3r"))
                .enabled(true)
                .roles(Arrays.asList(roles.get("CONTACT"))).build();
        daoUser.save(contact);


        Iterable<User> all = daoUser.findAll();
        System.out.println(all);
    }


}

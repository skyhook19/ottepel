package net.service;

import net.dao.contacts.DaoContact;
import net.dao.employee.DaoEmployee;
import net.dao.users.DaoRole;
import net.dao.users.DaoUser;
import net.domain.users.Role;
import net.domain.users.User;
import net.dto.ContactDto;
import net.dto.UserDto;
import net.service.converters.ConverterContact;
import net.service.converters.ConverterEmployee;
import net.service.converters.ConverterUsers;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


@Service
public class UserService {
    private final DaoUser daoUser;
    private final ConverterUsers converterUsers;
    private final ConverterContact converterContact;
    private final ConverterEmployee converterEmployee;
    private final DaoRole daoRole;
    private final DaoContact daoContact;
    private final RoleManager roleManager;
    private final DaoEmployee daoEmployee;

    @Value("${pass.size}")
    private int passwordSize;

    @Autowired
    public UserService(DaoUser daoUser, ConverterUsers converterUsers, ConverterContact converterContact, ConverterEmployee converterEmployee, DaoRole daoRole, DaoContact daoContact, RoleManager roleManager, DaoEmployee daoEmployee) {
        this.daoUser = daoUser;
        this.converterUsers = converterUsers;
        this.converterContact = converterContact;
        this.converterEmployee = converterEmployee;
        this.daoRole = daoRole;
        this.daoContact = daoContact;
        this.roleManager = roleManager;
        this.daoEmployee = daoEmployee;
    }


    public List<UserDto> getAllUsers() {
        return converterUsers.convertToUserDto(daoUser.findAll());
    }

    private String getCurrentUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public List<ContactDto> getAllContacts() {
        List<ContactDto> byAccount = converterContact.convertToContactDto(
                daoContact.findByAccount(daoEmployee.findOneByLogin(getCurrentUserLogin()).getAccount()));
        return byAccount;
    }

    public List<Role> getAllRoles() {
        return daoRole.findAll();
    }

    public boolean addCollaborator(String login, String pass, List<String> rolesName, String email) {
        if (daoUser.findOneByLogin(login) != null) {
            return false;
        }
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setEnabled(true);
        user.setPassword(new BCryptPasswordEncoder().encode(pass));
        user.setRoles(getRoleByRoleName(rolesName));
        daoUser.save(user);
        return true;
    }

    public String addContact(String name, String lastName) {
        User user = new User();
        String login = generateLogin(name, lastName);
        user.setLogin(login);
        user.setPassword(generatePassword());
        user.setRoles(Arrays.asList(roleManager.getRoleByRoleName("CONTACT")));
        user.setEnabled(false);
        return login;
    }

    private List<Role> getRoleByRoleName(List<String> roles) {
        List<Role> rolesResult = new ArrayList<>();
        if (roles == null) {
            return rolesResult;
        }
        for (String role : roles) {
            for (Role role1 : getAllRoles()) {
                if (role.equals(role1.getAuthority())) {
                    rolesResult.add(role1);
                }
            }
        }
        return rolesResult;
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphabetic(passwordSize);
    }

    private String generateLogin(String name, String lastName) {
        Calendar calendar = Calendar.getInstance();
        return name.trim().substring(0, 2) + lastName.trim().substring(0, 2) + calendar.get(Calendar.DAY_OF_MONTH) + calendar.get(Calendar.YEAR);
    }


    public void addRuc(String name, String login, String pass, String email) {
        addCollaborator(login, pass, Arrays.asList("ROLE_RUK"), email);
    }

    public boolean editRuc(String name, String login, String pass, String email, String oldPass) {
        User ruk = daoUser.findOneByLogin(login);
        if (ruk.getPassword().equals(new BCryptPasswordEncoder().encode(oldPass))) {
            ruk.setPassword(new BCryptPasswordEncoder().encode(pass));
            ruk.setEmail(email);
            daoUser.save(ruk);
            return true;
        }
        return false;
    }

    public UserDto getUser(String login) {
        return converterUsers.userToCollaboratorDto(daoUser.findOneByLogin(login));
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return daoUser.findOneByLogin(authentication.getName());
    }

    public UserDto getCurrentUserDto() {
        return converterUsers.userToCollaboratorDto(getCurrentUser());
    }

    public List<User> getAllUsersByLogin(List<String> logins) {
        return daoUser.findByLoginIn(logins);
    }
}

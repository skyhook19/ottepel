package net.service;

import net.dao.users.DaoRole;
import net.dao.users.DaoUser;
import net.domain.Role;
import net.domain.User;
import net.dto.UserDto;
import net.service.converters.ConverterUsers;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


@Service
public class UserService {
    private static Role contactRole;
    private final DaoUser daoUser;
    private final ConverterUsers converterUsers;
    private final DaoRole daoRole;
    @Value("${pass.size}")
    private int passwordSize;

    @Autowired
    public UserService(DaoUser daoUser, ConverterUsers converterUsers, DaoRole daoRole) {
        this.daoUser = daoUser;
        this.converterUsers = converterUsers;
        this.daoRole = daoRole;
    }

    @PostConstruct
    public void init() {
        contactRole = daoRole.findByAuthority("ROLE_CONTACT");
    }

    public List<UserDto> gitAllUsers(String column, String sort) {
        return converterUsers.convertToUserDto(daoUser.findAll());
    }

    public List<Role> getAllRoles() {
        return daoRole.findAll();
    }

    public boolean addCollaborator(String login, String name, String pass, List<String> rolesName, String email) {
        if (daoUser.findOneByLogin(login) != null) {
            return false;
        }
        User collaborator = new User();
        collaborator.setLogin(login);
        collaborator.setEmail(email);
        collaborator.setName(name);
        collaborator.setEnabled(true);
        collaborator.setPassword(new BCryptPasswordEncoder().encode(pass));
        collaborator.setRoles(getRoleByRoleName(rolesName));
        daoUser.save(collaborator);
        return true;
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

    public boolean addContact(String name, String lastName) {
        User contact = new User();
        contact.setLogin(generateLogin(name, lastName));
        contact.setName(name);
        contact.setPassword(generatePassword());
        contact.setRoles(Arrays.asList(contactRole));
        contact.setEnabled(false);
        return true;
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphabetic(passwordSize);
    }

    private String generateLogin(String name, String lastName) {
        Calendar calendar = Calendar.getInstance();
        return name.trim().substring(0, 2) + lastName.trim().substring(0, 2) + calendar.get(Calendar.DAY_OF_MONTH) + calendar.get(Calendar.YEAR);
    }
}

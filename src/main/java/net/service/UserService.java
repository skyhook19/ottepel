package net.service;

import net.dao.DaoRole;
import net.dao.DaoUser;
import net.domain.Role;
import net.domain.User;
import net.dto.UserDto;
import net.service.converters.ConverterUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final DaoUser daoUser;
    private final DaoRole daoRole;
    private final ConverterUsers converterUsers;

    @Autowired
    public UserService(DaoUser daoUser, DaoRole daoRole, ConverterUsers converterUsers) {
        this.daoUser = daoUser;
        this.daoRole = daoRole;
        this.converterUsers = converterUsers;
    }

    public List<UserDto> gitAllUsers(String column, String sort) {
        return converterUsers.convertToUserDto(daoUser.findAll());
    }

    public List<Role> getAllRoles() {
        return daoRole.findAll();
    }

    public boolean addUser(String login, String name, String pass, List<String> rolesName, String email) {
        if (daoUser.findOneByLogin(login) != null) {
            return false;
        }
        User user = User.builder()
                .login(login)
                .password(new BCryptPasswordEncoder().encode(pass))
                .email(email)
                .roles(getRoleByRoleName(rolesName))
                .name(name).build();
        daoUser.save(user);
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
}

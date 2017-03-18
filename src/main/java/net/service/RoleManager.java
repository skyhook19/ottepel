package net.service;

import net.dao.users.DaoRole;
import net.domain.users.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class RoleManager {
    public static final Map<String, Role> roles = new HashMap<>();
    private final DaoRole daoRole;

    @Autowired
    public RoleManager(DaoRole daoRole) {
        this.daoRole = daoRole;
    }

    @PostConstruct
    public void init() {
//        roles.put("ADMIN", daoRole.findByAuthority("ROLE_ADMIN"));
//        roles.put("USER", daoRole.findByAuthority("ROLE_USER"));
        roles.put("CONTACT", daoRole.findByAuthority("ROLE_CONTACT"));
        roles.put("TEACHER", daoRole.findByAuthority("ROLE_TEACHER"));
        roles.put("RUK", daoRole.findByAuthority("ROLE_RUK"));
    }

    public Role getRoleByRoleName(String roleName) {
        return roles.get(roleName);
    }
}

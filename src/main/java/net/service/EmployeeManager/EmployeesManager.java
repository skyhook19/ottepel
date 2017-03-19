package net.service.EmployeeManager;

import net.dao.users.DaoUser;
import net.domain.users.Role;
import net.domain.users.User;
import net.service.RoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeesManager {
    private final DaoUser daoUser;
    private final RoleManager roleManager;

    @Autowired
    public EmployeesManager(DaoUser daoUser, RoleManager roleManager) {
        this.daoUser = daoUser;
        this.roleManager = roleManager;
    }

    public EmployeeType getEmployeesType(User user) {
        List<Role> roles = user.getRoles();
        if (roles.contains(roleManager.getRoleByRoleName("RUK"))) {
            return EmployeeType.RUK;
        }
        if (roles.contains(roleManager.getRoleByRoleName("TEACHER"))) {
            return EmployeeType.TEACHER;
        }
        return null;
    }

    public EmployeeType getEmployeesType(String login) {
        return getEmployeesType(daoUser.findOneByLogin(login));
    }
}

package net.service;

import net.dao.employee.DaoEmployee;
import net.domain.employee.Employee;
import net.domain.infrastructure.Account;
import net.domain.users.User;
import net.dto.EmployeeDto;
import net.service.converters.ConverterEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final DaoEmployee daoEmployee;
    private final ConverterEmployee converterEmployee;
    private final UserService userService;

    @Autowired
    public EmployeeService(DaoEmployee daoEmployee, ConverterEmployee converterEmployee, UserService userService) {
        this.daoEmployee = daoEmployee;
        this.converterEmployee = converterEmployee;
        this.userService = userService;
    }

    public List<EmployeeDto> getAllEmployees() {
        Employee currentEmployee = getCurrentEmployee();
        Account account = currentEmployee.getAccount();
        List<Employee> employees = daoEmployee.findByAccountOrderByLogin(account);
        List<User> users = userService.getAllUsersByLogin(getLoginByEmployees(employees));
        return converterEmployee.convertToEmployeeDto(employees, users);
    }

    private List<String> getLoginByEmployees(List<Employee> employees) {
        return employees.stream().map(Employee::getLogin).collect(Collectors.toList());
    }

    private Employee getCurrentEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return daoEmployee.findOneByLogin(authentication.getName());
    }

    public EmployeeDto getCurrentEmployeeDto() {
        Employee currentEmployee = getCurrentEmployee();
        return converterEmployee.convertToEmployeeDto(currentEmployee, userService.getCurrentUser());
    }

    public void addEmployee(String login, String name) {
        Account account = getCurrentEmployee().getAccount();
        createEmployee(account, login, name);
    }

    public void createEmployee(Account account, String login, String name) {
        Employee employee = Employee.builder()
                .login(login)
                .name(name)
                .account(account)
                .build();
        daoEmployee.save(employee);
    }

    public EmployeeDto getEmployee(String login) {
        Employee employee = daoEmployee.findOneByLogin(login);
        return converterEmployee.convertToEmployeeDto(employee, userService.getUserByLogin(login));
    }

    public boolean updateEmployee(String login, String pass, String passwordOld, List<String> roles, String email, String name) {
        boolean updated = userService.updateUser(login, email, passwordOld, pass, roles);
        if (updated) {
            Employee employee = daoEmployee.findOneByLogin(login);
            employee.setName(name);
            daoEmployee.save(employee);
            return true;
        }
        return false;
    }
}

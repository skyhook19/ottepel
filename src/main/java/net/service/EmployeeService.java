package net.service;

import net.dao.employee.DaoEmployee;
import net.domain.employee.Employee;
import net.domain.infrastructure.Account;
import net.dto.EmployeeDto;
import net.service.converters.ConverterEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private static DaoEmployee daoEmployee;
    private static ConverterEmployee converterEmployee;

    @Autowired
    public EmployeeService(DaoEmployee daoEmployee, ConverterEmployee converterEmployee) {
        this.daoEmployee = daoEmployee;
        this.converterEmployee = converterEmployee;
    }

    public List<EmployeeDto> getAllEmployees(){
        Account account = getCurrentEmployee().getAccount();
        List<Employee> employees = daoEmployee.findByAccount(account);
        return converterEmployee.convertToEmployeeDto(employees);
    }

    private Employee getCurrentEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return daoEmployee.findOneByLogin(authentication.getName());
    }
}

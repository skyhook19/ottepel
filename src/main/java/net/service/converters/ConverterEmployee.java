package net.service.converters;

import net.domain.employee.Employee;
import net.domain.users.Role;
import net.domain.users.User;
import net.dto.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConverterEmployee {
    public List<EmployeeDto> convertToEmployeeDto(List<Employee> employees) {
        return employees.stream().map(this::convertToEmployeeDto).collect(Collectors.toList());
    }


    public EmployeeDto convertToEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .name(employee.getName())
                .dob(employee.getDob())
                .gender(employee.getGender())
                .lastName(employee.getLastName())
                .login(employee.getLogin())
                .phoneNumber(employee.getPhoneNumber())
                .build();
    }

    public EmployeeDto convertToEmployeeDto(Employee employee, User user) {
        return EmployeeDto.builder()
                .name(employee.getName())
                .dob(employee.getDob())
                .gender(employee.getGender())
                .lastName(employee.getLastName())
                .login(employee.getLogin())
                .phoneNumber(employee.getPhoneNumber())
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(Role::getAuthority).collect(Collectors.toList()))
                .build();
    }

    public List<EmployeeDto> convertToEmployeeDto(List<Employee> employees, List<User> users) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>(employees.size());
        for (int i = 0; i < employees.size(); i++) {
            employeeDtoList.add(convertToEmployeeDto(employees.get(i), users.get(i)));
        }
        return employeeDtoList;
    }
}

package net.service.converters;

import net.domain.employee.Employee;
import net.dto.EmployeeDto;
import org.springframework.stereotype.Component;

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

}

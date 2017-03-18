package net.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.domain.Gender;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private String login;
    private String name;
    private String lastName;
    private String phoneNumber;
    private Gender gender;
    private Date dob;//дата рождения
    private String email;
    private List<String> roles;
}

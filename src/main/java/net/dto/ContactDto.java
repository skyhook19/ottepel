package net.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.domain.contacts.Parent;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDto {
    private String login;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private int age;
    private Date dob;//дата рождения
    private String sourceOfCapital;
    private String interests;
    private String comment;
    private List<Parent> parents;

    public String getGender() {
        return gender.toString();
    }
}

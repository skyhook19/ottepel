package net.domain.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.domain.Gender;
import net.domain.infrastructure.Account;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Account account;
    @Column(unique = true)
    private String login;
    private String name;
    private String lastName;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date dob;//дата рождения
}

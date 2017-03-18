package net.domain.contacts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.domain.Gender;
import net.domain.infrastructure.Account;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;

    @Column(nullable = false, unique = true)
    private String login;
    private String name;
    private String lastName;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;
    private Date dob;//дата рождения
    private String sourceOfCapital;
    @Column(columnDefinition = "text")
    private String interests;
    @Column(columnDefinition = "text")
    private String comment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Parent> parents;
    private boolean enabled;

    public String getGender() {
        return gender.toString();
    }

    public boolean isEnabled() {
        return enabled;
    }
}

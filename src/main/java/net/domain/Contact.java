package net.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(nullable = false)
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

    public String getGender() {
        return gender.toString();
    }
}

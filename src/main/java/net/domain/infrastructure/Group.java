package net.domain.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.domain.contacts.Contact;
import net.domain.employee.Employee;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToOne
    private Course course;
    @ManyToMany
    private List<Lesson> lessons;
    @ManyToMany
    private List<Contact> contacts;//students
    @ManyToMany
    private List<Employee> employees;
}

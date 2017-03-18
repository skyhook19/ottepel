package net.domain.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.domain.contacts.Contact;
import net.domain.employee.Employee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Group {
    @Id
    private long id;
    private String name;
    private Сourses сourses;
    @ManyToMany
    private List<Lesson> lessons;
    @ManyToMany
    private List<Contact> contacts;//students
    @ManyToMany
    private List<Employee> employees;
}

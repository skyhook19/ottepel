package net;

import net.dao.contacts.DaoContact;
import net.dao.employee.DaoEmployee;
import net.dao.infrastructure.DaoCourses;
import net.dao.infrastructure.DaoGroup;
import net.dao.infrastructure.DaoLesson;
import net.dao.infrastructure.DaoParagraph;
import net.dao.users.DaoRole;
import net.dao.users.DaoUser;
import net.domain.Gender;
import net.domain.contacts.Contact;
import net.domain.contacts.Parent;
import net.domain.employee.Employee;
import net.domain.infrastructure.*;
import net.domain.users.Role;
import net.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class TmpConfig {
    public static final Map<String, Role> roles;

    static {
        roles = new HashMap<>();
//        roles.put("ADMIN", Role.builder().authority("ROLE_ADMIN").build());
//        roles.put("USER", Role.builder().authority("ROLE_USER").build());
        roles.put("CONTACT", Role.builder().authority("ROLE_CONTACT").build());
        roles.put("TEACHER", Role.builder().authority("ROLE_TEACHER").build());
        roles.put("RUK", Role.builder().authority("ROLE_RUK").build());
    }

    private final DaoUser daoUser;
    private final DaoRole daoRole;
    private final DaoContact daoContact;
    private final DaoEmployee daoEmployee;
    private final DaoGroup daoGroup;
    private final DaoCourses daoCourses;
    private final DaoLesson daoLesson;
    private final DaoParagraph daoParagraph;

    @Autowired
    public TmpConfig(DaoUser daoUser, DaoRole daoRole, DaoContact daoContact, DaoEmployee daoEmployee, DaoGroup daoGroup, DaoCourses daoCourses, DaoLesson daoLesson, DaoParagraph daoParagraph) {
        this.daoUser = daoUser;
        this.daoRole = daoRole;
        this.daoContact = daoContact;
        this.daoEmployee = daoEmployee;
        this.daoGroup = daoGroup;
        this.daoCourses = daoCourses;
        this.daoLesson = daoLesson;
        this.daoParagraph = daoParagraph;
    }

    @PostConstruct
    public void init() {
        baseDataInit();
    }


    private void baseDataInit() {
        daoRole.save(roles.values());

        User user = User.builder()
                .email("user@mail.com")
                .login("user")
                .password(new BCryptPasswordEncoder().encode("user"))
                .enabled(true)
                .roles(Arrays.asList(roles.get("RUK"), roles.get("TEACHER"))).build();
        daoUser.save(user);

        Account account = Account.builder()
                .name("моя супер школа")
                .description("моя супер школа")
                .build();


        Employee employee = Employee.builder()
                .login("user")
                .name("Аркадий Михайлович")
                .dob(new Date())
                .phoneNumber("279-569")
                .gender(Gender.MALE)
                .lastName("Гаврилов")
                .account(account)
                .build();
        daoEmployee.save(employee);


        User contact = User.builder()
                .email("u3ser@mail.com")
                .login("use3r")
                .password(new BCryptPasswordEncoder().encode("use3r"))
                .enabled(true)
                .roles(Arrays.asList(roles.get("CONTACT"))).build();
        daoUser.save(contact);

        Parent parent1 = createParent("Елена Пупкина", "88005553536", Gender.FEMALE, 39, "", "");
        Parent parent2 = createParent("Елена Навашина", "88005553538", Gender.FEMALE, 39, "", "");
        Parent parent3 = createParent("Антон Иванов", "88005553540", Gender.MALE, 39, "", "");

        Contact contact1 = createContact("vpupkin", account, "Вася", "Пупкин", "88005553535",
                Gender.MALE, 16, "Родители", "Java", "", Arrays.asList(parent1));
        Contact contact2 = createContact("vnavashin", account, "Вася", "Навашин", "88005553537",
                Gender.MALE, 16, "Родители", "Java", "", Arrays.asList(parent2));
        Contact contact3 = createContact("vivanov", account, "Вася", "Иванов", "88005553539",
                Gender.MALE, 16, "Родители", "Java", "", Arrays.asList(parent3));

        Iterable<User> all = daoUser.findAll();
        System.out.println(all);

        List<Contact> allC = daoContact.findAll();
        System.out.println(allC);

        Paragraph p1 = createParagraph("Введение в Java", 1);
        Paragraph p2 = createParagraph("ООП в Java", 2);
        ArrayList<Paragraph> paragraphs = new ArrayList<>();
        paragraphs.add(p1);
        paragraphs.add(p2);

        Lesson l1 = createLesson(p1, 1);
        Lesson l2 = createLesson(p2, 2);
        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(l1);
        lessons.add(l2);

       /* Course course1 = createCourse("Программирование на Java",
                "Курс для обучения лет языку программирования Java",
                30, "Для детей старше 16", paragraphs);
*/
        // Group g1 = createGroup("Старшая группа", course1, lessons, Arrays.asList(contact1,contact2,contact3), Arrays.asList(employee));

    }

    private Group createGroup(String name, Course course, List<Lesson> lessons, List<Contact> contacts, List<Employee> employees){
        Group group = Group.builder()
                .name(name)
                .course(course)
                .lessons(lessons)
                .contacts(contacts)
                .employees(employees)
                .build();

        daoGroup.save(group);
        return group;
    }

    private Course createCourse(String name, String description, int countLessons, String recommendations, List<Paragraph> paragraphs){
        Course course = Course.builder()
                .name(name)
                .description(description)
                .countLessons(countLessons)
                .recommendations(recommendations)
                .paragraphs(paragraphs)
                .build();

        daoCourses.save(course);
        return course;
    }

    private Lesson createLesson(Paragraph paragraph, int number){
        Lesson lesson = Lesson.builder()
                .paragraph(paragraph)
                .number(number)
                .build();

        daoLesson.save(lesson);
        return lesson;
    }

    private Paragraph createParagraph(String name, int number){
        Paragraph paragraph = Paragraph.builder()
                .name(name)
                .number(number)
                .build();

        daoParagraph.save(paragraph);
        return  paragraph;
    }

    private Contact createContact(String login, Account account, String name, String lastName, String phone, Gender gender,
                                  int age, String sourceOfCapital, String interests, String comment, List<Parent> parents){
        Contact contact = Contact.builder()
                .login(login)
                .account(account)
                .name(name)
                .lastName(lastName)
                .phoneNumber(phone)
                .gender(gender)
                .age(age)
                .dob(new Date())
                .sourceOfCapital(sourceOfCapital)
                .interests(interests)
                .comment(comment)
                .parents(parents)
                .build();

        daoContact.save(contact);
        return contact;
    }

    private Parent createParent(String name, String phone, Gender gender, int age, String comment, String position){
        Parent parent = Parent.builder()
                .name(name)
                .phoneNumber(phone)
                .gender(gender)
                .age(age)
                .comment(comment)
                .position(position)
                .build();

        return parent;
    }
}

package net.service;

import net.dao.infrastructure.DaoCourses;
import net.domain.infrastructure.Course;
import net.domain.infrastructure.Paragraph;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {
    private final DaoCourses daoCourses;

    public CourseService(DaoCourses daoCourses) {
        this.daoCourses = daoCourses;
    }

    @PostConstruct
    public void init() {
        List<Paragraph> paragraphList = Arrays.asList(
                Paragraph.builder()
                        .name("Вводное занятие")
                        .description("Описание вводного занятия")
                        .duration("1:30")
                        .number(1).build(),
                Paragraph.builder()
                        .name("Архитектура вычислительных систем ")
                        .description("Описание архитектуры вычислительных систем")
                        .duration("1:25")
                        .number(2).build(),
                Paragraph.builder()
                        .name("Математическая кибернетика ")
                        .description("Описание математической кибернетики")
                        .duration("1:00")
                        .number(3).build(),
                Paragraph.builder()
                        .name("Основы HTTP протоколов ")
                        .description("Описание основ http протоколов")
                        .duration("2:40")
                        .number(4).build(),
                Paragraph.builder()
                        .name("Хакатон ")
                        .description("Описание хакатона. Занятие на 48 часов. Без сна.")
                        .duration("48:00")
                        .number(5).build()

        );
        Course course = Course.builder()
                .countLessons(12)
                .description("Описание программы. Программа ниче так, учиться можно. В общем круто все.")
                .name("№ 1")
                .recommendations("asdasda")
                .paragraphs(paragraphList).build();
        daoCourses.save(course);
        Iterable<Course> all = daoCourses.findAll();
        System.out.println(all);
    }

    public void sortParagraf(Map<Integer, Integer> data, Long id) {
        Course course = daoCourses.findOneById(id);
        List<Paragraph> paragraphList = course.getParagraphs();
        for (Paragraph paragraph : paragraphList) {
            paragraph.setNumber(data.get((int) paragraph.getId()));
        }
        daoCourses.save(course);
    }

    public Course getProgramById(Long id) {
        return daoCourses.findOneById(id);
    }
}

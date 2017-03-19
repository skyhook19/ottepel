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
                        .name("qqqqqqq1")
                        .number(1).build(),
                Paragraph.builder()
                        .name("qqqqqqq2")
                        .number(2).build(),
                Paragraph.builder()
                        .name("qqqqqqq3")
                        .number(3).build()

        );
        Course course = Course.builder()
                .countLessons(12)
                .description("qwe")
                .name("aaaa")
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

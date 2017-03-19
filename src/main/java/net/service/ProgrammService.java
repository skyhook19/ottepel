package net.service;

import net.dao.infrastructure.DaoCourses;
import net.dao.infrastructure.DaoParagraf;
import net.domain.infrastructure.Course;
import net.domain.infrastructure.Paragraf;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProgrammService {
    private final DaoParagraf daoParagraf;
    private final DaoCourses daoCourses;

    public ProgrammService(DaoParagraf daoParagraf, DaoCourses daoCourses) {
        this.daoParagraf = daoParagraf;
        this.daoCourses = daoCourses;
    }

    public void sortParagraf(Map<Integer, Integer> data, Long id) {
        Course course = daoCourses.findOneById(id);
        List<Paragraf> paragrafList = course.getParagrafList();
        for (Paragraf paragraf : paragrafList) {
            paragraf.setOrder(data.get((int) paragraf.getId()));
        }
        daoCourses.save(course);
    }
}

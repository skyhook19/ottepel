package net.dao.infrastructure;

import net.domain.infrastructure.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DaoCourses extends CrudRepository<Course, Long> {
    Course findOneById(Long id);
}

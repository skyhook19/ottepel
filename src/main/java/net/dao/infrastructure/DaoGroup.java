package net.dao.infrastructure;

import net.domain.infrastructure.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DaoGroup extends CrudRepository<Group, Long> {
}

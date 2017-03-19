package net.dao.infrastructure;

import net.domain.infrastructure.Paragraf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DaoParagraf extends CrudRepository<Paragraf, Long> {
    Paragraf findOneById(Long id);
}

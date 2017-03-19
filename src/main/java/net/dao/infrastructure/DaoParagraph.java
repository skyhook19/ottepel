package net.dao.infrastructure;

import net.domain.infrastructure.Paragraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DaoParagraph extends CrudRepository<Paragraph, Long> {
    @Override
    List<Paragraph> findAll();

    Paragraph findOneByName(String name);

    Paragraph findOneById(Long id);
}

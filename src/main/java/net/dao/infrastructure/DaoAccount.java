package net.dao.infrastructure;

import net.domain.infrastructure.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DaoAccount extends CrudRepository<Account, Long> {
}

package net.dao.contacts;

import net.domain.contacts.Contact;
import net.domain.infrastructure.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DaoContact extends CrudRepository<Contact, Long> {
    @Override
    List<Contact> findAll();

    Contact findOneByLogin(String login);

    List<Contact> findByAccount(Account account);
}

package net.dao;

import net.domain.contacts.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DaoContact extends CrudRepository<Contact, Long> {
    @Override
    List<Contact> findAll();

    Contact findByLogin();
}

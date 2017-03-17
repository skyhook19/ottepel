package net.dao;

import net.domain.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DaoContact extends CrudRepository<Contact, Long> {
    @Override
    List<Contact> findAll();
}

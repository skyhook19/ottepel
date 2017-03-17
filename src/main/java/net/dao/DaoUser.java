package net.dao;

import net.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface DaoUser extends CrudRepository<User, String> {
    User findOneByLogin(String login);
}

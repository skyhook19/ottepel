package net.dao.users;

import net.domain.users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DaoUser extends CrudRepository<User, String> {
    User findOneByLogin(String login);

    @Override
    List<User> findAll();

}

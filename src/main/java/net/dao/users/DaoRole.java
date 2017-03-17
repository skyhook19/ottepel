package net.dao.users;


import net.domain.users.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DaoRole extends CrudRepository<Role, Integer> {
    @Override
    List<Role> findAll();

    Role findByAuthority(String roleName);
}

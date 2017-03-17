package net.dao;


import net.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DaoRole extends CrudRepository<Role, Integer> {
}

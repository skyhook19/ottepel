package net.dao.employee;

import net.domain.employee.Employee;
import net.domain.infrastructure.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DaoEmployee extends CrudRepository<Employee, Long> {
    @Override
    List<Employee> findAll();

    Employee findOneByLogin(String login);

    List<Employee> findByAccount(Account account);
}

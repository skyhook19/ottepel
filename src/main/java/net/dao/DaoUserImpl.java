package net.dao;

import net.domain.infrastructure.Account;
import net.domain.users.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DaoUserImpl {

    private final SessionFactory sessionFactory;

    @Autowired
    public DaoUserImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getAllEmployees(Account account) {
        List list = sessionFactory.getCurrentSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("account", account))
                .createCriteria("roles").add(Restrictions.not(Restrictions.eq("authority", "ROLE_CONTACT"))).list();
        return list;
    }

    public List<User> getAllContacts(Account account) {
        List list = sessionFactory.getCurrentSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("account", account))
                .createCriteria("roles").add(Restrictions.eq("authority", "ROLE_CONTACT")).list();
        return list;
    }

}

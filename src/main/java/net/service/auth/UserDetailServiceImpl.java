package net.service.auth;

import net.dao.users.DaoUser;
import net.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final DaoUser daoUser;

    @Autowired
    public UserDetailServiceImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = daoUser.findOneByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Founded");
        }
        return new UserDetailImpl(user);
    }
}
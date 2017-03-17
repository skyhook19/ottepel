package net;


import net.dao.DaoRole;
import net.dao.DaoUser;
import net.domain.Role;
import net.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class TmpConfig {
    public static final Map<String, Role> roles;

    static {
        roles = new HashMap<>();
        roles.put("ADMIN", Role.builder().authority("ROLE_ADMIN").build());
        roles.put("USER", Role.builder().authority("ROLE_USER").build());
    }

    private final DaoUser daoUser;
    private final DaoRole daoRole;


    @Autowired
    public TmpConfig(DaoUser daoUser, DaoRole daoRole) {
        this.daoUser = daoUser;
        this.daoRole = daoRole;
    }


    @PostConstruct
    public void init() {
        initUsers();
    }

    private void initUsers() {
        daoRole.save(roles.values());
        User user = User.builder().name("user")
                .email("user@mail.com")
                .login("user")
                .password(new BCryptPasswordEncoder().encode("user"))
                .roles(new ArrayList<>(roles.values())).build();
        daoUser.save(user);


        Iterable<User> all = daoUser.findAll();
        System.out.println(all);
    }

}

package net.service;

import net.dao.infrastructure.DaoAccount;
import net.domain.infrastructure.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final DaoAccount daoAccount;

    public AccountService(DaoAccount daoAccount) {
        this.daoAccount = daoAccount;
    }

    public Account createAccount(String name, String description) {
        Account account = Account.builder().name(name).description(description).build();
        daoAccount.save(account);
        return account;
    }
}

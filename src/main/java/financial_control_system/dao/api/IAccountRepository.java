package financial_control_system.dao.api;

import financial_control_system.dao.model.Account;

import java.util.Set;
import java.util.UUID;

public interface IAccountRepository {
    void create(Account account);
    Set<Account> getAll();
    Account getById(UUID id);
    void update(Account account);
    void delete(Account account);
}

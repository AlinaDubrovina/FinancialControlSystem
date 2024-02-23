package financial_control_system.dao.api;

import financial_control_system.dao.model.Account;

import java.sql.SQLException;
import java.util.Set;

public interface IAccountRepository {
    void create(Account account) throws SQLException; // а где дока?
    Set<Account> getAll() throws SQLException;
    Account getById(long id) throws SQLException;
    void update(long id, Account account) throws SQLException;
    void delete(Account account) throws SQLException;
}

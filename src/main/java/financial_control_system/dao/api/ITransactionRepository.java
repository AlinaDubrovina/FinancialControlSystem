package financial_control_system.dao.api;

import financial_control_system.dao.model.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ITransactionRepository {
    void create(Transaction transaction) throws SQLException;
    List<Transaction> getByAccountId(long accountId) throws SQLException;
    Transaction getById(UUID id) throws SQLException;
    void update(UUID id, Transaction transaction) throws SQLException;
    void delete(Transaction transaction) throws SQLException;
}

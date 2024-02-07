package financial_control_system.dao.api;

import financial_control_system.dao.model.Transaction;

import java.util.Set;
import java.util.UUID;

public interface ITransactionRepository {
    void create(Transaction transaction);
    Set<Transaction> getAll();
    Transaction getById(UUID id);
    void update(Transaction transaction);
    void delete(Transaction transaction);
}

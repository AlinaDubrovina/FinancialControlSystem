package financial_control_system.service.api;

import financial_control_system.core.dto.TransactionDto;

import java.util.List;
import java.util.UUID;

public interface ITransactionService {
    void create(TransactionDto transactionDto);
    List<TransactionDto> getByAccountId(long id);
    void update(UUID id, String newDescription);
    void delete(UUID id);
}

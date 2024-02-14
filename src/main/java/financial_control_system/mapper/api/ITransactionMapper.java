package financial_control_system.mapper.api;

import financial_control_system.core.dto.TransactionDto;
import financial_control_system.dao.model.Transaction;

public interface ITransactionMapper {
    Transaction toEntity(TransactionDto dto);
    TransactionDto toDto(Transaction entity);
}

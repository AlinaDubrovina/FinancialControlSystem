package financial_control_system.mapper;

import financial_control_system.core.dto.TransactionDto;
import financial_control_system.dao.model.Transaction;
import financial_control_system.mapper.api.ITransactionMapper;

public class TransactionMapper implements ITransactionMapper {
    @Override
    public Transaction toEntity(TransactionDto dto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(dto.getTransactionId());
        transaction.setAmount(dto.getAmount());
        transaction.setDescription(dto.getDescription());
        transaction.setAccountId(dto.getAccountId());
        return transaction;
    }

    @Override
    public TransactionDto toDto(Transaction entity) {
        TransactionDto transaction = new TransactionDto();
        transaction.setTransactionId(entity.getTransactionId());
        transaction.setAmount(entity.getAmount());
        transaction.setDescription(entity.getDescription());
        transaction.setAccountId(entity.getAccountId());
        return transaction;
    }
}

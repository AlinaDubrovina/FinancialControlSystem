package financial_control_system.service;

import financial_control_system.core.dto.TransactionDto;
import financial_control_system.dao.TransactionRepository;
import financial_control_system.dao.api.ITransactionRepository;
import financial_control_system.dao.model.Transaction;
import financial_control_system.mapper.TransactionMapper;
import financial_control_system.mapper.api.ITransactionMapper;
import financial_control_system.service.api.ITransactionService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService implements ITransactionService {
    private final ITransactionRepository repository;
    private final ITransactionMapper mapper;

    public TransactionService() {
        this.repository = new TransactionRepository();
        this.mapper = new TransactionMapper();
    }

    @Override
    public void create(TransactionDto transactionDto) {
        Transaction transaction;
        try {
            transaction = mapper.toEntity(transactionDto);
            repository.create(transaction);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TransactionDto> getByAccountId(long id) {
        List<TransactionDto> transactionDtos = new ArrayList<>();
        List<Transaction> transactions;
        try {
            transactions = repository.getByAccountId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Transaction transaction : transactions) {
            TransactionDto transactionDto = mapper.toDto(transaction);
            transactionDtos.add(transactionDto);
        }
        return transactionDtos;
    }

    @Override
    public void update(UUID id, String newDescription) {
        try {
            Transaction transaction = repository.getById(id);
            transaction.setDescription(newDescription);
            repository.update(id, transaction);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            Transaction transaction = repository.getById(id);
            repository.delete(transaction);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package financial_control_system.service;

import financial_control_system.core.dto.AccountDto;
import financial_control_system.dao.AccountRepository;
import financial_control_system.dao.api.IAccountRepository;
import financial_control_system.dao.model.Account;
import financial_control_system.mapper.AccountMapper;
import financial_control_system.mapper.api.IAccountMapper;
import financial_control_system.service.api.IAccountService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountService implements IAccountService {
    private final IAccountRepository accountRepository;
    private final IAccountMapper accountMapper;

    public AccountService() {
        this.accountRepository = new AccountRepository();
        this.accountMapper = new AccountMapper();
    }

    @Override
    public void create(AccountDto accountDto) {
        Account account;
        try {
            account = accountMapper.toEntity(accountDto);
            accountRepository.create(account);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<AccountDto> getAll() {
        try {
            return accountRepository.getAll()
                    .stream()
                    .map(accountMapper::toDto)
                    .collect(Collectors.toSet());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AccountDto getById(long id) {
        Account account;
        try {
            account = accountRepository.getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountMapper.toDto(account);
    }


    @Override
    public void update(long id, BigDecimal newBalance) {
        try {
            Account account = accountRepository.getById(id);
            account.setBalance(newBalance);
            accountRepository.update(id, account);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try {
            Account account = accountRepository.getById(id);
            accountRepository.delete(account);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

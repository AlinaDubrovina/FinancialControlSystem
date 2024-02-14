package financial_control_system.mapper;

import financial_control_system.core.dto.AccountDto;
import financial_control_system.dao.model.Account;
import financial_control_system.mapper.api.IAccountMapper;

import java.sql.SQLException;

public class AccountMapper implements IAccountMapper {
    @Override
    public Account toEntity(AccountDto accountDto) throws SQLException {
        Account account = new Account();
        account.setAccountId(accountDto.getAccountId());
        account.setBalance(accountDto.getBalance());
        account.setUserId(accountDto.getUserId());
        return account;
    }

    @Override
    public AccountDto toDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(account.getAccountId());
        accountDto.setBalance(account.getBalance());
        accountDto.setUserId(account.getUserId());
        return accountDto;
    }

}

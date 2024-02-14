package financial_control_system.mapper.api;

import financial_control_system.core.dto.AccountDto;
import financial_control_system.dao.model.Account;

import java.sql.SQLException;

public interface IAccountMapper {
    Account toEntity(AccountDto accountDto) throws SQLException;
    AccountDto toDto(Account account);
}

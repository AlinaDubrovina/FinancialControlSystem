package financial_control_system.service.api;

import financial_control_system.core.dto.AccountDto;

import java.math.BigDecimal;
import java.util.Set;

public interface IAccountService {
    void create(AccountDto accountDto);
    Set<AccountDto> getAll();
    AccountDto getById(long id);
    void update(long id, BigDecimal newBalance);
    void delete(long id);
}

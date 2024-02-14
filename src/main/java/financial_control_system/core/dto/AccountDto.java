package financial_control_system.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class AccountDto {
    private long accountId;
    private BigDecimal balance;
    private long userId;
}

package financial_control_system.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class AccountDto {
    private UUID accountId;
    private BigDecimal balance;
    private UserDto user;
}

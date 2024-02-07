package financial_control_system.dao.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Account {
    private UUID accountId;
    private BigDecimal balance;
    private User user;
}

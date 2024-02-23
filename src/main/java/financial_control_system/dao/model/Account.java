package financial_control_system.dao.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class Account {
    private long accountId;
    private BigDecimal balance;
    private long userId;
}

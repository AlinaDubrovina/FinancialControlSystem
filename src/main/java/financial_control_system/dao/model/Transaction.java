package financial_control_system.dao.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Transaction {
    private UUID transactionId;
    private BigDecimal amount;
    private String description;
    private Account account;
}

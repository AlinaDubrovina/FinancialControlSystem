package financial_control_system.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class TransactionDto {
    private UUID transactionId;
    private BigDecimal amount;
    private String description;
    private long accountId;
}

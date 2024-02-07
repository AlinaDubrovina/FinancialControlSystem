package financial_control_system.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    private long userId;
    private String userName;
    private String email;
}

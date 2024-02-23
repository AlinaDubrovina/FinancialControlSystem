package financial_control_system.core.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long userId;
    private String userName;
    private String email;
}

package financial_control_system.dao.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private long userId;
    private String userName;
    private String email;
}

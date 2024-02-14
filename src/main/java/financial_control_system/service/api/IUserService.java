package financial_control_system.service.api;

import financial_control_system.core.dto.UserDto;

import java.util.Set;

public interface IUserService {
    void create(UserDto userDto);
    Set<UserDto> getAll();
    UserDto getById(long id);
    void update(long id, String newEmail);
    void delete(long id);
}

package financial_control_system.mapper.api;

import financial_control_system.core.dto.UserDto;
import financial_control_system.dao.model.User;

public interface IUserMapper {
    User toEntity(UserDto userDto);
    UserDto toDto(User user);
}

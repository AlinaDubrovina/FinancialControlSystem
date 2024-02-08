package financial_control_system.mapper;

import financial_control_system.core.dto.UserDto;
import financial_control_system.dao.model.User;
import financial_control_system.mapper.api.IUserMapper;

public class UserMapper implements IUserMapper {
    @Override
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        return user;
    }

    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}

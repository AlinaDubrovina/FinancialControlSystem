package financial_control_system.service;

import financial_control_system.core.dto.UserDto;
import financial_control_system.dao.UserRepository;
import financial_control_system.dao.api.IUserRepository;
import financial_control_system.dao.model.User;
import financial_control_system.mapper.api.IUserMapper;
import financial_control_system.mapper.UserMapper;
import financial_control_system.service.api.IUserService;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    public UserService() {
        this.userRepository = new UserRepository();
        this.userMapper = new UserMapper();
    }

    @Override
    public void create(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        try {
            userRepository.create(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<UserDto> getAll() {
        Set<UserDto> userDtos = new HashSet<>();
        try {
            Set<User> all = userRepository.getAll();

            for (User user : all) {
                UserDto userDto = new UserDto();
                userDto.setUserId(user.getUserId());
                userDto.setUserName(user.getUserName());
                userDto.setEmail(user.getEmail());
                userDtos.add(userDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userDtos;
    }

    @Override
    public UserDto getById(long id) {
        User user;
        try {
            user = userRepository.getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userMapper.toDto(user);
    }

    @Override
    public void update(long userId, String newEmail) {
        try {
            User user = userRepository.getById(userId);
            user.setEmail(newEmail);
            userRepository.update(userId, user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long userId) {
        try {
            User user = userRepository.getById(userId);
            userRepository.delete(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

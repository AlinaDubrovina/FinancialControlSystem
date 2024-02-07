package financial_control_system.dao;

import financial_control_system.dao.api.IUserRepository;
import financial_control_system.dao.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserRepository implements IUserRepository {
    private static final String CREATE_QUERY = "INSERT into app.user(user_name, email) VALUES(?, ?) ";
    private static final String GET_ALL_QUERY = "SELECT * FROM app.user";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM user WHERE user_id = ?";
    private static final String UPDATE_QUERY = "UPDATE app.user SET email = ? WHERE user_id = ?";
    private static final String DELETE_QUERY = "DELETE * FROM app.user WHERE user_id = ?";
    private final DbConnection connection;

    public UserRepository(){
        connection = new DbConnection();
    }

    @Override
    public void create(User user) throws SQLException {
        try (Connection conn = connection.connect();
             PreparedStatement statement = conn.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());

            statement.executeUpdate();
        }
    }

    @Override
    public Set<User> getAll() throws SQLException {
        Set<User> users = new HashSet<>();

        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
        }

        return users;
    }

    @Override
    public User getById(long id) throws SQLException {
        User user = null;

        try (Connection conn = connection.connect();
             PreparedStatement statement = conn.prepareStatement(GET_BY_ID_QUERY)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setUserId(resultSet.getLong("user_id"));
                    user.setUserName(resultSet.getString("user_name"));
                    user.setEmail(resultSet.getString("email"));
                }
            }
        }

        return user;
    }

    @Override
    public void update(long userId, User user) throws SQLException {
        try (Connection conn = connection.connect();
             PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY)) {
            statement.setLong(1, userId);
            statement.setString(2, user.getEmail());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(User user) throws SQLException {
        try (Connection conn = connection.connect();
             PreparedStatement statement = conn.prepareStatement(DELETE_QUERY)) {
            long userId = user.getUserId();
            statement.setString(1, String.valueOf(userId));
            statement.executeUpdate();
        }
    }
}

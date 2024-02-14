package financial_control_system.dao.api;

import financial_control_system.dao.model.User;

import java.sql.SQLException;
import java.util.Set;

public interface IUserRepository {
    void create(User user) throws SQLException;
    Set<User> getAll() throws SQLException;
    User getById(long id) throws SQLException;
    void update(long id, User user) throws SQLException;
    void delete(User user) throws SQLException;
}

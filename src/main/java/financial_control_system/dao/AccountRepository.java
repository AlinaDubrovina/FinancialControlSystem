package financial_control_system.dao;

import financial_control_system.dao.api.IAccountRepository;
import financial_control_system.dao.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AccountRepository implements IAccountRepository {
    private static final String CREATE_QUERY = "INSERT into app.account(balance, user_id) VALUES(?, ?) "; // молодец
    private static final String GET_ALL_QUERY = "SELECT * FROM app.account";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM app.account WHERE account_id = ?";
    private static final String UPDATE_QUERY = "UPDATE app.account SET balance = ? WHERE account_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM app.account WHERE account_id = ?";
    private final DbConnection connection;

    public AccountRepository() {
        connection = new DbConnection();
    }

    @Override
    public void create(Account account) throws SQLException {
        try (Connection conn = connection.connect();
             PreparedStatement statement = conn.prepareStatement(CREATE_QUERY)) {
            statement.setBigDecimal(1, account.getBalance());
            statement.setLong(2, account.getUserId());

            statement.executeUpdate();
        }
    }

    @Override
    public Set<Account> getAll() throws SQLException {
        Set<Account> accounts = new HashSet<>();

        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                accounts.add(map(resultSet));
            }
        }

        return accounts;
    }

    private Account map(ResultSet resultSet) throws SQLException {
        return  new Account()
                .setAccountId(resultSet.getLong("account_id"))// вот эту логику можно в маппер вынести
                .setBalance(resultSet.getBigDecimal("balance")) // типо так
                .setUserId(resultSet.getLong("user_id"));
    }

    @Override
    public Account getById(long id) throws SQLException {
        Account account = null;

        try (Connection conn = connection.connect();
             PreparedStatement statement = conn.prepareStatement(GET_BY_ID_QUERY)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    account = new Account();
                    account.setAccountId(resultSet.getLong("account_id"));
                    account.setBalance(resultSet.getBigDecimal("balance"));
                    account.setUserId(resultSet.getLong("user_id"));
                }
            }
        }
        return account;
    }

    @Override
    public void update(long id, Account account) throws SQLException {
        try (Connection conn = connection.connect();
             PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY)) {
            statement.setBigDecimal(1, account.getBalance());
            statement.setLong(2, id);

            statement.executeUpdate();
        }
    }


    @Override
    public void delete(Account account) throws SQLException {
        try (Connection conn = connection.connect();
             PreparedStatement statement = conn.prepareStatement(DELETE_QUERY)) {
            long accountId = account.getAccountId();
            statement.setLong(1, accountId);
            statement.executeUpdate();
        }
    }
}

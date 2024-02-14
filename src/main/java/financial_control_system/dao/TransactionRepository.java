package financial_control_system.dao;

import financial_control_system.dao.api.ITransactionRepository;
import financial_control_system.dao.model.Transaction;
import financial_control_system.dao.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionRepository implements ITransactionRepository {
    private static final String CREATE_QUERY = "INSERT into app.transaction(amount, description, account_id, transaction_id) VALUES(?, ?, ?, ?) ";
    private static final String GET_ALL_BY_ACCOUNT_ID_QUERY = "SELECT * FROM app.transaction WHERE account_id = ?";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM app.transaction WHERE transaction_id = ?";
    private static final String UPDATE_QUERY = "UPDATE app.transaction SET description = ? WHERE transaction_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM app.transaction WHERE transaction_id = ?";
    private final DbConnection connection;

    public TransactionRepository() {
        connection = new DbConnection();
    }

    @Override
    public void create(Transaction transaction) throws SQLException {
        try (Connection conn = connection.connect();
             PreparedStatement statement = conn.prepareStatement(CREATE_QUERY)) {
            statement.setBigDecimal(1, transaction.getAmount());
            statement.setString(2, transaction.getDescription());
            statement.setLong(3, transaction.getAccountId());
            statement.setObject(4, UUID.randomUUID());

            statement.executeUpdate();
        }
    }


    @Override
    public List<Transaction> getByAccountId(long accountId) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_ALL_BY_ACCOUNT_ID_QUERY)) {

            preparedStatement.setLong(1, accountId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId((UUID) resultSet.getObject("transaction_id"));
                    transaction.setDescription(resultSet.getString("description"));
                    transaction.setAmount(resultSet.getBigDecimal("amount"));
                    transaction.setAccountId(resultSet.getLong("account_id"));
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }

    @Override
    public Transaction getById(UUID id) throws SQLException {
        Transaction transaction = null;

        try (Connection conn = connection.connect();
             PreparedStatement statement = conn.prepareStatement(GET_BY_ID_QUERY)) {

            statement.setObject(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    transaction = new Transaction();
                    transaction.setTransactionId((UUID) resultSet.getObject("transaction_id"));
                    transaction.setDescription(resultSet.getString("description"));
                    transaction.setAmount(resultSet.getBigDecimal("amount"));
                    transaction.setAccountId(resultSet.getLong("account_id"));
                }
            }
        }

        return transaction;
    }
    @Override
    public void update(UUID id, Transaction transaction) throws SQLException {
        try (Connection conn = connection.connect();
             PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, transaction.getDescription());
            statement.setObject(2, id);

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Transaction transaction) throws SQLException {
        try (Connection conn = connection.connect();
             PreparedStatement statement = conn.prepareStatement(DELETE_QUERY)) {
            UUID transactionId = transaction.getTransactionId();
            statement.setObject(1, transactionId);
            statement.executeUpdate();
        }
    }
}

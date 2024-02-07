package financial_control_system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection implements AutoCloseable{
    private static final String URL = "jdbc:postgresql://localhost:5432/financial_control_system";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qsef1234";
    private static Connection connection;

    public Connection connect(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

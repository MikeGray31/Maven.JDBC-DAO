package ConnectionFact;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String URL = "jdbc:postgresql://localhost:5432/mike";
    public static final String USER = "mike";
    public static final String PASS = "password";

    public static Connection getConnection()
    {
        try {
            return DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException ex) {
            System.err.format("SQL State: %s\n%s\n", ex.getSQLState(), ex.getMessage());
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
    }
}

package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private Connection connection;

    public DBConnection() throws SQLException {
        connection = DriverManager.getConnection(
            "jdbc:postgresql://127.0.0.1/postgres",
            "postgres",
            "root" 
        );
    }

    public Connection getConnection() {
        return connection;
    }
}

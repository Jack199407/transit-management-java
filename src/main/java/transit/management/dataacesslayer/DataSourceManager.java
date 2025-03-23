package transit.management.dataacesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DataSourceManager is a Singleton class that manages database connections.
 * Implements the Singleton design pattern with thread safety.
 */
public class DataSourceManager {

    private static DataSourceManager instance;
    private static final String URL = "jdbc:mysql://localhost:3306/transit_management?zeroDateTimeBehavior=convertToNull&characterEncoding=utf8&allowMultiQueries=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Private constructor prevents instantiation
    private DataSourceManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }

    /**
     * Get the singleton instance of DataSourceManager.
     * Thread-safe lazy initialization.
     */
    public static synchronized DataSourceManager getInstance() {
        if (instance == null) {
            instance = new DataSourceManager();
        }
        return instance;
    }

    /**
     * Get a new database connection.
     *
     * @return Connection to the MySQL database.
     * @throws SQLException if connection fails.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


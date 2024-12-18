package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
    public static Connection getConnection() {
        // JDBC URL for SQL Server
        String url = "jdbc:sqlserver://localhost:1433;databaseName=dbJavaOPC;trustServerCertificate=true";

        // Database credentials
        String username = "sa";
        String password = "123456";

        // Connection object
        Connection connection = null;

        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish connection
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

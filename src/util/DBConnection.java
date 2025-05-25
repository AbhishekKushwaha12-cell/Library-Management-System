package util;
import java.sql.*;

    public class DBConnection {
        private static final String DB_URL = "jdbc:mysql://localhost:3306/library";
        private static final String USER = "your_db_username"; // Replace with your actual MySQL username
        private static final String PASS = "your_db_password"; // Replace with your actual MySQL password

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(DB_URL,USER, PASS);
        }
    }


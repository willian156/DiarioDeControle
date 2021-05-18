package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public DbConnection() {
    }

    public static Connection getConnectionSqlite() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:ProjetoDB.db");
        return connection;
    }
}

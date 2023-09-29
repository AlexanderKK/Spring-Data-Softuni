package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    private static Connection connection;
    private static final String USER = "root";
    private static final String PASSWORD = "impeachment*";
    private static final String DATABASE_NAME = "custom-orm";
    private static final String DATABASE_PARAMETERS =
            "?allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true&serverTimezone=UTC";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/%s%s";

    private Connector() {}

    private static void createConnection() throws SQLException {
        if(connection != null) {
            return;
        }

        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);

        String formattedJdbc = String.format(JDBC_URL, DATABASE_NAME, DATABASE_PARAMETERS);

        connection = DriverManager.getConnection(formattedJdbc, props);
    }

    public static Connection getConnection() throws SQLException {
        createConnection();

        return connection;
    }

}

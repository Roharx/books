package dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DbConnector {
    private static final String file = "resources/dblogin.properties";
    private SQLServerDataSource dataSource = null;

    public DbConnector(){
        Properties properties = getConnectionDetails();
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(properties.getProperty("name"));
        dataSource.setUser(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setServerName(properties.getProperty("server"));
        dataSource.setPortNumber(Integer.parseInt(properties.getProperty("port")));
        dataSource.setTrustServerCertificate(true);
    }

    public Connection createConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    private static Properties getConnectionDetails(){
        Properties properties = new Properties();

        try {
            FileInputStream sr = new FileInputStream(file);
            properties.load(sr);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties;
    }

}

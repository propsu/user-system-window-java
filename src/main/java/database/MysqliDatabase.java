package database;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MysqliDatabase implements Database{
    private String host, user, password, dbName;
    private int port = 3306;
    private HikariDataSource hikari;

    public MysqliDatabase(String host, String user, String password, String database) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.dbName = database;
        prepareConnection();
    }


    @Override
    public void prepareConnection() {
        hikari = new HikariDataSource();
        hikari.setDataSourceClassName("org.mariadb.jdbc.MariaDbDataSource");
        hikari.addDataSourceProperty("serverName", host);
        hikari.addDataSourceProperty("user", user);
        hikari.addDataSourceProperty("port", port);
        hikari.addDataSourceProperty("password", password);
        hikari.addDataSourceProperty("databaseName", dbName);
    }

    @Override
    public void closeConnection() { hikari.close(); }

    @Override
    public Connection connect() throws SQLException { return hikari.getConnection(); }
}

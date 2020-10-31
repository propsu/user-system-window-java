package database;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {
    void prepareConnection();
    void closeConnection();

    Connection connect() throws SQLException;
}

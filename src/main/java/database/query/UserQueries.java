package database.query;

import database.MysqliDatabase;
import user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQueries extends  Queries{

    public UserQueries(MysqliDatabase db){
        super(db);
    }

    public User insertUser(String username, String password) throws SQLException {
        Connection connection = db.connect();
        PreparedStatement statement = connection.prepareStatement("INSERT IGNORE INTO users VALUES (null, ?, ?, now(), now())");
        statement.setString(1, username);
        statement.setString(2, password);
        statement.execute();
        closeQuery(statement, connection);
        return getUser(username);
    }

    public User changeUserPassword(User user, String newPassword) throws SQLException {
            Connection connection = db.connect();
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET password=? WHERE id=?");
            statement.setString(1, newPassword);
            statement.setInt(2, user.getId());
            statement.execute();
            closeQuery(statement, connection);

            return getUser(user.getId());
    }

    public User deleteUser(User user) throws SQLException {
        Connection connection = db.connect();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
        statement.setInt(1, user.getId());
        statement.execute();
        closeQuery(statement, connection);

        return getUser(user.getId());

    }

    public void closeQuery(PreparedStatement statement, Connection connection) throws SQLException {
        statement.clearParameters();
        statement.close();
        connection.close();
    }

    private User prepareGetUserStatement(String sql, String value){
        try{
            Connection connection = db.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, value);
            ResultSet result = statement.executeQuery();
            closeQuery(statement, connection);

            if(!result.next())
                return null;

            return new User(
                    result.getInt("id"),
                    result.getString("username"),
                    result.getString("password"),
                    result.getDate("created_at"),
                    result.getDate("modified_at")
            );

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public User getUser(String username){
        return  prepareGetUserStatement("SELECT * FROM users WHERE username=?", username);
    }
    public User getUser(int id){
        return  prepareGetUserStatement("SELECT * FROM users WHERE id=?", String.valueOf(id));
    }
}

import database.MysqliDatabase;
import database.query.UserQueries;

import java.awt.*;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}

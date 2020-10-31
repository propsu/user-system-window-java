package user;
import database.MysqliDatabase;
import database.query.UserQueries;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserSystem {
    private User currentUser = null;
    private final String regex = "^[a-zA-Z0-9]*$";
    private UserQueries userQueries;
    private MysqliDatabase db;

    public UserSystem() {
        db = new MysqliDatabase("localhost", "root", "", "java");
        userQueries = new UserQueries(db);
    }

    public Map<String, String> logIn(String username, String password) {
        User user = userQueries.getUser(username);
        Map<String, String> errors = new HashMap<>();

        if (user != null && user.getPassword().equals(password))
            currentUser = user;
        else if (user == null)
            errors.put("username", "Nie ma takiego użytkownika");
        else
            errors.put("password", "Błędne hasło");

        return errors;
    }

    public void logOut(){
        currentUser = null;
    }

    public Map registerUser(String username, String password, String repassword) throws SQLException {
        User user = userQueries.getUser(username);
        Map<String, String> errors = new HashMap<>();

        if(user == null){
            String passwordError = validatePassword(password);
            if(passwordError != null)
                errors.put("password", passwordError);

            if(!password.equals(repassword))
                errors.put("repassword", "Hasła nie są identyczne");

            String usernameError = validateUsername(username);
            if(usernameError != null)
                errors.put("username", usernameError);
        } else errors.put("username", "Użytkownik istnieje");

        if(errors.size() == 0){
            userQueries.insertUser(username, password);
        }

        return errors;
    }

    public Map changePassword(String currentPassword, String newPassword, String repassword) throws SQLException {
        Map<String, String> errors = new HashMap<>();

        if(!currentPassword.equals(getCurrentUser().getPassword()))
            errors.put("currentPassword", "Nieprawidłowe hasło");

        String passwordError = validatePassword(newPassword);
        if(passwordError != null)
            errors.put("newPassword", passwordError);

        if(!newPassword.equals(repassword))
            errors.put("repassword", "Hasła nie są identyczne");

        if(errors.size() == 0){
            userQueries.changeUserPassword(getCurrentUser(), newPassword);
        }

        return errors;
    }

    public String removeAccount() throws SQLException {
        userQueries.deleteUser(getCurrentUser());
        logOut();
        return "Usunięto konto";
    }

    private String validatePassword(String password){
        if(!password.matches(regex))
            return  "Hasło może zawierać tylko znaki alfanumeryczne";

        if(password.length() < 6)
            return "Hasło musi mieć co najmniej 6 znaków";

        if(password.length() > 16)
            return "Hasło może mieć maksymalnie 16 znaków";

        return null;
    }

    private String validateUsername(String username){
        if(!username.matches(regex))
            return  "Nazwa użytkownika może zawierać tylko znaki alfanumeryczne";

        if(username.length() < 4)
            return "Nazwa użytkownika musi mieć co najmniej 4 znaki";

        if(username.length() > 10)
            return "Nazwa użytkownika może mieć maksymalnie 10 znaków";

        return null;
    }

    public User getCurrentUser() { return currentUser; }
}

package tab.form;

import user.UserSystem;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class RegisterForm extends Form{
    public RegisterForm(UserSystem userSystem){
        super(userSystem);
        container = createForm();
    }

    @Override
    public boolean submit() throws SQLException {
        clearLabels();
        String username = getValue("username");
        String password = getValue("password");
        String repassword = getValue("repassword");

        Map errors = userSystem.registerUser(username, password, repassword);

        if(errors.size() == 0){
            clear();
            return true;
        }
        clearInputs("password", "repassword");
        errors.forEach((k, v)->{
            setErrorLabel(k.toString(), v.toString());
        });

        return false;
    }

    @Override
    public JPanel createForm() {
        JPanel container = new JPanel(null);
        container.setBounds(125, 50, 250, 208);

        JLabel usernameLabel = createInputLabel("Nazwa użytkownika");
        usernameLabel.setBounds(0, 0, 250, 16);

        JTextField usernameInput = createInput("username");
        usernameInput.setBounds(0, 16, 250, 25);

        JLabel usernameError = createErrorLabel("username");
        usernameError.setBounds(0, 41, 250,15);

        JLabel passwordLabel = createInputLabel("Hasło");
        passwordLabel.setBounds(0, 56, 250, 16);

        JTextField passwordInput = createPasswordInput("password");
        passwordInput.setBounds(0, 72, 250, 25);

        JLabel passwordError = createErrorLabel("password");
        passwordError.setBounds(0, 97, 250,20);

        JLabel repasswordLabel = createInputLabel("Powtórz hasło");
        repasswordLabel.setBounds(0, 117, 250, 16);

        JTextField repasswordInput = createPasswordInput("repassword");
        repasswordInput.setBounds(0, 133, 250, 25);

        JLabel repasswordError = createErrorLabel("repassword");
        repasswordError.setBounds(0, 158, 250,20);

        JButton submitButton = createSubmit("Zarejestruj się");
        submitButton.setBounds(0, 178, 250, 30);

        container.add(usernameLabel);
        container.add(usernameInput);
        container.add(usernameError);
        container.add(passwordLabel);
        container.add(passwordInput);
        container.add(passwordError);
        container.add(repasswordLabel);
        container.add(repasswordInput);
        container.add(repasswordError);
        container.add(submitButton);

        return container;
    }
}

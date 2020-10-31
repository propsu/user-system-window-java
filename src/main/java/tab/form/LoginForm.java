package tab.form;

import user.UserSystem;

import javax.swing.*;
import java.util.Map;

public class LoginForm extends Form{
    public LoginForm(UserSystem userSystem){
        super(userSystem);
        container = createForm();
    }

    @Override
    public boolean submit() {
        clearLabels();
        String username = getValue("username");
        String password = getValue("password");

        Map errors = userSystem.logIn(username, password);

        if(errors.size() == 0){
            clear();
            return true;
        }
        clearInputs("password");
        errors.forEach((k, v)->{
            setErrorLabel(k.toString(), v.toString());
        });

        return false;
    }

    @Override
    public JPanel createForm() {
        JPanel container = new JPanel(null);
        container.setBounds(125, 50, 250, 150);

        JLabel newUserLabel = createSuccessLabel("newUser");
        newUserLabel.setBounds(0, -15, 250, 16);

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

        JButton submitButton = createSubmit("Zaloguj się");
        submitButton.setBounds(0, 120, 250, 30);

        container.add(newUserLabel);
        container.add(usernameLabel);
        container.add(usernameInput);
        container.add(usernameError);
        container.add(passwordLabel);
        container.add(passwordInput);
        container.add(passwordError);
        container.add(submitButton);

        return container;
    }
}

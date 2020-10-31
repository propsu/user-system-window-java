package tab;

import tab.form.LoginForm;
import user.UserSystem;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginTab extends Tab {
    private LoginForm loginForm;
    private JButton registerTabButton;

    public LoginTab(String name, UserSystem userSystem, ActionListener listener){
        super(name);
        loginForm = new LoginForm(userSystem);

        registerTabButton = new JButton("Rejestracja");
        registerTabButton.setBounds(125, 205, 250, 30);

        registerTabButton.addActionListener(listener);
        loginForm.getSubmitButton().addActionListener(listener);

        add(loginForm.getContainer());
        add(registerTabButton);
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public JButton getRegisterTabButton() {
        return registerTabButton;
    }
}

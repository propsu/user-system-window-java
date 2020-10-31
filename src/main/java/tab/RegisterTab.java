package tab;

import tab.form.RegisterForm;
import user.UserSystem;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RegisterTab extends Tab {
    private RegisterForm registerForm;
    private JButton backTabButton;

    public RegisterTab(String name, UserSystem userSystem, ActionListener listener){
        super(name);
        registerForm = new RegisterForm(userSystem);

        backTabButton = new JButton("Powrót");
        backTabButton.setBounds(125, 258, 250, 30);

        backTabButton.addActionListener(listener);
        registerForm.getSubmitButton().addActionListener(listener);

        add(registerForm.getContainer());
        add(backTabButton);
    }

    public RegisterForm getRegisterForm() { return registerForm; }

    public JButton getBackTabButton() { return backTabButton; }

}

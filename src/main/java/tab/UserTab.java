package tab;

import user.UserSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserTab extends Tab{
    private UserSystem userSystem;
    private JButton logoutButton;
    private JButton deleteAccountButton;
    private JButton changePasswordTabButton;
    private JLabel welcomeLabel;

    public UserTab(String name, UserSystem userSystem, ActionListener listener){
        super(name);
        this.userSystem = userSystem;
        welcomeLabel = new JLabel();
        welcomeLabel.setBounds(125, 30, 250, 30);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        changePasswordTabButton = new JButton("Zmień hasło");
        changePasswordTabButton.setBounds(125, 65, 250, 30);

        deleteAccountButton = new JButton("Usuń konto");
        deleteAccountButton.setBounds(125, 100, 250, 30);

        logoutButton = new JButton("Wyloguj się");
        logoutButton.setBounds(125, 135, 250, 30);

        add(welcomeLabel);
        add(changePasswordTabButton);
        add(deleteAccountButton);
        add(logoutButton);

        logoutButton.addActionListener(listener);
        deleteAccountButton.addActionListener(listener);
        changePasswordTabButton.addActionListener(listener);
    }

    public void updateTab(){
        welcomeLabel.setText("Witaj " + userSystem.getCurrentUser().getUsername());
    }

    public JButton getLogoutButton() { return logoutButton; }

    public JButton getDeleteAccountButton() { return deleteAccountButton; }

    public JButton getChangePasswordTabButton() { return changePasswordTabButton; }
}

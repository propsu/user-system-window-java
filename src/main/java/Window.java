
import tab.*;
import tab.form.ChangePasswordForm;
import tab.form.LoginForm;
import tab.form.RegisterForm;
import user.UserSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Window extends JFrame implements ActionListener {
    private Map<String, Tab> tabs = new HashMap<>();
    private UserSystem userSystem = new UserSystem();
    private LoginForm loginForm;
    private RegisterForm registerForm;
    private LoginTab loginTab;
    private RegisterTab registerTab;
    private UserTab userTab;
    private ChangePasswordTab changePasswordTab;
    private ChangePasswordForm changePasswordForm;

    public Window(){
        super("System użytkowników");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setSize(new Dimension(500, 500));
        setBackground(new Color(250, 20, 70));

        registerTab = new RegisterTab("registerTab", userSystem,this);
        registerForm = registerTab.getRegisterForm();

        loginTab = new LoginTab("loginTab", userSystem, this);
        loginForm = loginTab.getLoginForm();

        userTab = new UserTab("userTab", userSystem, this);
        changePasswordTab = new ChangePasswordTab("changePasswordTab", userSystem, this);
        changePasswordForm = changePasswordTab.getChangePasswordForm();

        addTab(loginTab);
        addTab(registerTab);
        addTab(userTab);
        addTab(changePasswordTab);

        loginTab.hideTab(false);
    }

    private void addTab(Tab tab){
        tabs.put(tab.getName(), tab);
        add(tab);
    }

    private void logIn(){

        if(loginForm.submit()) {
            switchTab("userTab");
            userTab.updateTab();
        }
    }

    private void logOut(){
        userSystem.logOut();
        switchTab("loginTab");
    }

    private void register() throws SQLException {
        if(registerForm.submit()){
            switchTab("loginTab");
            loginForm.setSuccessLabel("newUser", "Pomyślnie założono konto");
        }
    }
    private void deleteAccount() throws SQLException {
        int result = JOptionPane.showConfirmDialog(this, "Na pewno chcesz usunąć konto?", "Potwierdź usunięcie konta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            userSystem.removeAccount();
            switchTab("loginTab");
        }
    }

    private void switchTab(String visibleTab) {
        if(tabs.containsKey(visibleTab)){
            for(Tab tab : tabs.values()){
                tab.setVisible(false);
                tab.enableComponents(false);
            }

            Tab tab = tabs.get(visibleTab);
            tab.setVisible(true);
            tab.enableComponents(true);
        } else
            System.out.println("Brak panelu: " + visibleTab);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == loginForm.getSubmitButton())
            logIn();

        if(source == userTab.getLogoutButton())
            logOut();

        if(source == loginTab.getRegisterTabButton()) {
            switchTab("registerTab");
            loginForm.clear();
        }

        if(source == registerTab.getBackTabButton())
            switchTab("loginTab");

        if(source == changePasswordTab.getBackTabButton())
            switchTab("userTab");

        if(source == userTab.getChangePasswordTabButton())
            switchTab("changePasswordTab");

        try {
            if(source == registerForm.getSubmitButton())
                register();

            if(source == userTab.getDeleteAccountButton())
                deleteAccount();

            if(source == changePasswordForm.getSubmitButton())
                changePasswordForm.submit();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

}

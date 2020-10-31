package tab.form;

import user.UserSystem;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class ChangePasswordForm extends Form{
    public ChangePasswordForm(UserSystem userSystem){
        super(userSystem);
        container = createForm();
    }

    @Override
    public boolean submit() throws SQLException {
        String currentPassword = getValue("currentPassword");
        String newPassword = getValue("newPassword");
        String repassword = getValue("repassword");
        clear();

        Map errors = userSystem.changePassword(currentPassword, newPassword, repassword);

        if(errors.size() == 0){
            setSuccessLabel("changedPassword", "Pomyślnie zmieniono hasło");
            return true;
        }

        errors.forEach((k, v)->{
            setErrorLabel(k.toString(), v.toString());
        });

        clearSuccessLabels();

        return false;
    }

    @Override
    public JPanel createForm() {
        JPanel container = new JPanel(null);
        container.setBounds(125, 50, 250, 229);

        JLabel successChangeLabel = createSuccessLabel("changedPassword");
            successChangeLabel.setBounds(0, 0, 250, 16);

        JLabel currentPasswordLabel = createInputLabel("Aktualne hasło");
        currentPasswordLabel.setBounds(0, 16, 250, 16);

        JTextField currentPasswordInput = createPasswordInput("currentPassword");
        currentPasswordInput.setBounds(0, 32, 250, 25);

        JLabel currentPasswordError = createErrorLabel("currentPassword");
        currentPasswordError.setBounds(0, 57, 250,20);

        JLabel newPasswordLabel = createInputLabel("Nowe hasło");
        newPasswordLabel.setBounds(0, 77, 250, 16);

        JTextField newPasswordInput = createPasswordInput("newPassword");
        newPasswordInput.setBounds(0, 93, 250, 25);

        JLabel newPasswordError = createErrorLabel("newPassword");
        newPasswordError.setBounds(0, 118, 250,20);

        JLabel repasswordLabel = createInputLabel("Powtórz hasło");
        repasswordLabel.setBounds(0, 138, 250, 16);

        JTextField repasswordInput = createPasswordInput("repassword");
        repasswordInput.setBounds(0, 154, 250, 25);

        JLabel repasswordError = createErrorLabel("repassword");
        repasswordError.setBounds(0, 179, 250,20);

        JButton submitButton = createSubmit("Zmień hasło");
        submitButton.setBounds(0, 199, 250, 30);

        container.add(successChangeLabel);
        container.add(currentPasswordLabel);
        container.add(currentPasswordInput);
        container.add(currentPasswordError);
        container.add(newPasswordLabel);
        container.add(newPasswordInput);
        container.add(newPasswordError);
        container.add(repasswordLabel);
        container.add(repasswordInput);
        container.add(repasswordError);
        container.add(submitButton);

        return container;
    }
}

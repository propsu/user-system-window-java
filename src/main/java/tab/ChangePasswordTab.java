package tab;

import tab.form.ChangePasswordForm;
import user.UserSystem;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ChangePasswordTab extends Tab {
    private ChangePasswordForm changePasswordForm;
    private JButton backTabButton;

    public ChangePasswordTab(String name, UserSystem userSystem, ActionListener listener){
        super(name);
        changePasswordForm = new ChangePasswordForm(userSystem);

        backTabButton = new JButton("Powrót");
        backTabButton.setBounds(125, 284, 250, 30);

        backTabButton.addActionListener(listener);
        changePasswordForm.getSubmitButton().addActionListener(listener);

        add(changePasswordForm.getContainer());
        add(backTabButton);
    }

    public ChangePasswordForm getChangePasswordForm() { return changePasswordForm; }

    public JButton getBackTabButton() { return backTabButton; }

}

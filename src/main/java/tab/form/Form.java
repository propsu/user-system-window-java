package tab.form;

import user.UserSystem;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Form implements FormInterface {
    protected Map<String, JTextField> inputs = new HashMap<>();
    protected Map<String, JLabel> errorLabels = new HashMap<>();
    protected Map<String, JLabel> successLabels = new HashMap<>();
    protected JButton submitButton;
    protected JPanel container;
    protected UserSystem userSystem;

    public Form(UserSystem userSystem){
        this.userSystem = userSystem;
    }

    public void clearInputs(String... inputsName){
        for (String inputName : inputsName){
            JTextField input = getInput(inputName);
            input.setText("");
        }
    }

    public void clearErrorsLabels(){
        for(JLabel error : errorLabels.values()){
            error.setText("");
        }
    }

    public void clearSuccessLabels(){
        for(JLabel success : successLabels.values()){
            success.setText("");
        }
    }

    public void clearLabels() {
        clearSuccessLabels();
        clearErrorsLabels();
    }

    public void clear(){
        for (JTextField input : inputs.values()){ input.setText(""); }
        clearErrorsLabels();
        clearSuccessLabels();
    }

    public JTextField createInput(String name){
        JTextField input = new JTextField();
        inputs.put(name, input);
        return input;
    }
    public JPasswordField createPasswordInput(String name){
        JPasswordField input = new JPasswordField();
        inputs.put(name, input);
        return input;
    }

    public JLabel createInputLabel(String text){
        JLabel label = new JLabel(text);
        label.setVerticalAlignment(JLabel.BOTTOM);
        return label;
    }

    public JButton createSubmit(String text){
        JButton submit = new JButton(text);
        submitButton = submit;
        return submit;
    }

    public JLabel createErrorLabel(String name){
        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(new Color(255, 0, 0));
        errorLabel.setFont(new Font("Arial", Font.BOLD, 14));
        errorLabels.put(name, errorLabel);
        return errorLabel;
    }

    public JLabel createSuccessLabel(String name){
        JLabel successLabel = new JLabel();
        successLabel.setForeground(new Color(0, 240, 0));
        successLabel.setFont(new Font("Arial", Font.BOLD, 14));
        successLabels.put(name, successLabel);
        return successLabel;
    }

    public JTextField getInput(String name){
        return inputs.get(name);
    }

    public String getValue(String name){
        return  getInput(name).getText();
    }

    public void setErrorLabel(String name, String text){
        getErrorLabel(name).setText(text);
    }
    public void setSuccessLabel(String name, String text){
        getSuccessLabel(name).setText(text);
    }
    public void setInput(String name, String text){
        getInput(name).setText(text);
    }

    public JLabel getErrorLabel(String name){
        return errorLabels.get(name);
    }
    public JLabel getSuccessLabel(String name){
        return successLabels.get(name);
    }

    public JPanel getContainer() {
        return container;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }
}

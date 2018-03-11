package gui.base_gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NewPlayerPanel extends JPanel {

    private BaseLayeredPane baseLayeredPane;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField passwordConfirmField;
    private JButton confirmButton;
    private JButton cancelButton;

    public NewPlayerPanel(BaseLayeredPane baseLayeredPane)
    {
        this.baseLayeredPane = baseLayeredPane;

        this.setPreferredSize(this.baseLayeredPane.getMainFrame().getPreferredSize());
    }

    public void init()
    {
        this.usernameField = new JTextField();
        this.passwordField = new JPasswordField();
        this.passwordConfirmField = new JPasswordField();
        this.confirmButton = new JButton("Confirm");
        this.cancelButton = new JButton("Cancel");

        // add listeners

        this.add(this.usernameField);
        this.add(this.passwordField);
        this.add(this.passwordConfirmField);
        this.add(this.confirmButton);
    }

    public void reinit()
    {
        this.removeAll();

        this.init();
    }



    public boolean checkIfUsernameExists(String username)
    {
        return true;
    }

    public boolean checkIfPasswordsMatch()
    {
        if(this.passwordField.getText().equals(this.passwordConfirmField.getText()))
        {
            return true;
        }

        return false;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JPasswordField getPasswordConfirmField() {
        return passwordConfirmField;
    }

    public void setPasswordConfirmField(JPasswordField passwordConfirmField) {
        this.passwordConfirmField = passwordConfirmField;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public void setConfirmButton(JButton confirmButton) {
        this.confirmButton = confirmButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
}

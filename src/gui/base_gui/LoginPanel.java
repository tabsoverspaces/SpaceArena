package gui.base_gui;

import engine_classes.player.Player;

import javax.swing.*;

public class LoginPanel extends JPanel {

    // parent holder
    private BaseLayeredPane baseLayeredPane;

    // components
    private JButton loginButton;
    private JButton newPlayerButton;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JLabel welcomeLabel;
    private JLabel iconLabel;



    public LoginPanel(BaseLayeredPane baseLayeredPane)
    {
        this.baseLayeredPane = baseLayeredPane;
        //setup dimensions and layouts and shits here

        this.setPreferredSize(this.baseLayeredPane.getMainFrame().getPreferredSize());
    }

    public void init()
    {
        this.loginButton = new JButton("Login");
        this.newPlayerButton = new JButton("Register new player");

        this.usernameField = new JTextField();
        this.passwordField = new JPasswordField();

        this.welcomeLabel = new JLabel("Welcome to SpaceArena");
        this.iconLabel = new JLabel();

        // add listeners to elements


        this.add(this.loginButton);
        this.add(this.newPlayerButton);
        this.add(this.usernameField);
        this.add(this.passwordField);
        this.add(this.welcomeLabel);
        this.add(this.iconLabel);
    }

    public void reinit()
    {
        this.removeAll();
        this.init();
    }

    private Player loadPlayerFromDisk(String username)
    {
        Player player = new Player();

        return player;
    }

    // IMPLEMENT THIS
    private boolean checkIfUserExists(String username)
    {
        return false;
    }

    // IMPLEMENT THIS
    private boolean checkIfPasswordMatches(String username, String password)
    {
        return false;
    }

    public boolean checkUsernameLength()
    {
        if(this.usernameField.getText().length() < 1)
        return false;

        return true;
    }

    public boolean checkPasswordLength()
    {
        if(this.passwordField.getText().length() < 1)
        {
            return false;
        }

        return true;
    }

    public boolean checkIfPasswordMatches()
    {
        return this.checkIfPasswordMatches(this.usernameField.getText(), this.passwordField.getText());
    }

    public Player loadPlayerFromDisk()
    {
        return this.loadPlayerFromDisk(this.usernameField.getText());
    }

    public boolean checkIfUserExists()
    {
        return this.checkIfUserExists(this.usernameField.getText());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JButton getNewPlayerButton() {
        return newPlayerButton;
    }

    public void setNewPlayerButton(JButton newPlayerButton) {
        this.newPlayerButton = newPlayerButton;
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

    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    public void setWelcomeLabel(JLabel welcomeLabel) {
        this.welcomeLabel = welcomeLabel;
    }

    public JLabel getIconLabel() {
        return iconLabel;
    }

    public void setIconLabel(JLabel iconLabel) {
        this.iconLabel = iconLabel;
    }
}

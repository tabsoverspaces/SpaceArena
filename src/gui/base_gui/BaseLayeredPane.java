package gui.base_gui;

import gui.base_gui.main_game_panel.ContentHolderLayeredPane;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BaseLayeredPane extends JLayeredPane {

    // parent holder frame
    private MainFrame mainFrame;

    // components of the layered pane
    private LoginPanel loginPanel;
    private NewPlayerPanel newPlayerPanel;

    private ContentHolderLayeredPane contentHolderLayeredPane;

    // minor components

    // stat holder
    private int componentCounter = 0 ;

    public BaseLayeredPane(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;

        this.setPreferredSize(this.mainFrame.getPreferredSize());
    }
    public void init()
    {
        this.loginPanel = new LoginPanel(this);
        this.newPlayerPanel = new NewPlayerPanel(this);

        this.loginPanel.reinit();
        this.newPlayerPanel.reinit();

        this.add(this.loginPanel, new Integer(0));
        this.add(this.newPlayerPanel, new Integer(1));

        this.loginPanel.setVisible(true);
        this.newPlayerPanel.setVisible(false);

        // add listeners
        this.loginPanel.getLoginButton().addActionListener((ActionEvent e)->{

            if(!BaseLayeredPane.this.loginPanel.checkUsernameLength())
            {
                JOptionPane.showMessageDialog(null,
                        "Please enter a username", "Login error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(!BaseLayeredPane.this.loginPanel.checkPasswordLength())
            {
                JOptionPane.showMessageDialog(null,
                        "Please enter a password", "Login error",
                        JOptionPane.ERROR_MESSAGE);

            }
            else if(!BaseLayeredPane.this.loginPanel.checkIfUserExists())
            {
                JOptionPane.showMessageDialog(null,
                        "The user doesn't exist", "Login error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(!BaseLayeredPane.this.loginPanel.checkIfPasswordMatches())
            {
                JOptionPane.showMessageDialog(null,
                        "The password you entered doesn't match", "Login error",
                        JOptionPane.ERROR_MESSAGE);
            }

            else
            {
                // login!
                // 1. create player from username and load stats from disk
                // 2. create main game panel with player object as parameter
                // 3. initialize main game panel
                // 4. start game

            }
        });

        this.loginPanel.setVisible(true);
        this.newPlayerPanel.setVisible(false);
    }

    public MainFrame getMainFrame()
    {
        return this.mainFrame;
    }


    public void reinitialize()
    {
        this.removeAll();
        this.init();
    }


    // mehod used to load a game with a successfully logged player


}

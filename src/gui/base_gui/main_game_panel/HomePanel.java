package gui.base_gui.main_game_panel;

import gui.base_gui.MainFrame;

import javax.swing.*;

public class HomePanel extends JPanel {

    // basic components
    private JButton arenaButton;
    private JButton armoryButton;
    private JButton shopPanel;
    private JButton spaceHuntButton;

    private MainFrame mainFrame;

    public HomePanel()
    {

    }

    public void init()
    {
        this.arenaButton = new JButton("Arena");
        this.armoryButton = new JButton("Armory");
        this.shopPanel = new JButton("Shop");
        this.spaceHuntButton = new JButton("Space Hunt");
    }


}

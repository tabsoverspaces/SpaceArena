package gui.base_gui.main_game_panel;

import javax.swing.*;

public class HomePanel extends JPanel {

    // basic components
    private JButton arenaButton;
    private JButton armoryButton;
    private JButton shopPanel;
    private JButton spaceHuntButton;

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

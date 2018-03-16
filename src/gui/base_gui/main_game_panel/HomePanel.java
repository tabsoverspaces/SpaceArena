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

    private ContentHolderLayeredPane contentHolderLayeredPane;

    public HomePanel(ContentHolderLayeredPane contentHolderLayeredPane)
    {
        this.contentHolderLayeredPane = contentHolderLayeredPane;
    }

    public void init()
    {
        this.setLayout(null);
        this.setBounds(0, 0, MainFrame.width, MainFrame.height);

        this.arenaButton = new JButton("Arena");
        this.armoryButton = new JButton("Armory");
        this.shopPanel = new JButton("Shop");
        this.spaceHuntButton = new JButton("Space Hunt");

        this.arenaButton.setBounds(100, 100, 100 , 30);
        this.armoryButton.setBounds(100, 200, 100 , 30);
        this.shopPanel.setBounds(100, 300, 100 , 30);
        this.spaceHuntButton.setBounds(100, 400, 100 , 30);



        this.add(arenaButton);
        this.add(armoryButton);
        this.add(shopPanel);
        this.add(spaceHuntButton);
    }

    public JButton getArenaButton()
    {
        return this.arenaButton;
    }

    public JButton getSpaceHuntButton(){return this.spaceHuntButton;}

}

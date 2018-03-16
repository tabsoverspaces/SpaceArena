package gui.base_gui.main_game_panel;

import engine_classes.battles.BattleAI;
import engine_classes.battles.BattlePVP;
import engine_classes.player.Player;
import gui.base_gui.BaseLayeredPane;
import gui.base_gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ContentHolderLayeredPane extends JLayeredPane {

    private Player currentlyLoggedPlayer;

    private BaseLayeredPane baseLayeredPane;

    // components to hold
    private HomePanel homePanel;
    private ShopPanel shopPanel;
    private ArenaPanel arenaPanel;
    private ArmoryPanel armoryPanel;
    private SpaceHuntPanel spaceHuntPanel;

    private BattlePanel battlePanel;

    public ContentHolderLayeredPane(Player player, BaseLayeredPane b)
    {
        this.baseLayeredPane = b;

        this.currentlyLoggedPlayer = player;

    }

    public void init()
    {
        this.setLayout(null);
        this.setBounds(0, 0, MainFrame.width, MainFrame.height);

        this.homePanel = new HomePanel(this);
        this.shopPanel = new ShopPanel(this);
        this.arenaPanel = new ArenaPanel(this);
        this.armoryPanel = new ArmoryPanel(this);
        this.spaceHuntPanel = new SpaceHuntPanel(this);

        this.homePanel.init();
        this.shopPanel.init();
        this.arenaPanel.init();
        this.armoryPanel.init();
        this.spaceHuntPanel.init();

        this.add(this.homePanel, new Integer(0));
        this.add(this.shopPanel, new Integer(1));
        this.add(this.arenaPanel, new Integer(2));
        this.add(this.armoryPanel, new Integer(3));
        this.add(this.spaceHuntPanel, new Integer(4));

        // add listeners and all
        this.homePanel.getArenaButton().addActionListener((ActionEvent e)->{

            // start a new battlepanel and a battle

            // create AI player
            Player ai = new Player();

            // create battle first
            BattleAI battle = new BattleAI(this.currentlyLoggedPlayer, ai);

            // create battle panel
            this.battlePanel = new BattlePanel(battle);
            this.add(this.battlePanel, new Integer(1));

            this.hideAllComponents();
            this.setLayer(this.battlePanel, 10);
            this.battlePanel.setVisible(true);

            Thread t = new Thread(this.battlePanel);
            this.battlePanel.startBattle();
            t.start();

        });

        this.homePanel.getSpaceHuntButton().addActionListener((ActionEvent e)->{

            // start a new battlepanel and a battle

            // create AI player
            Player ai = new Player();

            // create battle first
            BattlePVP battle = new BattlePVP(this.currentlyLoggedPlayer, ai);

            // create battle panel
            this.battlePanel = new BattlePanel(battle);
            this.add(this.battlePanel, new Integer(1));

            this.hideAllComponents();
            this.setLayer(this.battlePanel, 10);
            this.battlePanel.setVisible(true);

            Thread t = new Thread(this.battlePanel);
            this.battlePanel.startBattle();
            t.start();

        });

        this.repaint();
        this.revalidate();

    }

    public void hideAllComponents()
    {
        for(int i = 0 ;i < this.getComponentCount(); i++)
        {
            Component c = this.getComponent(i);

            c.setVisible(false);
        }
    }


    public Player getCurrentlyLoggedPlayer()
    {
        return this.currentlyLoggedPlayer;
    }
}

package gui.base_gui.main_game_panel;

import engine_classes.Player;

import javax.swing.*;

public class ContentHolderLayeredPane extends JLayeredPane {

    private Player currentlyLoggedPlayer;

    // components to hold
    private HomePanel homePanel;
    private ShopPanel shopPanel;
    private ArenaPanel arenaPanel;
    private ArmoryPanel armoryPanel;
    private SpaceHuntPanel spaceHuntPanel;

    public ContentHolderLayeredPane(Player player)
    {
        this.currentlyLoggedPlayer = player;

    }

    public void init()
    {
        this.homePanel = new HomePanel();
        this.shopPanel = new ShopPanel();
        this.arenaPanel = new ArenaPanel();
        this.armoryPanel = new ArmoryPanel();
        this.spaceHuntPanel = new SpaceHuntPanel();

        this.homePanel.init();
        this.shopPanel.init();
        this.arenaPanel.init();
        this.armoryPanel.init();
        this.spaceHuntPanel.init();
    }

}

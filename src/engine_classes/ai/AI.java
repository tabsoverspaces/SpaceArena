package engine_classes.ai;

import engine_classes.battles.Battle;
import gui.battle_gui.Battleship;

public class AI {

    // battle and player related variables
    private Battle battle;
    private Battleship shipAI;
    private Battleship shipPlayer;

    // move cooldown
    private MoveCooldown moveCooldownManager;

    // dynamic variables
    private Strategy currentGameplayStrategy;


    public AI()
    {
        this.currentGameplayStrategy = Strategy.DEFENDING;

        this.moveCooldownManager = new MoveCooldown();
    }

    public AI(Battle battle)
    {
        this();

        this.battle = battle;

        this.shipAI = battle.getBattleship2();
        this.shipPlayer = battle.getBattleship1();

    }

    // AI related methods
    public void updateAI()
    {
        this.moveCooldownManager.update();
    }

    // movement methods
    public void moveUp()
    {
        this.shipAI.moveUp();
    }
    public void moveDown()
    {
        this.shipAI.moveDown();
    }
    public void moveLeft()
    {
        this.shipAI.moveLeft();
    }

    public void moveRight()
    {
        this.shipAI.moveRight();
    }


}

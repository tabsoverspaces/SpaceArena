package engine_classes.ai_control;

import gui.battle_gui.Battle;
import gui.battle_gui.Battleship;

public class AI {

    private Battle battle;
    private Battleship shipAI;
    private Battleship shipPlayer;

    // move cooldown
    private MoveCooldown moveCooldownManager;


    public AI()
    {
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

}

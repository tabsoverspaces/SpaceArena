package engine_classes.ai;

import engine_classes.battles.Battle;
import engine_classes.battles.BattleAI;

public abstract class MovementManager {

    private BattleAI battle;

    private MoveCooldown moveCooldownHandler;

    private MovementManager()
    {
        this.moveCooldownHandler = new MoveCooldown();
    }


    public MovementManager(BattleAI battle)
    {
        this();

        this.battle = battle;
    }

    public void move()
    {

        if(!this.moveCooldownHandler.isMoveOnCooldown())
        {
            this.generateMovement();
            this.moveCooldownHandler.move();
        }
    }


    public abstract void generateMovement();

    public Battle getBattle()
    {
        return this.battle;
    }


}

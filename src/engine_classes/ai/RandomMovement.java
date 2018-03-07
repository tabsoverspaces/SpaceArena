package engine_classes.ai;

import engine_classes.battles.Battle;
import engine_classes.battles.BattleAI;

public class RandomMovement extends MovementManager
{



    public RandomMovement(BattleAI battle)
    {
        super(battle);
    }

    @Override
    public void generateMovement() {

        int startingX = this.getBattle().getBattleship2().getX();
        int startingY = this.getBattle().getBattleship2().getY();

        int deltaX = this.generateRandomX(startingX);
        int deltaY = this.generateRandomY(startingY);

        this.getBattle().getBattleship2().setX(startingX + deltaX);
        this.getBattle().getBattleship2().setY(startingY + deltaY);
    }

    private int generateRandomX(int startingX)
    {
        int delta = 0;



        return delta;
    }

    private int generateRandomY(int startingY)
    {
        int delta = 0;

        return delta;
    }
}

package engine_classes.ai;

import engine_classes.battles.Battle;
import engine_classes.battles.BattleAI;

import java.util.Random;

public class RandomMovement extends MovementManager
{



    public RandomMovement(BattleAI battle)
    {
        super(battle);
    }

    @Override
    public void generateMovement() {

        int var = 0;

        var = new Random().nextInt(4);

        switch(var){
            case 1:
                this.getBattle().getBattleship2().moveUp();
                break;
            case 2:
                this.getBattle().getBattleship2().moveDown();
                break;
            case 3:
                this.getBattle().getBattleship2().moveLeft();
                break;
            case 4:
                this.getBattle().getBattleship2().moveRight();
                break;
        }
    }
}

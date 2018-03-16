package engine_classes.ai.movement;

import engine_classes.battles.BattleAI;

import java.util.Random;

public class RandomMovement extends MovementManager
{
    private boolean continuousMovement;
    private int continuousMovementCountLeft;
    private int directionOfContinuousMovement;

    private final int CHANCE_TO_MOVE_CONTINUOUSLY = 10;
    private final int CONTINUOUS_MOVEMENT_COUNT = 5;


    public RandomMovement(BattleAI battle)
    {
        super(battle);

        this.continuousMovement = false;
        this.continuousMovementCountLeft = 0;
        this.directionOfContinuousMovement = 0;

    }

    @Override
    public void generateMovement() {

        // this if tries to spin for a continuous movement
        if(!this.continuousMovement) {
            // check continous movement
            int continuousChance = new Random().nextInt(100);

            if (continuousChance < this.CHANCE_TO_MOVE_CONTINUOUSLY) {
                this.continuousMovement = true;
                this.continuousMovementCountLeft = this.CONTINUOUS_MOVEMENT_COUNT;
                this.directionOfContinuousMovement = new Random().nextInt(4);
            }
        }


        // normal movement
        if(!this.continuousMovement) {
            int var = new Random().nextInt(4);

            switch (var) {
                case 0:
                    this.getBattle().getBattleship2().moveUp();
                    break;
                case 1:
                    this.getBattle().getBattleship2().moveDown();
                    break;
                case 2:
                    this.getBattle().getBattleship2().moveLeft();
                    break;
                case 3:
                    this.getBattle().getBattleship2().moveRight();
                    break;
            }
        }
        else // continuous movement
        {
            // move continuous
            switch (this.directionOfContinuousMovement) {
                case 0:
                    this.getBattle().getBattleship2().moveUp();
                    break;
                case 1:
                    this.getBattle().getBattleship2().moveDown();
                    break;
                case 2:
                    this.getBattle().getBattleship2().moveLeft();
                    break;
                case 3:
                    this.getBattle().getBattleship2().moveRight();
                    break;
            }

            this.continuousMovementCountLeft--;

            if(this.continuousMovementCountLeft == 0)
            {
                this.continuousMovement = false;
            }
        }


    }
}

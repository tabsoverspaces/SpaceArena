package engine_classes.battles;

import engine_classes.ai.attacking.RandomShootingManager;
import engine_classes.ai.attacking.ShootingManager;
import engine_classes.ai.movement.MovementManager;
import engine_classes.ai.movement.RandomMovement;
import engine_classes.player.Player;

public class BattleAI extends Battle {

    private MovementManager movementManager;
    private ShootingManager shootingManager;


    public BattleAI(Player player1, Player player2)
    {
        super(player1, player2);

        this.movementManager = new RandomMovement(this);
        this.shootingManager = new RandomShootingManager(this);
    }

    public void updateBattle() {
        if (this.battleOverCondition())
            this.setBattleRunning(false);

        this.getBattleship1().update();
        this.getBattleship2().update();

        this.updateBullets();

        this.updateAnimations();

        // move ship
        this.movementManager.move();
        this.movementManager.update();

        this.shootingManager.shoot();
        this.shootingManager.update();

    }

}

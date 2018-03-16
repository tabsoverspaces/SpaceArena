package engine_classes.ai.attacking;

import engine_classes.battles.BattleAI;
import gui.battle_gui.Animation;

import java.util.Random;

public class RandomShootingManager extends ShootingManager
{
    private final int  CHANCE_NEXT_WEAPON = 60;


    public RandomShootingManager(BattleAI battle) {
        super(battle);
    }

    public void update()
    {
        this.getShootCooldown().update();

        this.handleWeaponChange();

    }

    private void handleWeaponChange()
    {
        if(!this.getWeaponChangeCooldown().isChangeOnCooldown()) {
            // get chance to sswap
            Random r = new Random();
            int number = r.nextInt(100);
            // chance to go forward a weapon
            if (number <= CHANCE_NEXT_WEAPON) {
                // next weapon
                this.getBattleAI().getBattleship2().changeToNextWeapon();

                Animation a = new Animation(this.getBattleAI().getBattleship2().getX(),
                        this.getBattleAI().getBattleship2().getY(), "Changed weapon!");

                this.getBattleAI().addAnimation(a);

            } else {
                // previous weapon
                this.getBattleAI().getBattleship2().changeToPreviousWeapon();

                Animation a = new Animation(this.getBattleAI().getBattleship2().getX(),
                        this.getBattleAI().getBattleship2().getY(), "Changed weapon!");

                this.getBattleAI().addAnimation(a);
            }

            this.getWeaponChangeCooldown().change();
        }

        this.getWeaponChangeCooldown().update();
    }


}

package engine_classes.items.weapons;

import engine_classes.items.weapons.bullets.BasicBullet;
import engine_classes.items.weapons.bullets.Bullet;
import engine_classes.items.weapons.bullets.RocketMissile;
import gui.battle_gui.Battleship;

public class PopGun extends Weapon {

    private final double DAMAGE = 0;
    private final double FIRERATE = 20;


    public PopGun()
    {
        super();
    }

    @Override
    public double initiateDamage() {
        return DAMAGE;
    }

    @Override
    public double initiateFireRate() {
        return FIRERATE;
    }

    @Override
    public double initiateShootSpeed() {
        return 200;
    }


    @Override
    public Bullet getBulletModel() {
        return new RocketMissile(this);
    }

    @Override
    public double getItemValue() {
        return 0;
    }
}

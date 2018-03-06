package engine_classes.items.weapons;

import engine_classes.items.weapons.bullets.BasicBullet;
import engine_classes.items.weapons.bullets.Bullet;
import engine_classes.items.weapons.bullets.RocketMissile;
import gui.battle_gui.Battleship;

import java.awt.*;

public class PopGun extends Weapon {

    private final double DAMAGE = 0;
    private final double FIRERATE = 20;
    private final double BULLET_SPEED = 200;

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
        return BULLET_SPEED;
    }
    @Override
    public void drawWeapon(Graphics g){


    }
    @Override
    public Bullet[] shootBullets() {
        Bullet[] a = new Bullet[1];

        a[0] = new BasicBullet(this);

        return a;
    }


    @Override
    public Bullet getBulletModel() {

        return new BasicBullet(this);
    }

    @Override
    public double getItemValue() {
        return 0;
    }
}

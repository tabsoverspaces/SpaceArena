package engine_classes.items.weapons;

import engine_classes.items.weapons.bullets.Bullet;
import engine_classes.items.weapons.bullets.RocketMissile;

import java.awt.*;

public class RocketLauncher extends Weapon {

    private final double DAMAGE = 0;
    private final double FIRERATE = 1.5;
    private final double BULLET_SPEED = 550;

    public RocketLauncher()
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
    protected Bullet[] shootBullets() {
        Bullet[] b = new Bullet[1];

        b[0] = (new RocketMissile(this));

        return b;
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

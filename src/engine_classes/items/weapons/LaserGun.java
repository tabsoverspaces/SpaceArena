package engine_classes.items.weapons;

import engine_classes.items.weapons.bullets.Bullet;
import engine_classes.items.weapons.bullets.LaserRay;

public class LaserGun extends Weapon {

    private final double DAMAGE = 0;
    private final double FIRERATE = 100;
    private final double BULLET_SPEED = 2000;

    public LaserGun()
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
    protected Bullet[] shootBullets() {
        Bullet[] b = new Bullet[1];

        b[0] = new LaserRay(this);

        return b;
    }

    @Override
    public Bullet getBulletModel() {
        return new LaserRay(this);
    }

    @Override
    public double getItemValue() {
        return 0;
    }
}

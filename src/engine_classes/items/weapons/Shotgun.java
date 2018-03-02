package engine_classes.items.weapons;

import engine_classes.items.weapons.bullets.Bullet;



public class Shotgun extends Weapon {

    private final double DAMAGE = 100;
    private final double FIRERATE = 20;

    private long shotAt;
    private int distancePassed;

    public Shotgun()
    {}


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
    public Bullet[] getBulletModel() {



        return null;
    }

    @Override
    public double getItemValue() {
        return 0;
    }
}

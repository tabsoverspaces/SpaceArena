package engine_classes.items.weapons;

import engine_classes.items.weapons.bullets.Bullet;
import engine_classes.items.weapons.bullets.ShotgunGauge;


public class Shotgun extends Weapon {

    private final double DAMAGE = 0;
    private final double FIRERATE = 4;

    private int currentNumberOfShells;

    private long shotAt;
    private int distancePassed;

    public Shotgun()
    {
        this.currentNumberOfShells = 3;
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
        return 400;
    }

    @Override
    public Bullet[] shootBullets() {
        Bullet[] a = new Bullet[3];

        // left shell
        ShotgunGauge b1 = new ShotgunGauge(this, -50* (this.getSource().getPlayerNo()), (int)this.getWeaponShootSpeed());

        // center shell
        ShotgunGauge b2 = new ShotgunGauge(this, 0,(int)this.getWeaponShootSpeed());

        // right shell
        ShotgunGauge b3 = new ShotgunGauge(this, (50)*(this.getSource().getPlayerNo()), (int)this.getWeaponShootSpeed());

        a[0] = b1;
        a[1] = b2;
        a[2] = b3;

        return a;
    }

    @Override
    public Bullet getBulletModel() {

        return new ShotgunGauge(this);
    }

    @Override
    public double getItemValue() {
        return 0;
    }
}

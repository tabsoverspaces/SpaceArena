package engine_classes.items.weapons;

import engine_classes.items.weapons.bullets.Bullet;
import engine_classes.items.weapons.bullets.LaserRay;

import java.awt.*;

public class LaserGun extends Weapon {

    private final double DAMAGE = 0;
    private final double FIRERATE = 100;
    private final double BULLET_SPEED = 2000;

    // graphics items
    private final int gunWidth = 15;
    private final int gunHeight = 40;

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
    public void drawWeapon(Graphics g){

        this.drawLaserGun(g, this.getSource().getShipCenterX()-this.gunWidth/2, this.getSource().getY());
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

    private void drawLaserGun(Graphics g, int startingX, int startingY)
    {
        g.setColor(Color.black);
        g.drawRect(startingX, startingY, this.gunWidth, this.gunHeight);


    }
}

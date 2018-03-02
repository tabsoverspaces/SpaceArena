package engine_classes.items.weapons;

import engine_classes.items.Item;
import engine_classes.items.weapons.bullets.Bullet;
import gui.battle_gui.Battleship;

public abstract class Weapon extends Item {

    private double damage;
    private double fireRate;

    private double weaponShootSpeed;

    private Battleship source;

    // cooldown variables
    private long lastShotAt;
    private boolean shootingCooldown;

    public Weapon()
    {
        this.damage = this.initiateDamage();
        this.fireRate = this.initiateFireRate();
        this.weaponShootSpeed = this.initiateShootSpeed();

        // cooldown variables
        this.lastShotAt = System.nanoTime();
        this.shootingCooldown = false;

    }
    public void setSource(Battleship s)
    {
        this.source = s;
    }


    public abstract double initiateDamage();
    public abstract double initiateFireRate();
    public abstract double initiateShootSpeed();

    public Bullet[] shoot()
    {
        this.activateShootingCooldown();

        return this.getBulletModel();
    }

    public abstract Bullet[] getBulletModel();

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public Battleship getSource()
    {return this.source;}


    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double firerate) {
        this.fireRate = firerate;
    }

    public double getWeaponShootSpeed() {
        return weaponShootSpeed;
    }

    public void setWeaponShootSpeed(double weaponShootSpeed) {
        this.weaponShootSpeed = weaponShootSpeed;
    }

    public void activateShootingCooldown()
    {
        this.lastShotAt = System.nanoTime();
        this.shootingCooldown = true;
    }
    public void deactivateShootingCooldown()
    {
        this.shootingCooldown = false;
    }

}

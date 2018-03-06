package engine_classes.items.weapons;

import engine_classes.items.Item;
import engine_classes.items.weapons.bullets.Bullet;
import gui.battle_gui.Battleship;

import java.awt.*;

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

    public void update()
    {
        this.updateShootingCooldown();
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

        return this.shootBullets();
    }

    public abstract void drawWeapon(Graphics g);

    protected abstract Bullet[] shootBullets();

    public abstract Bullet getBulletModel();

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

    public void updateShootingCooldown()
    {
        long timeNow = System.nanoTime();

        if (timeNow - this.lastShotAt >= ((1 / this.getWeaponAndBulletFireRate() * 1_000_000_000))) {
            this.deactivateShootingCooldown();
        }
    }

    private double getWeaponAndBulletFireRate()
    {
        return this.fireRate + this.getBulletModel().getBonusFireRate();
    }

    public long getLastShotAt() {
        return lastShotAt;
    }

    public void setLastShotAt(long lastShotAt) {
        this.lastShotAt = lastShotAt;
    }

    public boolean isShootingCooldown() {
        return shootingCooldown;
    }

    public void setShootingCooldown(boolean shootingCooldown) {
        this.shootingCooldown = shootingCooldown;
    }
}

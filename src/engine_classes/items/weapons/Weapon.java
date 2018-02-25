package engine_classes.items.weapons;

import engine_classes.items.Item;
import engine_classes.items.weapons.bullets.Bullet;
import gui.battle_gui.Battleship;

public abstract class Weapon extends Item {

    private double damage;
    private double fireRate;

    private Battleship source;

    public Weapon()
    {

    }

    public Weapon(double damage, double firerate)
    {
        this();

        this.damage = damage;
        this.fireRate = firerate;
    }

    public Weapon(Battleship ship , double damage, double fireRate)
    {
        this(damage, fireRate);

        this.source = ship;
    }

    public Bullet shoot()
    {
        return this.getBulletModel();
    }

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

    public void setFirerRate(double firerate) {
        this.fireRate = firerate;
    }
}

package engine_classes.items.weapons;

import engine_classes.items.Item;
import engine_classes.items.weapons.bullets.Bullet;
import gui.battle_gui.Battleship;

public abstract class Weapon extends Item {

    private double damage;
    private double firerate;

    private Battleship source;

    public Weapon()
    {

    }

    public Weapon(double damage, double firerate)
    {
        this();

        this.damage = damage;
        this.firerate = firerate;
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


    public double getFirerate() {
        return firerate;
    }

    public void setFirerate(double firerate) {
        this.firerate = firerate;
    }
}

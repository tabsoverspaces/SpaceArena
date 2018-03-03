package engine_classes.items.weapons.bullets;

import utility.Direction;
import engine_classes.items.weapons.Weapon;
import gui.battle_gui.Battleship;
import interfaces.ApplyDamageInterface;
import interfaces.TakeDamageInterface;

import java.awt.*;

public abstract class Bullet implements TakeDamageInterface, ApplyDamageInterface{


    private int x,y,z;
    private int width,height;

    private Direction direction;

    private boolean isActive;

    private Weapon weaponSource;


    private double bonusDamage;
    private double bonusFirerate;
    private double bonusShootSpeed;

    protected long lastUpdate;
    protected long initialShotAt;

    private Bullet()
    {
        this.isActive = true;

        this.initialShotAt = System.nanoTime();
        this.lastUpdate = this.initialShotAt;

        this.width = this.initiateWidth();
        this.height = this.initiateHeight();

        this.bonusDamage = this.initiateBonusDamage();
        this.bonusFirerate = this.initiateBonusFirerate();
        this.bonusShootSpeed = this.initiateBonusShootSpeed();


    }

    public abstract int initiateWidth();
    public abstract int initiateHeight();



    public abstract double initiateBonusDamage();
    public abstract double initiateBonusFirerate();
    public abstract double initiateBonusShootSpeed();

    public Bullet(Weapon source)
    {
        this();

        this.weaponSource = source;

        if(this.weaponSource.getSource().getPlayerNo() == 1)
        {
            this.direction = Direction.UP;
        }
        else
        {
            this.direction = Direction.DOWN;
        }

        // initiate location
        this.x = this.weaponSource.getSource().getShipCenterX();
        this.y = this.weaponSource.getSource().getShipFrontY();
        this.z = 0;


    }

    protected void updateTimeVariables()
    {
        this.lastUpdate = System.nanoTime();
    }

    public abstract boolean checkCollision(Battleship target);
    public abstract boolean checkOutOfBounds(Rectangle bounds);


    public abstract double getBulletDamage();

    public abstract void drawBullet(Graphics g);

    public abstract void updateBullet();

    public double getTotalDamage(){
        return this.weaponSource.getDamage() + this.bonusDamage + this.getBulletDamage();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getBonusDamage() {
        return bonusDamage;
    }

    public void setBonusDamage(double bonusDamage) {
        this.bonusDamage = bonusDamage;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Weapon getSource() {
        return this.weaponSource;
    }

    public void setSource(Weapon source) {
        this.weaponSource = source;
    }

    public double getInitialShootSpeed()
    {
        return this.weaponSource.getWeaponShootSpeed()
                + (this.weaponSource.getWeaponShootSpeed() * this.bonusShootSpeed / 100);
    }

    public double getBonusFireRate()
    {
        return this.bonusFirerate;
    }


    public double getAppliableDamage()
    {
        return this.getTotalDamage();
    }



}

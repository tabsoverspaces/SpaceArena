package engine_classes.items.weapons.bullets;

import engine_classes.Direction;
import engine_classes.items.weapons.Weapon;
import gui.battle_gui.Battleship;

import java.awt.*;

public abstract class Bullet {


    private int x,y,z;
    private int width,height;

    private double baseMovementSpeed;
    private double baseDamage;

    private double bonusMovementSpeed;
    private double bonusDamage;

    private Direction direction;

    private boolean isActive;

    private Battleship source;

    private Bullet()
    {
        this.isActive = true;

        this.bonusDamage = 0;
        this.bonusMovementSpeed = 0;
    }

    private Bullet(double damage)
    {
        this();
        this.baseDamage = damage;
    }

    public Bullet(Weapon source)
    {
        this(source.getDamage());

        this.source = source.getSource();

        if(this.source.getPlayerNo() == 1)
        {
            this.direction = Direction.UP;
        }
        else
        {
            this.direction = Direction.DOWN;
        }
    }

    public abstract void drawBullet(Graphics g);

    public abstract void updateBullet();

    public abstract double getBaseMovementSpeed();

    public double getTotalDamage(){
        return this.baseDamage + this.bonusDamage;
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

    public void setBaseMovementSpeed(double baseMovementSpeed) {
        this.baseMovementSpeed = baseMovementSpeed;
    }

    public void setBaseDamage(double baseDamage) {
        this.baseDamage = baseDamage;
    }

    public double getBonusMovementSpeed() {
        return bonusMovementSpeed;
    }

    public void setBonusMovementSpeed(double bonusMovementSpeed) {
        this.bonusMovementSpeed = bonusMovementSpeed;
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

    public Battleship getSource() {
        return source;
    }

    public void setSource(Battleship source) {
        this.source = source;
    }

    public double getBaseDamage()
    {
        return this.baseDamage;
    }
}

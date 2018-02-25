package engine_classes.items.weapons.bullets;

import engine_classes.Direction;
import engine_classes.items.weapons.Weapon;
import gui.battle_gui.Battleship;
import interfaces.Targetable;

import java.awt.*;

public abstract class Bullet {


    private int x,y,z;
    private int width,height;

    private double baseMovementSpeed;
    private double weaponDamage;

    private double bonusMovementSpeed;
    private double bonusDamage;

    private Direction direction;

    private boolean isActive;

    private Weapon weaponSource;

    protected long lastUpdate;
    protected long initialShotAt;

    private Bullet()
    {
        this.isActive = true;

        this.bonusDamage = 0;
        this.bonusMovementSpeed = 0;

        this.initialShotAt = System.nanoTime();
        this.lastUpdate = this.initialShotAt;
    }

    private Bullet(double damage)
    {
        this();
        this.weaponDamage = damage;

        this.initiateBaseMovementSpeed();

    }

    public Bullet(Weapon source)
    {
        this(source.getDamage());

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
    }

    protected void updateTimeVariables()
    {
        this.lastUpdate = System.nanoTime();
    }

    public abstract boolean checkCollision(Battleship target);
    public abstract boolean checkOutOfBounds(Rectangle bounds);






    public abstract void initiateBaseMovementSpeed();

    public abstract void drawBullet(Graphics g);

    public abstract void updateBullet();

    public double getTotalDamage(){
        return this.weaponDamage + this.bonusDamage;
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
        this.weaponDamage = baseDamage;
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

    public Weapon getSource() {
        return this.weaponSource;
    }

    public void setSource(Weapon source) {
        this.weaponSource = source;
    }

    public double getBaseDamage()
    {
        return this.weaponDamage;
    }
}

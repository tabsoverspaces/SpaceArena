package engine_classes.items.weapons.bullets;

import engine_classes.items.weapons.Weapon;
import gui.battle_gui.Battleship;
import interfaces.ApplyDamageInterface;
import interfaces.TakeDamageInterface;

import java.awt.*;

public class ShotgunGauge extends Bullet {

    private int xMoveSpeed;
    private int yMoveSpeed;

    private int startingX;
    private int startingY;

    private final int MAXIMUM_DISTANCE = 200; // hipotenouse between x and y

    private double damagePenalty;
    private final int MINIMUM_DAMAGE_PENALTY = 0;
    private final int MAXIMUM_DAMAGE_PENALTY = 80;

    public ShotgunGauge(Weapon source)
    {
        super(source);

        this.startingX = this.getX();
        this.startingY = this.getY();

        this.damagePenalty = 0;
    }

    public ShotgunGauge(Weapon source, int xMoveSpeed, int yMoveSpeed)
    {
        this(source);

        this.xMoveSpeed = xMoveSpeed;
        this.yMoveSpeed = yMoveSpeed;


    }

    @Override
    public int initiateWidth() {
        return 10;
    }

    @Override
    public int initiateHeight() {
        return 10;
    }

    @Override
    public double initiateBonusDamage() {
        return 0;
    }

    @Override
    public double initiateBonusFirerate() {
        return 0;
    }

    @Override
    public double initiateBonusShootSpeed() {
        return 0;
    }

    @Override
    public boolean checkCollision(Battleship ship) {

        if(ship.getX() < this.getX() + this.getWidth() && ship.getX() + ship.getWidth() > this.getX() &&
                ship.getY() < this.getY() + this.getHeight() && ship.getY() + ship.getHeight() > this.getY() &&
                ship.getZ() == this.getZ())
        {
            return true;
        }

        return false;
    }

    @Override
    public boolean checkOutOfBounds(Rectangle bounds) {

        if(this.getX() > bounds.width || this.getX() < 0 || this.getY() > bounds.height || this.getY() < 0)
        {
            return true;
        }

        return false;
    }

    @Override
    public double getBulletDamage() {
        return 60 - (80*this.damagePenalty / 100);
    }

    @Override
    public void drawBullet(Graphics g) {
        g.setColor(Color.black);
        g.drawOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public void updateBullet() {


        // get fraction of time
        long timeSinceLastUpdate = System.nanoTime() - this.lastUpdate; // time in nano seconds

        // totalMovementSpeed * percentage of the time that's passed since the last update time / 1
        double speedIncrementX = this.xMoveSpeed  * (timeSinceLastUpdate / 1_000_000_000.0); // DOUBLE CHECK THIS
        double speedIncrementY = this.yMoveSpeed * (timeSinceLastUpdate / 1_000_000_000.0);

        this.setX((int) (this.getX() + speedIncrementX));
        this.setY(this.getY() + (int)((-1)*(this.getSource().getSource().getPlayerNo())*speedIncrementY));

        // check distance and update damage
        double distancePassed = this.distancePassed();

        this.updateDamage(distancePassed);

        if(distancePassed > this.MAXIMUM_DISTANCE)
        {
            this.setActive(false);
        }

        this.updateTimeVariables();
    }

    private void updateDamage(double distance)
    {
        double damagePenaltyDiff = this.MAXIMUM_DAMAGE_PENALTY - this.MINIMUM_DAMAGE_PENALTY;

        double pathPercentage = (distance/this.MAXIMUM_DISTANCE)*100;

        double penalty = this.MINIMUM_DAMAGE_PENALTY + (damagePenaltyDiff * pathPercentage / 100);

        this.damagePenalty = penalty;
    }

    private double distancePassed()
    {
        // more complex formula, using Pitagora
//        double deltaX = Math.abs(this.getX() - this.startingX);
//        double deltaY = Math.abs(this.getY() - this.startingY);
//
//        double distance = Math.sqrt((deltaX*deltaX) + (deltaY * deltaY));

        double distance = Math.abs(this.getY() - this.startingY);

        return distance;
    }

    @Override
    public void applyDamage(TakeDamageInterface target) {

    }

    @Override
    public void takeDamage(ApplyDamageInterface source) {
        this.setActive(false);
    }
}
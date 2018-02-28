package engine_classes.items.weapons.bullets;

import engine_classes.items.weapons.Weapon;
import gui.battle_gui.Battleship;
import interfaces.ApplyDamageInterface;
import interfaces.TakeDamageInterface;
import interfaces.Targetable;

import java.awt.*;

public class BasicBullet extends Bullet {


    @Override
    public int initiateWidth() {
        return 5;
    }

    @Override
    public int initiateHeight() {
        return 5;
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

    public BasicBullet(Weapon source)
    {
        super(source);

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
        return 10;
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
        double speedIncrement = this.getInitialShootSpeed() * (timeSinceLastUpdate / 1_000_000_000.0); // DOUBLE CHECK THIS

        this.setY(this.getY() + (int)((-1)*(this.getSource().getSource().getPlayerNo())*speedIncrement));

        this.updateTimeVariables();
    }


    @Override
    public void applyDamage(TakeDamageInterface target) {
    }

    @Override
    public double getAppliableDamage() {
        return this.getTotalDamage();
    }

    @Override
    public void takeDamage(ApplyDamageInterface source) {
        this.setActive(false);
    }
}

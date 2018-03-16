package engine_classes.items.weapons.bullets;

import engine_classes.items.weapons.Weapon;
import gui.battle_gui.Battleship;
import interfaces.ApplyDamageInterface;
import interfaces.TakeDamageInterface;

import java.awt.*;

public class LaserRay extends Bullet {


    public LaserRay(Weapon source) {
        super(source);
    }

    @Override
    public int initiateWidth() {
        return 3;
    }

    @Override
    public int initiateHeight() {
        return 0;
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
        if(this.getY() < 0 || this.getY() + this.getHeight() > bounds.height)
        {
            return true;
        }

        return false;
    }

    @Override
    public double getBulletDamage() {
        return 2.5;
    }

    @Override
    public void drawBullet(Graphics g) {

        int x1,x2;
        int y1,y2;

        x1 = this.getX();
        y1 = this.getY();

        x2 = this.getX();
        y2 = this.getY() + this.getHeight();

        g.setColor(Color.black);
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void updateBullet() {

        // get fraction of time
        long timeSinceLastUpdate = System.nanoTime() - this.lastUpdate; // time in nano seconds

        // totalMovementSpeed * percentage of the time that's passed since the last update time / 1
        double speedIncrement = this.getInitialShootSpeed() * (timeSinceLastUpdate / 1_000_000_000.0); // DOUBLE CHECK THIS

        // update dimensions
        int playerNo = this.getSource().getSource().getPlayerNo();

        if(playerNo == 1)
        {
            this.setY((int) (this.getY() - speedIncrement));
            this.setHeight((int) (this.getHeight() + speedIncrement));
        }
        else{
            this.setHeight((int) (this.getHeight() + speedIncrement));
        }

        this.updateTimeVariables();


    }

    @Override
    public void applyDamage(TakeDamageInterface target) {

    }

    @Override
    public void takeDamage(ApplyDamageInterface source) {

        this.setActive(false);

    }
}

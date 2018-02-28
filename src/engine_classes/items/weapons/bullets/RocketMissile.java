package engine_classes.items.weapons.bullets;

import engine_classes.items.weapons.Weapon;
import gui.battle_gui.Battleship;
import interfaces.ApplyDamageInterface;
import interfaces.TakeDamageInterface;

import java.awt.*;

public class RocketMissile extends Bullet{

    @Override
    public int initiateWidth() {
        return 25;
    }

    @Override
    public int initiateHeight() {
        return 25;
    }

    @Override
    public double initiateBonusDamage() {
        return 0;
    }

    @Override
    public double initiateBonusFirerate() {
        return -50;
    }

    @Override
    public double initiateBonusShootSpeed() {
        return 35;
    }

    public RocketMissile(Weapon source)
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
        return false;
    }

    @Override
    public double getBulletDamage() {
        return 50;
    }

    @Override
    public void drawBullet(Graphics g) {

        int topx, leftx, rightx;
        int topy, lefty, righty;

        topx = this.getX();
        topy = this.getY() + ((this.getSource().getSource().getPlayerNo())*(-1)) + this.getHeight();

        rightx = topx - this.getWidth()/2;
        righty = this.getY();

        leftx = topx + this.getWidth()/2;
        lefty = this.getY();

        int[] xarray = {topx,leftx, rightx};
        int[] yarray = {topy, lefty, righty};
        int number = 3;

        if(this.getSource().getSource().getPlayerNo()==1)
        {
            Polygon p = new Polygon(xarray, yarray, number);

            Graphics2D g2 = (Graphics2D)g;
            g2.rotate(Math.toRadians(180));
            g2.setColor(Color.black);

            g2.draw(p);

            g2.rotate(Math.toRadians(180));
        }
        else
        {
            g.drawPolygon(xarray, yarray, number);
        }


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

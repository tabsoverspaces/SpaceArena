package engine_classes.items.weapons.bullets;

import engine_classes.items.weapons.Weapon;
import gui.battle_gui.Battleship;
import interfaces.ApplyDamageInterface;
import interfaces.TakeDamageInterface;

import java.awt.*;

public class RocketMissile extends Bullet{

    public RocketMissile(Weapon source)
    {
        super(source);
    }

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

        this.drawRocketMissile(g, this.getX(), this.getY());

    }

    private void drawRocketMissile(Graphics g, int x, int y)
    {
        int leftX, centerX, rightX;
        int leftY, centerY, rightY;

        if(this.getSource().getSource().getPlayerNo() == 1)
        {
            leftX = x - this.getWidth()/2;
            leftY = y;

            centerX = x;
            centerY = y - this.getHeight();

            rightX = x+this.getWidth()/2;
            rightY = y;


        }
        else
        {
            leftX = x - this.getWidth()/2;
            leftY = y;

            centerX = x;
            centerY = y + this.getHeight();

            rightX = x+this.getWidth()/2;
            rightY = y;
        }

        // draw 3 lines
        g.setColor(Color.black);
        g.drawLine(leftX,leftY,rightX,rightY);
        g.drawLine(rightX, rightY, centerX, centerY);
        g.drawLine(centerX, centerY, leftX, leftY);

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

package gui.battle_gui;

import engine_classes.Bullet;
import engine_classes.Direction;
import engine_classes.Ship;
import gui.MainFrame;
import gui.interfaces.ApplyDamageInterface;
import gui.interfaces.Drawable;
import gui.interfaces.Movable;
import gui.interfaces.TakeDamageInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

import static engine_classes.Direction.DOWN;
import static engine_classes.Direction.UP;
import static javax.print.attribute.standard.Chromaticity.COLOR;

public class Battleship implements Drawable, Movable, TakeDamageInterface  {

    private boolean isAlive;

    private Ship ship;
    private int playerNo;

    private final int width = 50;
    private final int height = 50;

    private int x;
    private int y;

    private int lowLimit;
    private int topLimit;

    private JPanel parentPanel;

    private boolean shootingCooldown;
    private long shootingCooldownLeft;
    private long lastShotAt;

    private double maxHealth;
    private double maxShield;

    private double currentHealth;
    private double currentShield;

    public Battleship()
    {
        this.shootingCooldown = false;
        this.isAlive = true;

    }


    public Battleship(Ship ship, int playerNo)
    {
        this();

        this.ship = ship;
        this.playerNo = playerNo;

        this.maxHealth = this.ship.getTotalHealth();
        this.maxShield = this.ship.getTotalShield();

        this.currentHealth = this.maxHealth;
        this.currentShield = this.maxShield;
    }

    public void init()
    {
        Rectangle rect = this.getCenterOfScreen(this.playerNo);

        this.x = (int)rect.getX();
        this.y = (int)rect.getY();

        if(this.playerNo == 1)
        {
            this.lowLimit = this.parentPanel.getHeight();
            this.topLimit = this.parentPanel.getHeight()/2;
        }

        else
        {
            this.lowLimit = this.parentPanel.getHeight()/2;
            this.topLimit = 0;
        }
    }

    public Bullet shoot()
    {
        if(this.shootingCooldown)
        {
            System.out.println("cooldown!");

        }
        else {

            this.activateShootingCooldown();
            return this.createBullet(this.x + this.width / 2, this.y);
        }

        return null;
    }
    public void update()
    {
        long time = System.nanoTime();

        if(time - this.lastShotAt >= ((1/this.ship.getTotalFireRate()*1_000_000_000)))
        {
            this.shootingCooldown = false;
        }
    }

    private void activateShootingCooldown()
    {
        this.shootingCooldown = true;
        this.lastShotAt = System.nanoTime();
    }

    public boolean isOnShootingCooldown()
    {
        return this.shootingCooldown;
    }

    public Bullet createBullet(int x, int y)
    {
        Direction dir;

        if(this.playerNo == 1)
            dir = UP;
        else {
            dir = DOWN;
        }

        Bullet b = new Bullet(dir);
        b.setSource(this);

        b.setX(x-b.getWidth()/2);
        b.setY(y-b.getHeight());

        return b;
    }

    private void drawBars(Graphics g)
    {
        int shipSpacing = 10;
        int barSpacing = 5;

        int barLength = Math.min(this.width*2 , 200);
        int barHeight = 15;

        int x;
        int healthBarY;
        int shieldBarY;

        int healthPercentage = (int)((this.currentHealth / this.maxHealth)*100);
        int shieldPercentage = (int)((this.currentShield / this.maxShield)*100);

        // shading variables
        int shadeX;

        int shadeLengthPercentage = 80;
        int shadeHeightPercentage = 20;

        int shadeXOffset = 10; // percentage
        int shadeYOffset = 60; // percentage calculated from top of bar

        int healthShadeY, shieldShadeY;

        int shadeWidth, shadeHeight;
        shadeWidth = barLength * shadeLengthPercentage / 100;
        shadeHeight = barHeight * shadeHeightPercentage / 100;

        if(this.playerNo == 1)
        {
            healthBarY = this.y + this.height + shipSpacing;
            shieldBarY = healthBarY + barHeight + barSpacing;

            x = (int)(this.getShipCenterX() - (barLength/2));

            // shading variables
            shadeX = x + (barLength * shadeXOffset / 100);

            healthShadeY = healthBarY + (barHeight * shadeYOffset / 100);
            shieldShadeY = shieldBarY + (barHeight * shadeYOffset / 100);


            // draw borders
            g.drawRect(x, healthBarY, barLength, barHeight);
            g.drawRect(x, shieldBarY, barLength, barHeight);

            // represent health and shield values
            g.setColor(Color.green);
            g.fillRect(x+1, healthBarY+1, (barLength*healthPercentage/100)-2, barHeight-2);
            g.setColor(Color.orange);
            g.fillRect(x+1, shieldBarY+1 , (barLength*shieldPercentage/100)-2, barHeight-2);

            // draw shades
            g.setColor(new Color(0,255,127));
            g.fillRect(shadeX , healthShadeY, shadeWidth , shadeHeight);
            g.setColor(Color.yellow);
            g.fillRect(shadeX , shieldShadeY, shadeWidth , shadeHeight);

        }
        else
        {

        }
    }

    private double getShipCenterX()
    {
        return this.x + this.width/2;
    }


    @Override
    public void draw(Graphics g) {

        Graphics2D g2 = (Graphics2D)g;

        if(this.playerNo==1)
            g2.setColor(Color.blue);
        else
            g2.setColor(Color.red);

        // draw initial box and get its stats
        g2.fillRect(this.x, this.y, this.width, this.height);

        System.out.println(this.y);

        this.drawBars(g);


        // then draw all weapons respectively
    }

    private Rectangle getCenterOfScreen(int playerNo)
    {
        //1 for 1, 2 for 2nd player
        // 1st player always on bottom

        Rectangle rect = new Rectangle();

        rect.setSize(width, height);

        // find x
        // find y

        int x,y;
        int shipOffset = 10;

        x = (this.parentPanel.getWidth()/2) - (this.width/2);

        if(playerNo == 2) // fist player
        {
            y = 0;
            y += shipOffset;
        }
        else
        {
            y = this.parentPanel.getHeight();
            y -= (shipOffset + this.height);
        }

        rect.setLocation(x,y);

        return rect;

    }

    private static Shape mirrorAlongX(double x, Shape shape)
    {
        AffineTransform at = new AffineTransform();
        at.translate(x, 0);
        at.scale(-1, 1);
        at.translate(-x, 0);
        return at.createTransformedShape(shape);
    }

    public void setParentPanel(JPanel panel)
    {
        this.parentPanel = panel;
    }

    @Override
    public void moveUp() {

        if((this.y - this.ship.getTotalMovementSpeed()) < this.topLimit) {
            this.y = this.topLimit;
        }else {
            this.y -= this.ship.getTotalMovementSpeed();
        }
    }

    @Override
    public void moveDown() {

            if ((this.y + this.ship.getTotalMovementSpeed()) > (this.lowLimit - this.height)) {
                this.y = this.lowLimit - this.height;
            } else {
                this.y += this.ship.getTotalMovementSpeed();
            }

    }

    @Override
    public void moveRight() {
        if((this.x + this.ship.getTotalMovementSpeed()) > (this.parentPanel.getWidth() - this.width))
        {
            this.x = this.parentPanel.getWidth() - (this.width);
        }
        else {
            this.x += this.ship.getTotalMovementSpeed();
        }
    }

    @Override
    public void moveLeft() {
        if((this.x - this.ship.getTotalMovementSpeed()) < (0))
        {
            this.x = 0 ;
        }
        else {
            this.x -= this.ship.getTotalMovementSpeed();
        }
    }

    @Override
    public void move() {

    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public int getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(int lowLimit) {
        this.lowLimit = lowLimit;
    }

    public int getTopLimit() {
        return topLimit;
    }

    public void setTopLimit(int topLimit) {
        this.topLimit = topLimit;
    }

    public JPanel getParentPanel() {
        return parentPanel;
    }

    @Override
    public void takeDamage(ApplyDamageInterface source) {
        double damageToTake = source.getAppliableDamage();

        this.applyDamageToShip( damageToTake);
    }

    private void applyDamageToShip(double damage)
    {
        double initialDamage = damage;
        double stageTwoDamage = initialDamage - this.currentShield;

        if(initialDamage >= this.currentShield)
        {
            this.currentShield = 0;
        }
        else
        {
            this.currentShield -= initialDamage;
            return;
        }

        this.currentHealth -= stageTwoDamage;

        if(this.currentHealth <=0)
        {
            this.isAlive = false;
        }

    }
}

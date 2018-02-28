package gui.battle_gui;

import engine_classes.Direction;
import engine_classes.Ship;
import engine_classes.items.weapons.Weapon;
import engine_classes.items.weapons.bullets.Bullet;
import interfaces.ApplyDamageInterface;
import interfaces.Drawable;
import interfaces.Movable;
import interfaces.TakeDamageInterface;

import javax.swing.*;
import java.awt.*;

import static engine_classes.Direction.DOWN;
import static engine_classes.Direction.UP;

public class Battleship implements Drawable, Movable, TakeDamageInterface  {

    private boolean isAlive;

    private Ship ship;
    private int playerNo;

    private final int width = 50;
    private final int height = 50;

    private int x;
    private int y;
    private int z;

    private int lowLimit;
    private int topLimit;

    private JPanel parentPanel;

    private boolean shootingCooldown;
    private long shootingCooldownLeft;
    private long lastShotAt;

    private boolean dodgeCooldown;
    private long dodgeCooldownLeft;
    private long lastDodgeAt;

    private double maxHealth;
    private double maxShield;

    private double currentHealth;
    private double currentShield;

    private Weapon activeWeapon;



    private Battleship()
    {
        this.shootingCooldown = false;
        this.isAlive = true;

        this.dodgeCooldown = false;

        this.z = 0;
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

        this.setSourceToWeapons();
        this.setDefaultActiveWeapon();
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

    public void dodge()
    {
        if(this.dodgeCooldown)
        {
            System.out.println("dodge!");
        }
        else
        {
            // apply dodge
            this.setZ(10);

            // set up timers
            this.dodgeCooldown = true;
            this.lastDodgeAt = System.nanoTime();


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
    public void update() {
        long time = System.nanoTime();

        if (time - this.lastShotAt >= ((1 / this.activeWeapon.getFireRate() * 1_000_000_000))) {
            this.shootingCooldown = false;
        }

        // this checks to see if cooldown is done
        if (time - this.lastDodgeAt >= this.getDodgeCooldown() * 1_000_000_000) {
            this.dodgeCooldown = false;
        }

        // update dodge value(z)
        if (time-this.lastDodgeAt >= this.getDodgeDuration()*1_000_000_000)
        {
            this.z = 0;
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

        Bullet b = this.activeWeapon.getBulletModel();

        if(this.playerNo == 1)
        {  dir = UP;

            b.setX(x-b.getWidth()/2);
            b.setY(y-b.getHeight());
        }
        else {
            dir = DOWN;

            b.setX(x-b.getWidth()/2);
            b.setY(y + this.height);

        }

        b.setDirection(dir);



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

        int textX;
        int healthTextY;
        int shieldTextY;
        int textXOffsetPercentage = 25;
        int textYOffsetPercentage = 10;
        int textLengthPercentage = 50;
        int textHeightPercentage = 80;
        int textLength, textHeight;

        if(this.playerNo == 1)
        {
            healthBarY = this.y + this.height + shipSpacing;
            shieldBarY = healthBarY + barHeight + barSpacing;

            x = (int)(this.getShipCenterX() - (barLength/2));

            // shading variables
            shadeX = x + (barLength * shadeXOffset / 100);

            healthShadeY = healthBarY + (barHeight * shadeYOffset / 100);
            shieldShadeY = shieldBarY + (barHeight * shadeYOffset / 100);

            textX = x + (barLength* textXOffsetPercentage / 100);
//            healthTextY = healthBarY + (barHeight * textYOffsetPercentage / 100);
//            shieldTextY = shieldBarY + (barHeight * textYOffsetPercentage/100);

            healthTextY = healthBarY + (barHeight * textYOffsetPercentage/100) + 10 ;
            shieldTextY = shieldBarY + 10 ;

            textLength = barLength * textLengthPercentage;
            textHeight =  barHeight * textHeightPercentage;

            // draw borders
            g.drawRect(x, healthBarY, barLength, barHeight);
            g.drawRect(x, shieldBarY, barLength, barHeight);

            // represent health and shield values
            g.setColor(Color.green);
            g.fillRect(x+1, healthBarY+1, (barLength*healthPercentage/100)-2, barHeight-2);
            g.setColor(Color.orange);
            g.fillRect(x+1, shieldBarY+1 , (barLength*shieldPercentage/100)-2, barHeight-2);

            // draw shades
//            g.setColor(new Color(0,255,127));
//            g.fillRect(shadeX , healthShadeY, shadeWidth , shadeHeight);
//            g.setColor(Color.yellow);
//            g.fillRect(shadeX , shieldShadeY, shadeWidth , shadeHeight);

            // draw text on bars
            g.setColor(Color.black);
            g.drawString((this.currentHealth + "/"+this.maxHealth) , textX, healthTextY);
            g.drawString((this.currentShield + "/" + this.maxShield), textX, shieldTextY);


        }
        else
        {
            shieldBarY = this.y - shipSpacing;
            healthBarY = shieldBarY - barSpacing - barHeight;

            x = (int)this.getShipCenterX() - barLength/2;

            g.drawRect(x, healthBarY, barLength, barHeight);
            g.drawRect(x, shieldBarY , barLength, barHeight);

            textX = x + (barLength* textXOffsetPercentage / 100);
//            healthTextY = healthBarY + (barHeight * textYOffsetPercentage / 100);
//            shieldTextY = shieldBarY + (barHeight * textYOffsetPercentage/100);
            healthTextY = healthBarY + 10;
            shieldTextY = shieldBarY + 10;

            g.setColor(Color.green);
            g.fillRect(x+1, healthBarY + 1 , (barLength * healthPercentage/100)-2, barHeight-2);
            g.setColor(Color.orange);
            g.fillRect(x + 1 , shieldBarY + 1, (barLength*shieldPercentage/100)-2, barHeight-2);

            // draw text on bars
            g.setColor(Color.black);
            g.drawString((this.currentHealth + "/"+this.maxHealth) , textX, healthTextY);
            g.drawString((this.currentShield + "/" + this.maxShield), textX, shieldTextY);

        }
    }


    public int getShipCenterX(){
        return this.x + (this.width/2);
    }
    public int getShipFrontY()
    {
        if(this.playerNo==1)
        {
            return this.y;
        }
        else
        {
            return this.y-this.height;
        }
    }


    @Override
    public void draw(Graphics g) {

        Graphics2D g2 = (Graphics2D)g;

        if(this.playerNo==1)
            if(this.z!=0)
                g2.setColor(Color.gray);
        else
            g2.setColor(Color.blue);
        else
            if(this.z!=0)
                g2.setColor(Color.gray);
        else
            g2.setColor(Color.red);

        // draw initial box and get its stats
        g2.fillRect(this.x, this.y, this.width, this.height);

        this.drawBars(g);

        // draw cooldowns
        this.drawCooldowns(g);


        // then draw all weapons respectively
    }

    private void drawCooldowns(Graphics g)
    {
        int startingX = 10;
        int spacing = 10;

        int  startingY = 0;

        if(this.playerNo==1)
        {
            startingY = this.parentPanel.getHeight()-100;
        }
        else
        {
            startingY = 10;
        }

        g.drawString("Dodge CD left :  " + this.getDodgeCooldownLeft() + " secs.", startingX, startingY);
    }

    private double getDodgeCooldownLeft()
    {
        if(this.dodgeCooldown)
        {
            return this.getDodgeCooldown() - ((System.nanoTime() - this.lastDodgeAt)/1_000_000_000.0);
        }
        else
            return 0;
    }

    private double getDodgeCooldown()
    {
        return (((1 / this.ship.getTotalMovementSpeed()) * 100));
    }

    private double getDodgeDuration()
    {
        return this.ship.getTotalMovementSpeed() / 10;
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

        if(playerNo == -1) // fist player
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

    public boolean isOnDodgeCooldown()
    {
        return this.dodgeCooldown;
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

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(!(obj instanceof Battleship))
        {
            return false;
        }

        if(this.playerNo == ((Battleship) obj).playerNo)
            return true;
        else
            return false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isShootingCooldown() {
        return shootingCooldown;
    }

    public void setShootingCooldown(boolean shootingCooldown) {
        this.shootingCooldown = shootingCooldown;
    }

    public long getShootingCooldownLeft() {
        return shootingCooldownLeft;
    }

    public void setShootingCooldownLeft(long shootingCooldownLeft) {
        this.shootingCooldownLeft = shootingCooldownLeft;
    }

    public long getLastShotAt() {
        return lastShotAt;
    }

    public void setLastShotAt(long lastShotAt) {
        this.lastShotAt = lastShotAt;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getMaxShield() {
        return maxShield;
    }

    public void setMaxShield(double maxShield) {
        this.maxShield = maxShield;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public double getCurrentShield() {
        return currentShield;
    }

    public void setCurrentShield(double currentShield) {
        this.currentShield = currentShield;
    }public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setDefaultActiveWeapon()
    {
        this.activeWeapon = this.ship.getListOfWeapons().get(0);
    }

    public void setSourceToWeapons()
    {
        for(Weapon w : this.ship.getListOfWeapons())
        {
            w.setSource(this);
        }
    }

}

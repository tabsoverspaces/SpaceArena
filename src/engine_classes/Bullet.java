package engine_classes;

import gui.battle_gui.Battleship;
import gui.interfaces.ApplyDamageInterface;
import gui.interfaces.Drawable;
import gui.interfaces.Movable;
import gui.interfaces.TakeDamageInterface;

import java.awt.*;

import static engine_classes.Direction.UP;

public class Bullet implements Drawable, Movable, TakeDamageInterface, ApplyDamageInterface
{
    private Battleship source;

    private int x;
    private int y;
    private int z;
    private final int width = 5;
    private final int height = 5;

    private double baseMovementSpeed;
    private double baseDamage;

    private double bonusMovementSpeed;
    private double bonusDamage;

    private Direction direction;

    private boolean isActive;

    public Bullet(Direction dir)
    {
        this.isActive = true;
        this.source = source;
        this.direction = dir;

        this.z = 0;


        this.baseMovementSpeed = 3;
        this.baseDamage = 10;

        this.bonusMovementSpeed = 0;
        this.bonusDamage = 0;
    }

    public int getSpeedMultiplier()
    {
        if(this.direction == UP)
            return -1;
        else
            return 1;
    }

    public double getTotalMovementSpeed()
    {
        return (this.baseMovementSpeed * this.getSpeedMultiplier()) + (this.bonusMovementSpeed * this.getSpeedMultiplier());
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getBaseMovementSpeed() {
        return baseMovementSpeed;
    }

    public void setBaseMovementSpeed(double baseMovementSpeed) {
        this.baseMovementSpeed = baseMovementSpeed;
    }

    public double getBonusMovementSpeed() {
        return bonusMovementSpeed;
    }

    public void setBonusMovementSpeed(double bonusMovementSpeed) {
        this.bonusMovementSpeed = bonusMovementSpeed;
    }

    @Override
    public void draw(Graphics graphics) {

        Graphics2D g2 = (Graphics2D)graphics;
        g2.setColor(Color.black);

        // draw initial box and get its stats
        g2.drawOval(this.x, this.y, this.width, this.height);


        // then draw all weapons respectively
    }

    public boolean isActive()
    {
        return this.isActive;
    }

    public Battleship getSource()
    {
        return this.source;
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void move() {

        this.y += this.getTotalMovementSpeed();
    }

    public void setSource(Battleship ship)
    {
        this.source= ship;
    }

    @Override
    public void applyDamage(TakeDamageInterface target) {

        this.isActive = false;
    }

    @Override
    public void takeDamage(ApplyDamageInterface source) {
        // nothing for now
    }

    public double getAppliableDamage()
    {
        return this.getTotalDamage();
    }

    public double getTotalDamage()
    {
        return this.baseDamage + this.bonusDamage;
    }

    public int getZ()
    {
        return this.z;
    }

    public void setZ(int z){
        this.z = z;
    }
}

package engine_classes.items.weapons.bullets;

import engine_classes.items.weapons.Weapon;
import gui.battle_gui.Battleship;
import interfaces.ApplyDamageInterface;
import interfaces.TakeDamageInterface;

import java.awt.*;

public class ShotgunGauge extends Bullet {

    private int xMoveSpeed;
    private int yMoveSpeed;

    private double startingDamage;
    private int startingX;
    private int startingY;

    private int currentX;
    private int currentY;

    private double maximumDistance; // hipotenouse between x and y

    private ShotgunGauge(Weapon source)
    {
        super(source);
    }

    public ShotgunGauge(Weapon source, int xMoveSpeed, int yMoveSpeed)
    {
        super(source);

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
    public boolean checkCollision(Battleship target) {
        return false;
    }

    @Override
    public boolean checkOutOfBounds(Rectangle bounds) {
        return false;
    }

    @Override
    public double getBulletDamage() {
        return 80;
    }

    @Override
    public void drawBullet(Graphics g) {

    }

    @Override
    public void updateBullet() {

    }

    @Override
    public void applyDamage(TakeDamageInterface target) {

    }

    @Override
    public void takeDamage(ApplyDamageInterface source) {

    }
}
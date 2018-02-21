package engine_classes;

import gui.interfaces.Drawable;

import javax.swing.*;
import java.awt.*;

public class Ship{

    private String name;

    private double baseHealth;
    private double baseShield;
    private double baseShieldRegen;
    private double baseMovementSpeed;
    private double baseFireRate;

    private double bonusHealth;
    private double bonusShield;
    private double bonusShieldRegen;
    private double bonusMovementSpeed;
    private double bonusFireRate;


    public Ship()
    {
        this.baseHealth = 100;
        this.baseShield = 50;
        this.baseShieldRegen = 0;
        this.baseMovementSpeed = 10; // probably in pixels but idk, need to define screen size
        this.baseFireRate = 10; // attacks per second, cd = 1/firerate


        this.bonusHealth = 0;
        this.bonusShield = 0;
        this.bonusShieldRegen = 0;
        this.bonusMovementSpeed = 0;
        this.bonusFireRate = 0;

    }

    public double getTotalFireRate()
    {
        return this.baseFireRate + this.bonusFireRate;
    }

    public double getTotalHealth()
    {
        return this.baseHealth + this.bonusHealth;
    }
    public double getTotalMovementSpeed()
    {
        return this.baseMovementSpeed + this.bonusMovementSpeed;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(double baseHealth) {
        this.baseHealth = baseHealth;
    }

    public double getBaseShield() {
        return baseShield;
    }

    public void setBaseShield(double baseShield) {
        this.baseShield = baseShield;
    }

    public double getBaseShieldRegen() {
        return baseShieldRegen;
    }

    public void setBaseShieldRegen(double baseShieldRegen) {
        this.baseShieldRegen = baseShieldRegen;
    }

    public double getBaseMovementSpeed() {
        return baseMovementSpeed;
    }

    public void setBaseMovementSpeed(double baseMovementSpeed) {
        this.baseMovementSpeed = baseMovementSpeed;
    }

    public double getBonusHealth() {
        return bonusHealth;
    }

    public void setBonusHealth(double bonusHealth) {
        this.bonusHealth = bonusHealth;
    }

    public double getBonusShield() {
        return bonusShield;
    }

    public void setBonusShield(double bonusShield) {
        this.bonusShield = bonusShield;
    }

    public double getBonusShieldRegen() {
        return bonusShieldRegen;
    }

    public void setBonusShieldRegen(double bonusShieldRegen) {
        this.bonusShieldRegen = bonusShieldRegen;
    }

    public double getBonusMovementSpeed() {
        return bonusMovementSpeed;
    }

    public void setBonusMovementSpeed(double bonusMovementSpeed) {
        this.bonusMovementSpeed = bonusMovementSpeed;
    }

    public double getTotalShield()
    {
        return this.baseShield + this.bonusShield;
    }
}

package engine_classes;

import engine_classes.items.engines.BaseEngine;
import engine_classes.items.engines.Engine;
import engine_classes.items.shields.Shield;
import engine_classes.items.weapons.PopGun;
import engine_classes.items.weapons.Shotgun;
import engine_classes.items.weapons.Weapon;
import gui.battle_gui.Battleship;

import java.util.ArrayList;
import java.util.LinkedList;

public class Ship{

    private String name;

    private double baseHealth;
    private double baseShield;
    private double baseShieldRegen;

    private double bonusHealth;
    private double bonusShield;
    private double bonusShieldRegen;

    // equipment
    private LinkedList<Weapon> listOfWeapons;
    private ArrayList<Shield> listOfShields;

    private Engine engine;


    public Ship()
    {
        this.baseHealth = 100;
        this.baseShield = 50;
        this.baseShieldRegen = 0;


        this.bonusHealth = 0;
        this.bonusShield = 0;
        this.bonusShieldRegen = 0;

        //
        this.listOfShields = new ArrayList<>();
        this.listOfWeapons = new LinkedList<>();

        // equip base engine
        this.engine = new BaseEngine();

        // wepaos
        this.addWeapon(new PopGun());
        this.addWeapon(new Shotgun());




    }

    public Battleship createBattleship()
    {
        return new Battleship(this);
    }

    public void addWeapon(Weapon weapon)
    {
        this.listOfWeapons.add(weapon);
    }


    public double getTotalHealth()
    {
        return this.baseHealth + this.bonusHealth;
    }
    public double getTotalMovementSpeed()
    {
        return this.getEngineBonusMovementSpeed();
    }

    public double getEngineBonusMovementSpeed()
    {
        return this.engine.getMovementSpeedValue();
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

    public double getTotalShield()
    {
        return this.baseShield + this.bonusShield;
    }

    public LinkedList<Weapon> getListOfWeapons() {
        return listOfWeapons;
    }

    public void setListOfWeapons(LinkedList<Weapon> listOfWeapons) {
        this.listOfWeapons = listOfWeapons;
    }

    public ArrayList<Shield> getListOfShields() {
        return listOfShields;
    }

    public void setListOfShields(ArrayList<Shield> listOfShields) {
        this.listOfShields = listOfShields;
    }
}

package engine_classes.player;

import engine_classes.ship.Ship;

import java.util.ArrayList;

public class Player {

    private String name;

    private double primaryBalance;
    private double secondaryBalance;

    private Ship activeShip;
    private ArrayList<Ship> listOfShips;

    public Player()
    {
        this.listOfShips = new  ArrayList<>();
    }

    public Ship getActiveShip()
    {
        return this.activeShip;
    }
}

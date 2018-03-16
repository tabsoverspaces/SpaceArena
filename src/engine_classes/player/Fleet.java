package engine_classes.player;

import engine_classes.ship.Ship;

import java.util.ArrayList;

public class Fleet {

    private ArrayList<Ship> listOfShips;

    public Fleet()
    {
        this.listOfShips = new ArrayList<>();
    }

    public void addShip(Ship ship)
    {
        this.listOfShips.add(ship);
    }

    public ArrayList<Ship> getListOfShips()
    {
        return this.listOfShips;
    }
}

package engine_classes.player;

import engine_classes.ship.Ship;

public class Player {

    private String name;

    private Stats stats;

    private Fleet fleet;
    private Warehouse warehouse;

    private Ship activeShip;

    public Player()
    {
        this.stats = new Stats();
        this.fleet = new Fleet();
        this.warehouse = new Warehouse();

        // tester
        Ship ship = new Ship();
        this.fleet.addShip(ship);

        this.setDefaultActiveShip();
    }

    public void setDefaultActiveShip()
    {
        this.activeShip = this.fleet.getListOfShips().get(0);
    }

    public Ship getActiveShip()
    {
        return this.activeShip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public void setActiveShip(Ship activeShip) {
        this.activeShip = activeShip;
    }
}

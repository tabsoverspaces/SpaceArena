package engine_classes.items.shields;

import engine_classes.items.Item;

public abstract class Shield extends Item {

    private double shieldValue;

    public Shield()
    {

    }

    public Shield(double shieldValue)
    {
        this.shieldValue = shieldValue;
    }

    public double getShieldValue()
    {return this.shieldValue;}
}

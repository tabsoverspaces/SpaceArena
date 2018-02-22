package engine_classes.items.engines;

import engine_classes.items.Item;

public abstract class Engine extends Item{

    private double movementSpeedValue;

    public abstract double getMovementSpeedValue();
}

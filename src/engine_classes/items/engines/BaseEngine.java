package engine_classes.items.engines;

public class BaseEngine extends Engine {

    public BaseEngine()
    {

    }

    @Override
    public double getItemValue() {
        return 0;
    }

    public double getMovementSpeedValue()
    {
        return 10;
    }

}

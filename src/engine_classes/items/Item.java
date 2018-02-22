package engine_classes.items;

public abstract class Item {

    private String name;


    public Item()
    {

    }

    public Item(String name)
    {
        this();

        this.name = name;
    }

    public abstract double getItemValue();

}

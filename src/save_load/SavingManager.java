package save_load;

import engine_classes.player.Player;

public class SavingManager {

    public static final String MAIN_PATH = "Data/";

    public SavingManager()
    {

    }

    public static void save(Player player)
    {
        new PlayerPersistance().savePlayerData(player);
    }

    public static void load()
    {

    }


}

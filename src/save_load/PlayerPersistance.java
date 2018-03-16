package save_load;

import engine_classes.player.Player;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PlayerPersistance {

    private final String playersDirectory = "players/";

    public void savePlayerData(Player player)
    {
        this.savePlayerStats(player);
        this.savePlayerFleet(player);
        this.savePlayerWarehouse(player);
    }

    private void savePlayerStats(Player player)
    {
        String savePath = SavingManager.MAIN_PATH + this.playersDirectory + "/"+player.getName();

        // do saving
        try{
            FileOutputStream fout = new FileOutputStream(savePath);
            ObjectOutputStream out = new ObjectOutputStream(fout);

        }catch(IOException e)
        {

        }
    }

    private void savePlayerFleet(Player player)
    {
        String savePath = SavingManager.MAIN_PATH + this.playersDirectory + "/"+player.getName()
                +"/"+"fleet";

        // do saving
        try{
            FileOutputStream fout = new FileOutputStream(savePath);
            ObjectOutputStream out = new ObjectOutputStream(fout);

        }catch(IOException e)
        {

        }
    }
    private void savePlayerWarehouse(Player player)
    {
        String savePath = SavingManager.MAIN_PATH + this.playersDirectory + "/"+player.getName()
                +"/"+"warehouse";

        // do saving
        try{
            FileOutputStream fout = new FileOutputStream(savePath);
            ObjectOutputStream out = new ObjectOutputStream(fout);

        }catch(IOException e)
        {

        }
    }
}

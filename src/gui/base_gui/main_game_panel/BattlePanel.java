package gui.base_gui.main_game_panel;

import engine_classes.battles.Battle;
import gui.base_gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class BattlePanel extends JPanel implements Runnable{

    private Battle battle; // the battle objects that i being drawn and ran

    public BattlePanel(Battle battle)
    {
        // setup dimensons
        this.setBounds(0, 0, MainFrame.width, MainFrame.height);

        this.battle = battle;

        this.battle.getBattleship1().setParentPanel(this);
        this.battle.getBattleship2().setParentPanel(this);

        this.battle.getBattleship1().init();
        this.battle.getBattleship2().init();

        this.addKeyListener(this.battle);

    }

    public void paintComponent(Graphics g)
    {
        // clear
        g.setColor(Color.WHITE);
        g.fillRect(0 , 0 , 2000, 1000);

        this.battle.drawBattle(g);
    }

    public void startBattle()
    {
        this.battle.startGame();
    }

    private void update()
    {
        this.setFocusable(true);
        this.grabFocus();

        this.battle.updateBattle();

        this.repaint();
    }




    // main game flow method
    public void run()
    {
        int frameCount = 30;

        //This value would probably be stored elsewhere.
        final double GAME_HERTZ = 30.0;
        //Calculate how many ns each frame should take for our target game hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the game this many times before a new render.
        //If you're worried about visual hitches more than perfect timing, set this to 1.
        final int MAX_UPDATES_BEFORE_RENDER = 5;
        //We will need the last update time.
        double lastUpdateTime = System.nanoTime();
        //Store the last time we rendered.
        double lastRenderTime = System.nanoTime();

        //If we are able to get as high as this FPS, don't render again.
        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        //Simple way of finding FPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        while (this.battle.isBattleRunning())
        {
            double now = System.nanoTime();
            int updateCount = 0;

            if (!this.battle.isBattlePaused())
            {
                //Do as many game updates as we need to, potentially playing catchup.
                while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
                {

                    this.update();

                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                //If for some reason an update takes forever, we don't want to do an insane number of catchups.
                //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
                if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
                {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                //Render. To do so, we need to calculate interpolation for a smooth render.
                float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
//                drawGame(interpolation);
                lastRenderTime = now;

                //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
                while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
                {
                    Thread.yield();

                    //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                    //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                    //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
                    try {Thread.sleep(1);} catch(Exception e) {}

                    now = System.nanoTime();
                }
            }
        }
    }


}

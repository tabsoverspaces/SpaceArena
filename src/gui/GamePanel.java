package gui;

import engine_classes.Ship;
import engine_classes.items.weapons.bullets.Bullet;
import gui.battle_gui.Animation;
import gui.battle_gui.Battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class GamePanel extends JPanel implements KeyListener, Runnable {

    private boolean gameRunning;
    private boolean paused;

    private Battleship bs1;
    private Battleship bs2;

    private ArrayList<Bullet> listOfBullets; // array used to store all the bullets that are currently alive/on-screenp
    private ArrayList<Bullet> tempList;
    private ArrayList<Animation> listOfAnimation;

    private final Set<Integer> pressed = new HashSet<Integer>();

    public GamePanel()
    {
        this.gameRunning = true;
        this.paused = false;

        this.listOfBullets = new ArrayList<>();
        this.tempList = new ArrayList<>();

        this.listOfAnimation = new ArrayList<>();
    }

    public GamePanel(Ship ship1, Ship ship2)
    {
        this();

        this.setBackground(Color.RED);

        this.bs1 = new Battleship(ship1, 1);
        this.bs2 = new Battleship(ship2, -1);

        this.addKeyListener(this);


    }

    public void init()
    {
        this.bs1.setParentPanel(this);
        this.bs2.setParentPanel(this);

        this.bs1.init();
        this.bs2.init();

        this.setFocusable(true);
    }

    public void paintComponent(Graphics g){

        // cler
        g.setColor(Color.WHITE);
        g.fillRect(0 , 0 , 2000, 1000);


        g.setColor(Color.black);
        this.bs1.draw(g);
        this.bs2.draw(g);

        for(Bullet b : this.listOfBullets)
        {
            b.drawBullet(g);
        }

        for(int i = 0 ; i < this.listOfAnimation.size();i++)
        {
            Animation a = this.listOfAnimation.get(i);

            a.draw(g);
        }



    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.pressed.add(e.getKeyCode());

            int  c = e.getKeyCode();

            if (c == KeyEvent.VK_W) {
                this.bs1.moveUp();
            }
            if (c == KeyEvent.VK_S) {

                this.bs1.moveDown();
            }
            if (c == KeyEvent.VK_D) {

                this.bs1.moveRight();
            }
            if (c == KeyEvent.VK_A) {

                this.bs1.moveLeft();
            }

            if (c == KeyEvent.VK_SPACE) {
                if (!this.bs1.isOnShootingCooldown())
                    this.tempList.add(this.bs1.shoot());
            }

            if(c==KeyEvent.VK_B)
            {
                if(!this.bs1.isOnDodgeCooldown())
                {
                    this.bs1.dodge();

                    Animation a = new Animation(this.bs1.getX()+this.bs1.getWidth(), this.bs1.getY(), "Dodging!");

                    this.addAnimation(a);
                }
            }

            if(c==KeyEvent.VK_NUMPAD8)
            {
                this.bs2.moveUp();
            }
            if(c==KeyEvent.VK_NUMPAD4)
            {
                this.bs2.moveLeft();
            }
            if(c==KeyEvent.VK_NUMPAD6)
            {
                this.bs2.moveRight();
            }
            if(c==KeyEvent.VK_NUMPAD5)
            {
                this.bs2.moveDown();
            }
            if(c==KeyEvent.VK_NUMPAD0)
            {
                if (!this.bs2.isOnShootingCooldown())
                    this.tempList.add(this.bs2.shoot());
            }
            if(c==KeyEvent.VK_NUMPAD1)
            {
                if(!this.bs2.isOnDodgeCooldown())
                {
                    this.bs2.dodge();

                    Animation a = new Animation(this.bs2.getX()+this.bs2.getWidth(), this.bs2.getY()-(this.bs2.getHeight()/2), "Dodging!");

                    this.addAnimation(a);
                }
            }

    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        this.pressed.remove(e.getKeyChar());
    }



    // main game loop
    @Override
    public void run() {

        int fps;
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

        while (gameRunning)
        {
            double now = System.nanoTime();
            int updateCount = 0;

            if (!paused)
            {
                //Do as many game updates as we need to, potentially playing catchup.
                while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
                {
                    updateGame();

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

                //Update the frames we got.
                int thisSecond = (int) (lastUpdateTime / 1000000000);
                if (thisSecond > lastSecondTime)
                {
                    fps = frameCount;
                    frameCount = 0;
                    lastSecondTime = thisSecond;
            }

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

    private void updateGame()
    {
        if(this.gameOverCondition())
            gameRunning = false;

        this.bs1.update();
        this.bs2.update();

        this.updateBullets();
        this.updateAnimations();
        this.manageTemporaryList();
        this.repaint();
    }


    private void manageTemporaryList()
    {
        for(Bullet b : this.tempList)
        {
            this.listOfBullets.add(b);
        }

        this.tempList.clear();
    }

    private void updateBullets()
    {
        for(int i = 0 ; i < this.listOfBullets.size() ; i++)
        {
            Bullet b = this.listOfBullets.get(i);

            b.updateBullet();

            if(this.outOfBounds(b) || (!b.isActive()))
            {
                this.listOfBullets.remove(b);
            }

            if(b.getSource().getSource().equals(this.bs1))
            {
                this.collisionCheck(this.bs2, b);
            }
            else
            {
                this.collisionCheck(this.bs1, b);
            }
        }
    }

    private void updateAnimations(){
        for(int i = 0 ; i < this.listOfAnimation.size() ; i++)
        {
            Animation a = this.listOfAnimation.get(i);

            a.updateAnimation();

            if(!a.isActive())
            {
              this.listOfAnimation.remove(a);
            }


        }
    }





    public boolean collisionCheck(Battleship ship, Bullet b)
    {

        if(b.checkCollision(ship))
        {// damage ship
            ship.takeDamage(b);
            b.applyDamage(ship);

            this.listOfBullets.remove(b);

            Animation a = new Animation(b.getX() , b.getY() , ((-1)*b.getTotalDamage())+"");
            this.addAnimation(a);


            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean outOfBounds(Bullet b)
    {
        if(b.getY() > this.getHeight()|| (b.getY() < 0))
        {
            this.listOfBullets.remove(b);
            System.out.println("removed");
            return true;
        }
        return false;
    }

    private boolean gameOverCondition()
    {
        if(this.bs1.getCurrentHealth() <= 0 )
            return true;
        else if (this.bs2.getCurrentHealth() <= 0)
            return true;
        else return false;
    }

    private void addAnimation(Animation a)
    {
        a.startAnimation();

        this.listOfAnimation.add(a);
    }
}

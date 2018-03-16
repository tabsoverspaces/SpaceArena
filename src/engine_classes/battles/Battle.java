package engine_classes.battles;

import engine_classes.items.weapons.bullets.Bullet;
import engine_classes.player.Player;
import gui.base_gui.MainFrame;
import gui.battle_gui.Animation;
import gui.battle_gui.Battleship;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public abstract class Battle implements KeyListener {

    // player 1
    private Player player1;
    // player 1 ship
    private Battleship battleship1;

    // player 2
    private Player player2;
    // playe 2 ship
    private Battleship battleship2;

    // bullet list
    private ArrayList<Bullet> listOfBullets;
    // animation list
    private ArrayList<Animation> listOfAnimations;

    // game flow control variables
    private boolean battleRunning;
    private boolean battlePaused;


    /**
     * Main constructor
     */
    private Battle()
    {
        this.listOfBullets = new ArrayList<>();
        this.listOfAnimations = new ArrayList<>();

        this.battlePaused = false;
        this.battleRunning = false;
    }

    public Battle(Player player1, Player player2)
    {
        this();

        this.player1 = player1;
        this.player2 = player2;

        this.battleship1 = this.player1.getActiveShip().createBattleship();
        this.battleship1.setPlayerNo(1);

        this.battleship2 = this.player2.getActiveShip().createBattleship();
        this.battleship2.setPlayerNo(-1);

    }

    /**
     * Method used to update the battle
     */
    public void updateBattle()
    {
        if(this.battleOverCondition())
            this.battleRunning = false;

        this.battleship1.update();
        this.battleship2.update();

        this.updateBullets();
        this.updateAnimations();



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

    protected void updateAnimations()
    {
        for(int i = 0 ; i < this.listOfAnimations.size() ; i ++)
        {
            Animation a = this.listOfAnimations.get(i);

            a.updateAnimation();

            if(!a.isActive())
            {
              this.getListOfAnimations().remove(a);
            }
        }
    }

    protected void updateBullets()
    {
        for(int i = 0 ; i < this.listOfBullets.size();i++)
        {
            Bullet b = this.listOfBullets.get(i);

            b.updateBullet();


            if(b.getSource().getSource().equals(this.battleship1))
            {
                this.collisionCheck(this.battleship2, b);
            }
            else
            {
                this.collisionCheck(this.battleship1, b);
            }

            if(this.outOfBounds(b) || (!b.isActive()))
            {
                this.listOfBullets.remove(b);
                continue;
            }
        }
    }

    protected boolean outOfBounds(Bullet b)
    {
        if(b.checkOutOfBounds(new Rectangle(0,0, MainFrame.width,MainFrame.height)))
        {
            this.listOfBullets.remove(b);
            System.out.println("removed");
            return true;
        }
        return false;
    }

    /**
     * Method used to draw the battle elements
     * @param g
     */
    public void drawBattle(Graphics g)
    {
        g.setColor(Color.black);
        this.battleship1.draw(g);
        this.battleship2.draw(g);

        // draw bullets first
        for(int i = 0; i < this.listOfBullets.size() ; i++)
        {
            Bullet b = this.listOfBullets.get(i);

            b.drawBullet(g);
        }

        // draw animations second
        for(int i = 0 ; i < this.listOfAnimations.size();i++)
        {
            Animation a = this.listOfAnimations.get(i);

            a.draw(g);
        }
    }

    /**
     * Method used to check if battle is done
     * @return
     */
    protected boolean battleOverCondition()
    {
        if(this.battleship1.getCurrentHealth() <= 0 )
            return true;
        else if (this.battleship2.getCurrentHealth() <= 0)
            return true;

        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e) {

            int  c = e.getKeyCode();

            if (c == KeyEvent.VK_W) {
                this.battleship1.moveUp();
            }
            if (c == KeyEvent.VK_S) {

                this.battleship1.moveDown();
            }
            if (c == KeyEvent.VK_D) {

                this.battleship1.moveRight();
            }
            if (c == KeyEvent.VK_A) {

                this.battleship1.moveLeft();
            }

            if (c == KeyEvent.VK_SPACE) {
                if (!this.battleship1.getActiveWeapon().isShootingCooldown())
                    this.addBullets(this.battleship1.shoot());
            }

            if(c==KeyEvent.VK_B)
            {
                if(!this.battleship1.isOnDodgeCooldown())
                {
                    this.battleship1.dodge();

                    Animation a = new Animation(this.battleship1.getX()+this.battleship1.getWidth(), this.battleship1.getY(), "Dodging!");

                    this.addAnimation(a);
                }
            }

            if(c==KeyEvent.VK_Q)
            {
                this.battleship1.changeToPreviousWeapon();
                Animation a = new Animation(this.battleship1.getX() + this.battleship1.getWidth() , this.battleship1.getY(), "Changed weapon");

                this.addAnimation(a);
            }
            if(c==KeyEvent.VK_E)
            {
                this.battleship1.changeToNextWeapon();
                Animation a = new Animation(this.battleship1.getX() + this.battleship1.getWidth() , this.battleship1.getY(), "Changed weapon");

                this.addAnimation(a);
            }

    }

    public void addAnimation(Animation a)
    {
        a.startAnimation();

        this.getListOfAnimations().add(a);
    }


    public void addBullets(Bullet[] array)
    {
        for(int i = 0 ; i  < array.length ; i++)
        {
            Bullet b = array[i];

            this.listOfBullets.add(b);
        }
    }




    // game flow control methods
    public void pauseBattle()
    {
        this.battlePaused = true;
    }
    public void unpauseBattle()
    {
        this.battlePaused = false;
    }
    public void startGame()
    {
        this.battleRunning = true;
    }
    public void stopGame()
    {
        this.battleRunning = false;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Battleship getBattleship1() {
        return battleship1;
    }

    public void setBattleship1(Battleship battleship1) {
        this.battleship1 = battleship1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Battleship getBattleship2() {
        return battleship2;
    }

    public void setBattleship2(Battleship battleship2) {
        this.battleship2 = battleship2;
    }

    public ArrayList<Bullet> getListOfBullets() {
        return listOfBullets;
    }

    public void setListOfBullets(ArrayList<Bullet> listOfBullets) {
        this.listOfBullets = listOfBullets;
    }

    public ArrayList<Animation> getListOfAnimations() {
        return listOfAnimations;
    }

    public void setListOfAnimations(ArrayList<Animation> listOfAnimations) {
        this.listOfAnimations = listOfAnimations;
    }

    public boolean isBattleRunning() {
        return battleRunning;
    }

    public void setBattleRunning(boolean battleRunning) {
        this.battleRunning = battleRunning;
    }

    public boolean isBattlePaused() {
        return battlePaused;
    }

    public void setBattlePaused(boolean battlePaused) {
        this.battlePaused = battlePaused;
    }
}

package gui.battle_gui;

import engine_classes.Player;
import engine_classes.Ship;
import engine_classes.items.weapons.bullets.Bullet;

import java.awt.*;
import java.util.ArrayList;

public class Battle {

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


    public Battle()
    {
        this.listOfBullets = new ArrayList<>();
        this.listOfAnimations = new ArrayList<>();

        this.battlePaused = false;
        this.battleRunning = false;
    }

    public void init(Player player1, Player player2)
    {
        this.player1 = player1;
        this.player2 = player2;

        this.battleship1 = this.player1.getActiveShip().createBattleship();
        this.battleship1.setPlayerNo(1);

        this.battleship2 = this.player2.getActiveShip().createBattleship();
        this.battleship2.setPlayerNo(-1);
    }

    public void updateBattle()
    {
        if(this.battleOverCondition())
            this.battleRunning = false;

        this.battleship1.update();
        this.battleship2.update();

        for(int i = 0 ; i < this.listOfBullets.size();i++)
        {
            Bullet b = this.listOfBullets.get(i);

            b.updateBullet();
        }

        for(int i = 0 ; i < this.listOfAnimations.size() ; i ++)
        {
            Animation a = this.listOfAnimations.get(i);

            a.updateAnimation();
        }
    }




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

    private boolean battleOverCondition()
    {
        if(this.battleship1.getCurrentHealth() <= 0 )
            return true;
        else if (this.battleship2.getCurrentHealth() <= 0)
            return true;

        return false;
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

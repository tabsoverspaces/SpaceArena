package engine_classes.battles;

import engine_classes.player.Player;
import gui.battle_gui.Animation;

import java.awt.event.KeyEvent;

public class BattlePVP extends Battle {

    public BattlePVP(Player player1, Player player2)
    {
        super(player1, player2);
    }


    @Override
    public void keyPressed(KeyEvent e) {

            int  c = e.getKeyCode();

            if (c == KeyEvent.VK_W) {
                this.getBattleship1().moveUp();
            }
            if (c == KeyEvent.VK_S) {

                this.getBattleship1().moveDown();
            }
            if (c == KeyEvent.VK_D) {

                this.getBattleship1().moveRight();
            }
            if (c == KeyEvent.VK_A) {

                this.getBattleship1().moveLeft();
            }

            if (c == KeyEvent.VK_SPACE) {
                if (!this.getBattleship1().getActiveWeapon().isShootingCooldown())
                    this.addBullets(this.getBattleship1().shoot());
            }

            if(c==KeyEvent.VK_B)
            {
                if(!this.getBattleship1().isOnDodgeCooldown())
                {
                    this.getBattleship1().dodge();

                    Animation a = new Animation(this.getBattleship1().getX()+this.getBattleship1().getWidth(), this.getBattleship1().getY(), "Dodging!");

                    this.addAnimation(a);
                }
            }

            if(c==KeyEvent.VK_Q)
            {
                this.getBattleship1().changeToPreviousWeapon();
                Animation a = new Animation(this.getBattleship1().getX() + this.getBattleship1().getWidth() , this.getBattleship1().getY(), "Changed weapon");

                this.addAnimation(a);
            }
            if(c==KeyEvent.VK_E)
            {
                this.getBattleship1().changeToNextWeapon();
                Animation a = new Animation(this.getBattleship1().getX() + this.getBattleship1().getWidth() , this.getBattleship1().getY(), "Changed weapon");

                this.addAnimation(a);
            }

            // PLAYER 2
         if(c==KeyEvent.VK_NUMPAD8)
            {
                this.getBattleship2().moveUp();
            }
            if(c==KeyEvent.VK_NUMPAD4)
            {
                this.getBattleship2().moveLeft();
            }
            if(c==KeyEvent.VK_NUMPAD6)
            {
                this.getBattleship2().moveRight();
            }
            if(c==KeyEvent.VK_NUMPAD5)
            {
                this.getBattleship2().moveDown();
            }
            if(c==KeyEvent.VK_NUMPAD0)
            {
                if (!this.getBattleship2().getActiveWeapon().isShootingCooldown())

                    this.addBullets(this.getBattleship2().shoot());
            }
            if(c==KeyEvent.VK_NUMPAD1)
            {
                if(!this.getBattleship2().isOnDodgeCooldown())
                {
                    this.getBattleship2().dodge();

                    Animation a = new Animation(this.getBattleship2().getX()+this.getBattleship2().getWidth(), this.getBattleship2().getY()-(this.getBattleship2().getHeight()/2), "Dodging!");

                    this.addAnimation(a);
                }
            }

            if(c==KeyEvent.VK_NUMPAD7)
            {
                this.getBattleship2().changeToPreviousWeapon();
                Animation a = new Animation(this.getBattleship2().getX() + this.getBattleship2().getWidth() , this.getBattleship2().getY(), "Changed weapon");

                this.addAnimation(a);
            }

            if(c==KeyEvent.VK_NUMPAD9)
            {
                this.getBattleship2().changeToNextWeapon();
                Animation a = new Animation(this.getBattleship2().getX() + this.getBattleship2().getWidth() , this.getBattleship2().getY(), "Changed weapon");

                this.addAnimation(a);
            }

    }
}

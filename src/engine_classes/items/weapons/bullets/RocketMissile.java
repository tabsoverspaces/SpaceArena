package engine_classes.items.weapons.bullets;

import engine_classes.items.weapons.Weapon;
import gui.battle_gui.Battleship;

import java.awt.*;

public class RocketMissile extends Bullet{

    public RocketMissile(Weapon source)
    {











        super(source);
    }



    @Override
    public boolean checkCollision(Battleship target) {
        return false;
    }

    @Override
    public boolean checkOutOfBounds(Rectangle bounds) {
        return false;
    }

    @Override
    public void initiateBaseMovementSpeed() {

    }

    @Override
    public void drawBullet(Graphics g) {

    }

    @Override
    public void updateBullet() {

    }
}

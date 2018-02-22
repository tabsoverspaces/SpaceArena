package engine_classes.items.weapons.bullets;

import engine_classes.items.weapons.Weapon;

import java.awt.*;

public class BasicBullet extends Bullet {



    public BasicBullet(Weapon source)
    {
        super(source);
    }


    @Override
    public void drawBullet(Graphics g) {


    }

    @Override
    public void updateBullet() {

    }

    @Override
    public double getBaseMovementSpeed() {
        return 40;
    }


}

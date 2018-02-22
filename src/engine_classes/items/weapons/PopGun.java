package engine_classes.items.weapons;

import engine_classes.items.weapons.bullets.BasicBullet;
import engine_classes.items.weapons.bullets.Bullet;

public class PopGun extends Weapon {

    @Override
    public Bullet getBulletModel() {
        return new BasicBullet(this.getDamage());
    }

    @Override
    public double getItemValue() {
        return 0;
    }
}

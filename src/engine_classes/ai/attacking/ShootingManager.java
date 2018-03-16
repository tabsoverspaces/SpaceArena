package engine_classes.ai.attacking;

import engine_classes.battles.BattleAI;

public abstract class ShootingManager {

    private BattleAI battleAI;

    private ShootCooldown shootCooldown;
    private WeaponChangeCooldown weaponChangeCooldown;

    private ShootingManager()
    {
        this.shootCooldown = new ShootCooldown();
        this.weaponChangeCooldown = new WeaponChangeCooldown();
    }

    public ShootingManager(BattleAI battle)
    {
        this();
        this.battleAI = battle;
    }

    public void shoot()
    {
        if(!this.shootCooldown.isShootOnCooldown()) {
            this.battleAI.addBullets(this.battleAI.getBattleship2().shoot());
            this.shootCooldown.shoot();
        }
    }

    public void update()
    {
        this.shootCooldown.update();
    }

    public ShootCooldown getShootCooldown() {
        return shootCooldown;
    }

    public BattleAI getBattleAI()
    {
        return this.battleAI;
    }

    public WeaponChangeCooldown getWeaponChangeCooldown() {
        return weaponChangeCooldown;
    }
}

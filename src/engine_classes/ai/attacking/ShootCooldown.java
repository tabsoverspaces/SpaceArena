package engine_classes.ai.attacking;

public class ShootCooldown {

    private long lastShootAt;
    private boolean shootOnCooldown;

    private final double  MAXIMUM_SHOTS_PER_SECOND = 2;

    public ShootCooldown()
    {
        this.lastShootAt = System.nanoTime();
        this.shootOnCooldown = false;
    }
    public void update()
    {
        if(this.shootOnCooldown)
        {
            this.updateMovementCooldown();
        }
    }

    private void updateMovementCooldown()
    {
        long timeNow = System.nanoTime();

        if (timeNow - this.lastShootAt >= ((1 / this.MAXIMUM_SHOTS_PER_SECOND * 1_000_000_000))) {
            this.deactivateShootCooldown();
        }
    }

    // main move API method
    public void shoot()
    {
        this.activateShootCooldown();
    }

    public boolean isShootOnCooldown()
    {
        return this.shootOnCooldown;
    }

    private void activateShootCooldown()
    {
        this.shootOnCooldown = true;
        this.updateTimeVariables();
    }

    private void deactivateShootCooldown()
    {
        this.shootOnCooldown = false;
    }


    private void updateTimeVariables()
    {
        this.lastShootAt = System.nanoTime();
    }

}

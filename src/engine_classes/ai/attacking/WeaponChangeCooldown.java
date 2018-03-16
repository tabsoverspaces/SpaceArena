package engine_classes.ai.attacking;

public class WeaponChangeCooldown {

    private long lastChangeAt;
    private boolean changeOnCooldown;

    private final double MINIMUM_CHANGE_COOLDOWN = 5; // in seconds

    public WeaponChangeCooldown()
    {
        this.lastChangeAt = System.nanoTime();
        this.changeOnCooldown = false;
    }
    public void update()
    {
        if(this.changeOnCooldown)
        {
            this.updateChangeCooldown();
        }
    }

    private void updateChangeCooldown()
    {
        long timeNow = System.nanoTime();

        if (timeNow - this.lastChangeAt >= ((this.MINIMUM_CHANGE_COOLDOWN * 1_000_000_000))) {
            this.deactivateChangeCooldown();
        }
    }

    // main move API method
    public void change()
    {
        this.activateChangeCooldown();
    }

    public boolean isChangeOnCooldown()
    {
        return this.changeOnCooldown;
    }

    private void activateChangeCooldown()
    {
        this.changeOnCooldown = true;
        this.updateTimeVariables();
    }

    private void deactivateChangeCooldown()
    {
        this.changeOnCooldown = false;
    }


    private void updateTimeVariables()
    {
        this.lastChangeAt = System.nanoTime();
    }
}

package engine_classes.ai.movement;

public class MoveCooldown {

    private long lastMoveAt;
    private boolean moveOnCooldown;

    private final double  MAXIMUM_MOVES_PER_SECOND = 100;



    public MoveCooldown()
    {
        this.lastMoveAt = System.nanoTime();
        this.moveOnCooldown = false;
    }
    public void update()
    {
        if(this.moveOnCooldown)
        {
            this.updateMovementCooldown();
        }
    }

    private void updateMovementCooldown()
    {
        long timeNow = System.nanoTime();

        if (timeNow - this.lastMoveAt >= ((1 / this.MAXIMUM_MOVES_PER_SECOND * 1_000_000_000))) {
            this.deactivateMoveCooldown();
        }
    }

    // main move API method
    public void move()
    {
        this.activateMoveCooldown();
    }

    public boolean isMoveOnCooldown()
    {
        return this.moveOnCooldown;
    }

    private void activateMoveCooldown()
    {
        this.moveOnCooldown = true;
        this.updateTimeVariables();
    }

    private void deactivateMoveCooldown()
    {
        this.moveOnCooldown = false;
    }


    private void updateTimeVariables()
    {
        this.lastMoveAt = System.nanoTime();
    }






}

package engine_classes.ai_control;

public class MoveCooldown {

    private long lastMoveAt;
    private boolean moveOnCooldown;



    public MoveCooldown()
    {
        this.lastMoveAt = System.nanoTime();
        this.moveOnCooldown = false;
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

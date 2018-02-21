package gui.battle_gui;

import gui.interfaces.Drawable;

import java.awt.*;

public class Animation implements Drawable
{

    private final double duration = .5; // in seconds
    private final int distanceToCross = 20; // in pixels

    private int startingX;
    private int startingY;

    private int currentX;
    private int currentY;

    private boolean active;
    private String value;

    // time variables
    private long startingTime;

    /**
     *
     */
    public Animation()
    {
        active = false;
    }

    /**
     *
     * @param startingX
     * @param startingY
     */
    public Animation(int startingX , int startingY, String value)
    {
        this.startingX = startingX;
        this.startingY = startingY;

        this.value = value;
    }


    public void startAnimation() {
        this.active = true;

        this.startingTime = System.nanoTime();
    }
    public void updateAnimation(){

        // get current time
        long timeDifference = (System.nanoTime() - this.startingTime);

        double currentPercentage = timeDifference / (this.duration*1_000_000_000.0);

        System.out.println(currentPercentage);

        this.currentX = this.startingX +  (int) (this.distanceToCross * (currentPercentage));
        this.currentY = this.startingY -  (int) (this.distanceToCross * (currentPercentage));


        if(timeDifference>= this.duration * 1_000_000_000)
        {
            this.stopAnimation();
        }
    }
    public void stopAnimation()
    {
        this.active = false;
    }


    @Override
    public void draw(Graphics graphics) {

        graphics.setColor(Color.black);
        graphics.drawString(this.value + "" , this.currentX, this.currentY);

    }

    public boolean isActive()
    {
        return this.active;
    }
}

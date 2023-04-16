import java.awt.Color;
import java.awt.Graphics;

//makes villain a subclass which extends Actor
public class Villain extends Actor
{
    //integer value for the hypotenuse length which the Villain can travel per update
    private int SingleMovement = 5;

    //integer values for the difference in x and y coordinates between a Villain and the Character
    private int xdifFromBob;
    private int ydifFromBob;

    //the length of the hypotenuse of a right-angled triangle between the coordinates of a Villain and the Character
    private float BobHypoteneuse;

    //original colour of the Villains
    private Color ogColour = new Color(255,0,0);

    //the colour which a Villain will change to after colliding with the Character
    private Color collisionColour = new Color(30,200,30);

    //variable which stores the current colour of a Villain
    //sets the current colour to ogColour
    private Color currentColour = ogColour;

    //allows instances of Villain to be created
    public Villain()
    {

    }

    //method used to draw a Villain
    public void draw(Graphics g2, int x, int y)
    {
        //stores the width of the JFrame() as a variable
        int w = this.getwidth();

        //draws a circle using the currentcolour,
        //diameter and x and y coordinates which are passed through
        g2.setColor(currentColour);
        g2.fillOval(x, y, w, w);

        //outlines the circle in black
        g2.setColor(new Color(0, 0, 0));
        g2.drawOval(x, y, w, w);
    }

    //returns SingleMovement
    public int getSingleMovement()
    {
        return SingleMovement;
    }

    //calculates the hypotenuse length of the right-angled triangle between 2 sets of coordinates passed through
    public void setHypoteneuseFromBob(int bx, int vx, int by, int vy)
    {
        //uses Pythagoras's theorem to calculate the hypotenuse
        xdifFromBob = bx - vx;
        ydifFromBob = by - vy;
        BobHypoteneuse = (float) Math.sqrt(Math.pow(xdifFromBob, 2) + Math.pow(ydifFromBob, 2));
    }

    //returns BobHypotenuse
    public float getHypoteneuseFromBob()
    {
        return BobHypoteneuse;
    }

    //sets the current colour to the original Villain colour
    public void setColourOG()
    {
        currentColour = ogColour;
    }

    //sets the current colour to the colour which is used after a Villain collides with the Character
    public void setColourCollision()
    {
        currentColour = collisionColour;
    }

    //returns SingleMovement
    public void setSingleMovement(int x)
    {
        SingleMovement = x;
    }
}
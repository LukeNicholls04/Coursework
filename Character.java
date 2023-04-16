import java.awt.Color;
import java.awt.Graphics;

//makes Character a subclass which extends Actor
public class Character extends Actor
{
    //integer value for the starting angle where the first arc in the Character is drawn from
    private int angle;

    //integer value used to determine which colour is used to draw with
    private int colrotation = 0;

    //integer value for the hypotenuse length which the Character can travel per update
    private int SingleMovement = 10;

    //allows instances of Character to be created
    public Character()
    {

    }

    //sets angle equal to an integer value passed through
    public void setAngle(int x)
    {
        angle = x;
    }

    //returns angle
    public int getAngle()
    {
        return angle;
    }

    //method used to draw the Character
    public void draw(Graphics g2, int x, int y)
    {
        //creates an integer value to store the width from Actor
        int width = this.getwidth();

        //stores a Color as a variable
        Color black = new Color(0,0,0);

        //integer value for the number of arcs which the Character is composed of
        int rotation = 9;

        //for loop to draw all the separate arcs which Character is composed of
        for(int i = 0; i < rotation; i++)
        {
            //determines the Color to draw each arc with
            //changes the colrotation value to ensure that the 3 colors alternate consecutively while drawing the arcs for the Charcter
            if(colrotation == 0)
            {
                g2.setColor(new Color(186,46,203));
                colrotation++;
            }
            else if(colrotation == 1)
            {
                g2.setColor(new Color(237,194,216));
                colrotation++;
            }
            else if(colrotation == 2)
            {
                g2.setColor(new Color(68,16,111));
                colrotation = 0;
            }

            //draws a single filled arc for the Character
            g2.fillArc(x, y, width, width, angle, 360/rotation);

            //outlines the arc in black
            g2.setColor(black);
            g2.drawArc(x, y, width, width, angle, 360/rotation);

            //changes to angle to be the starting angle at which the next arc will be drawn at
            angle = angle + 360/rotation;
        }
    }

    //returns SingleMovement
    public int getSingleMovement()
    {
        return SingleMovement;
    }
}
import java.awt.Color;
import java.awt.Graphics;
import java.awt.RenderingHints;

public class Character extends Actor
{
    private int angle = 0;
    private int colrotation = 0;
    private int SingleMovement = 10;
    
    public Character()
    {
    	
    }
    public void setAngle(int x)
    {
        angle = x;
    }
    public int getAngle()
    {
        return angle;
    }
    public void draw(Graphics g2, int x, int y)
    {
        int width = this.getwidth();
        Color black = new Color(0,0,0);
        int rotation = 9;
        for(int i = 0; i < rotation; i++)
        {
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
            g2.fillArc(x, y, width, width, angle, 360/rotation);
            g2.setColor(black);
            g2.drawArc(x, y, width, width, angle, 360/rotation);
            angle = angle + 360/rotation;
        }
    }
    public int getSingleMovement()
    {
        return SingleMovement;
    }
}

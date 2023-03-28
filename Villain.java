import java.awt.Color;
import java.awt.Graphics;

public class Villain extends Actor
{
    private int SingleMovement = 5;
    private int xdifFromBob;
    private int ydifFromBob;
    private float BobHypoteneuse;
    private Color ogColour = new Color(255,0,0);
    private Color collisionColour = new Color(55,90,55);
    private Color currentColour = ogColour;

    public Villain()
    {
    	
    }

    public void draw(Graphics g2, int x, int y)
    {
    	double w = this.getwidth();
    	int w1 = (int) w;
        g2.setColor(currentColour);
        g2.fillOval(x, y, w1, w1);
        g2.setColor(new Color(0, 0, 0));
        g2.drawOval(x, y, w1, w1);

    }

    public int getSingleMovement()
    {
        return SingleMovement;
    }

    public void setHypoteneuseFromBob(int bx, int vx, int by, int vy)
    {
        xdifFromBob = bx - vx;
        ydifFromBob = by - vy;
        BobHypoteneuse = (float) Math.sqrt(Math.pow(xdifFromBob, 2) + Math.pow(ydifFromBob, 2));
    }

    public int getxdifFromBob()
    {
        return xdifFromBob;
    }

    public int getydifFromBob()
    {
        return ydifFromBob;
    }

    public float getHypoteneuseFromBob()
    {
        return BobHypoteneuse;
    }

    public void setColourOG()
    {
        currentColour = ogColour;
    }

    public void setColourCollision()
    {
        currentColour = collisionColour;
    }
    
    public void setSingleMovement(int x) 
    {
    	SingleMovement = x;
    }

}
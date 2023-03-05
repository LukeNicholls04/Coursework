import java.awt.Color;
import java.awt.Graphics;

public class Answers
{
    private int width = 35;
    private int x;
    private int y;
    private Color currentColour = new Color(255,0,0);
    public Answers()
    {

    }
    public void setx(int a)
    {
        x = a;
    }
    public void sety(int a)
    {
        y = a;
    }
    public int getx()
    {
       return x;
    }
    public int gety()
    {
        return y;
    }
    public void draw(Graphics g2, int x, int y)
    {
        g2.setColor(currentColour);
        g2.fillRect(x,y,width,width);
        g2.setColor(new Color(0,0,0));
        g2.drawRect(x,y,width,width);
    }
}

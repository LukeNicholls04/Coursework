import java.awt.Color;
import java.awt.Graphics;

public class Answers
{
    private int width = 140;
    private int x;
    private int y;
    private boolean correct = false;
    private String text = "";
    private Color currentColour = new Color(255,174,188);
    public Answers()
    {

    }
    public void setText(String x) 
    {
    	text = x;
    }
    public String getText() 
    {
    	return text;
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
    public int getWidth() 
    {
    	return width;
    }
    public void setcolour(Color x) 
    {
    	currentColour = x;
    }
    public void setCorrect(boolean x)
    {
    	correct = x;
    }
    public boolean getCorrect() 
    {
    	return correct;
    }
    public void draw(Graphics g2, int x, int y)
    {
        g2.setColor(currentColour);
        g2.fillRect(x,y,width,width);
        g2.setColor(new Color(0,0,0));
        g2.drawRect(x,y,width,width);
    }
}
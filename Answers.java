import java.awt.Color;
import java.awt.Graphics;

public class Answers
{
    //integer value for the width of the Answers
    private int width = 70;

    //integer values for the x and y coordinates of the Answers
    private int x;
    private int y;

    //boolean value for whether the Answer box contains the correct answer to the question or not
    private boolean correct = false;

    //String value for the text which is drawn inside the Answer box
    private String text = "";

    //Color which is used to draw the Answers
    private Color currentColour = new Color(255,174,188);

    //allows instances of Answers to be created
    public Answers()
    {

    }

    //sets text equal to a String value passed through
    public void setText(String x)
    {
        text = x;
    }

    //returns text
    public String getText()
    {
        return text;
    }

    //sets x equal to an integer value passed through
    public void setx(int a)
    {
        x = a;
    }

    //sets y equal to an integer value passed through
    public void sety(int a)
    {
        y = a;
    }

    //returns x
    public int getx()
    {
        return x;
    }

    //returns y
    public int gety()
    {
        return y;
    }

    //returns width
    public int getWidth()
    {
        return width;
    }

    //sets the currentColour equal to a Color which is passed through
    public void setcolour(Color x)
    {
        currentColour = x;
    }

    //sets correct equal to a boolean value which is passed through
    public void setCorrect(boolean x)
    {
        correct = x;
    }

    //returns correct
    public boolean getCorrect()
    {
        return correct;
    }

    //method used to draw the Answers
    public void draw(Graphics g2, int x, int y)
    {
        //draws the Answers using the currentcolour, width and x and y coordinates which are passed through
        g2.setColor(currentColour);
        g2.fillRect(x,y,width,width);

        //outlines the Answers drawing in black
        g2.setColor(new Color(0,0,0));
        g2.drawRect(x,y,width,width);
    }
}
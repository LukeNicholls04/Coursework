import java.awt.Graphics;
public abstract class Actor
{
    //integer values for the x and y coordinates
    private int x;
    private int y;

    //integer value for the diameter
    private int width = 40;

    //integer values for the midpoint
    private int midx ;
    private int midy;

    //integer values for the next x and y coordinates once updated
    private int xMove;
    private int yMove;

    //integer values for the distance between 2 sets of coordinates
    private int xdif;
    private int ydif;

    //the length of the hypotenuse of a right-angled triangle between 2 sets of coordinates
    private double MovementHypoteneuse;

    //the scale factor required to scale down a right-angled triangle between 2 sets of coordinates to a specific hypotenuse length
    private double ScaleFactor ;

    //allows instances of Actor to be created
    public Actor()
    {

    }

    //abstract method which forces child classes to implement their own draw() method
    public abstract void draw(Graphics g2, int x, int y);

    //abstract integer which forces child classes to set their own hypotenuse length which their Actor can travel per update
    public abstract int getSingleMovement();

    //method to set the change in x and y coordinates which will be implemented during the next update
    public void setmoves(int px, int py, int mx, int my)
    {
        //similar maths to calculating unit vectors - this calculates the most accurate integer values for xMove and yMove which will form a triangle with a hypotenuse length of ScaleFactor
        xdif = px - mx;
        ydif = py - my;
        MovementHypoteneuse = Math.sqrt(Math.pow(xdif, 2) + Math.pow(ydif, 2));
        ScaleFactor = getSingleMovement()/MovementHypoteneuse;
        yMove = (int) (ScaleFactor*ydif);
        xMove = (int) (ScaleFactor*xdif);
    }

    //returns xMove
    public int getXMove()
    {
        return xMove;
    }

    //returns yMove
    public int getYMove()
    {
        return yMove;
    }

    //sets x equal to an integer value passed through
    public void setx(int a)
    {
        x = a;
    }

    //returns x
    public int getx()
    {
        return x;
    }

    //sets y equal to an integer value passed through
    public void sety(int a)
    {
        y = a;
    }

    //returns y
    public int gety()
    {
        return y;
    }

    //returns width
    public int getwidth()
    {
        return width;
    }

    //calculates the midpoint of the Actor based on its x and y coordinates which are passed through
    public void setMidpoint(int x, int y)
    {
        midx = x - this.getwidth()/2;
        midy = y - this.getwidth()/2;
    }

    //returns the y coordinate of the midpoint
    public int getMidy()
    {
        return midy;
    }

    //returns the x coordinate of the midpoint
    public int getMidx()
    {
        return midx;
    }

    //returns MovementHypoteneuse
    public double getHypoteneuse()
    {
        return MovementHypoteneuse;
    }
}
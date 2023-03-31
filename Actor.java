import java.awt.Graphics;

public abstract class Actor
{
    private int x;
    private int y;
    private int width = 75;
    private int midx = 0;
    private int midy = 0;
    private int xMove = 0;
    private int yMove = 0;
    private int xdif = 0;
    private int ydif = 0;
    private double MovementHypoteneuse = 0;
    private double ScaleFactor = 0;

    public Actor()
    {
    	
    }
    public abstract void draw(Graphics g2, int x, int y);
    public abstract int getSingleMovement();
    public void setmoves(int px, int py, int mx, int my)
    {
        xdif = px - mx;
        ydif = py - my;
        MovementHypoteneuse = Math.sqrt(Math.pow(xdif, 2) + Math.pow(ydif, 2));
        ScaleFactor = getSingleMovement()/MovementHypoteneuse;
        yMove = (int) (ScaleFactor*ydif);
        xMove = (int) (ScaleFactor*xdif);
    }
    public int getXMove()
    {
        return xMove;
    }
    public int getYMove()
    {
        return yMove;
    }
    public void setx(int a)
    {
        x = a;
    }
    public int getx()
    {
        return x;
    }
    public void sety(int a)
    {
        y = a;
    }
    public int gety()
    {
        return y;
    }
    public void setwidth(int a)
    {
        width = a;
    }
    public int getwidth()
    {
        return width;
    }
    public void setMidpoint(int x, int y)
    {
        midx = x - this.getwidth()/2;
        midy = y - this.getwidth()/2;
    }
    public int getMidy()
    {
        return midy;
    }
    public int getMidx()
    {
        return midx;
    }
    public double getHypoteneuse()
    {
        return MovementHypoteneuse;
    }
}

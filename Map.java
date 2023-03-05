import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Map extends JPanel implements ActionListener, KeyListener
{
    private int count = 0;
    private Questions question = new Questions();
    private Character bob = new Character();
    private ArrayList <Villain> villains = new ArrayList();
    private Timer animation = new Timer(40,this);
    private Timer countdownTimer = new Timer(1000,this);
    private Timer update = new Timer(40,this);
    private Timer Begin = new Timer(100,this);
    private Random rand = new Random();
    private ArrayList <Answers> answers = new ArrayList();

    public Map()
    {
        this.setBackground(new Color(80,80,80));
        for(int i = 0; i < 2; i++)
        {
            addVillain();
            villains.get(i).setColourOG();
        }
        for(int i = 0; i < 4; i++)
        {
            addAnswer();
            int Ranx = rand.nextInt(900);
            int Rany = rand.nextInt(500);
            answers.get(i).setx(Ranx);
            answers.get(i).sety(Rany);
        }
        animation.start();
        Begin.start();
    }

    private void addAnswer()
    {
        answers.add(new Answers());
    }
    private void addVillain()
    {
        villains.add(new Villain());
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        bob.draw(g2,bob.getx(),bob.gety());

        for(int i = 0; i < villains.size(); i++)
        {
            villains.get(i).draw(g2,villains.get(i).getx(),villains.get(i).gety());
        }

        for(int i = 0; i < answers.size(); i++)
        {
            answers.get(i).draw(g2,answers.get(i).getx(), answers.get(i).gety());
        }

        g2.setColor(new Color(255,255,255));
        g2.setFont(new Font("Monospaced", Font.PLAIN, question.getFontSize()));


        g2.drawString(question.getDisplay(), getWidth()/2 - question.getDisplayLength(), 300);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == Begin)
        {
            setUp();
            Begin.stop();
        }
        if(e.getSource() == countdownTimer)
        {
            question.setDisplay(count);
            count--;
            if(count < 0)
            {
                update.start();
                countdownTimer.stop();
                question.setFontSize(100);
                question.setQuestion();
                question.setDisplay(question.getQuestion());
            }
        }
        if(e.getSource() == animation)
        {
            bob.setAngle(bob.getAngle() + 10);
            repaint();
        }
        if(e.getSource() == update)
        {
            Point p = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(p,  this);

            bob.setMidpoint(bob.getx()+bob.getwidth(),bob.gety() + bob.getwidth());
            bob.setmoves(p.x, p.y, bob.getMidx(), bob.getMidy());

            if(bob.getHypoteneuse() > bob.getSingleMovement())
            {
                bob.setx(bob.getx() + bob.getXMove());
                bob.sety(bob.gety() + bob.getYMove());
            }
            else
            {
                bob.setx(bob.getMidx()-bob.getwidth()/2);
                bob.sety(bob.getMidy()-bob.getwidth()/2);
            }

            for(int i = 0; i < villains.size(); i++)
            {

                //prevent overlaps with hypoteneuse
                //make a get/set hypoteneuse abstract method

                villains.get(i).setMidpoint(villains.get(i).getx()+villains.get(i).getwidth(),villains.get(i).gety() + villains.get(i).getwidth());
                villains.get(i).setmoves(bob.getMidx(), bob.getMidy(), villains.get(i).getMidx(), villains.get(i).getMidy());

                if(villains.get(i).getHypoteneuse() > villains.get(i).getSingleMovement())
                {
                    villains.get(i).setx(villains.get(i).getx() + villains.get(i).getXMove());
                    villains.get(i).sety(villains.get(i).gety() + villains.get(i).getYMove());
                }
                else
                {
                    villains.get(i).setx(bob.getx());
                    villains.get(i).sety(bob.gety());
                }
                villains.get(i).setHypoteneuseFromBob(bob.getMidx(), villains.get(i).getMidx(), bob.getMidy(), villains.get(i).getMidy());
                if(villains.get(i).getHypoteneuseFromBob() <= bob.getwidth())
                {
                    update.stop();

                    //gameover screen

                    //int newxdif = (int) (bob.getwidth()/villains.get(i).getHypoteneuse() * villains.get(i).getxdifFromBob());
                    //int newydif = (int) (bob.getwidth()/villains.get(i).getHypoteneuse() * villains.get(i).getydifFromBob());


                    //villains.get(i).setx(villains.get(i).getx() + newxdif);
                    //villains.get(i).setx(villains.get(i).getx() + newxdif);

                    //no overlaps
                    // diameter/hypoteneuse * xdif = newxdif
                    // diameter/hypoteneuse * xdif = newydif
                    // x = x + (newxdif - xdif)
                    // y = y + (newydif - ydif)

                    question.setDisplay("GAME OVER");
                    villains.get(i).setColourCollision();
                }
            }
            repaint();
        }
    }
    public void setUp()
    {
        question.setFontSize(100);
        question.setDisplay("PRESS SPACE BAR TO BEGIN");
        bob.setx(getWidth()/2 - bob.getwidth()/2);
        bob.sety(getHeight()/2 - bob.getwidth()/2);
        villains.get(0).setx(getWidth()/2 - villains.get(0).getwidth()/2);
        villains.get(0).sety(0);
        villains.get(1).setx(getWidth()/2 - villains.get(1).getwidth()/2);
        villains.get(1).sety(getHeight() - villains.get(1).getwidth());
    }
    public void reset()
    {
        for(int i = 0; i < villains.size(); i++)
        {
            villains.get(i).setColourOG();
        }
        countdownTimer.stop();
        update.stop();
        setUp();
    }
    public void countdown(int x)
    {
        count = x;
        question.setFontSize(300);
        question.setDisplay(count);
        count--;
        countdownTimer.start();
    }
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == 32)
        {
            //no reset needed when gameover implemented fully
            reset();
            countdown(3);
        }
        if(e.getKeyCode() == 10)
        {
            reset();
        }
    }
    public void keyReleased(KeyEvent e)
    {

    }
    public void keyTyped(KeyEvent e)
    {

    }
}

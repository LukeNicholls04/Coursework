import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
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
    private int nextlevelCount = 0;
    private int gameoverCount = 0;
    private int level = 1;
    private Random rand = new Random();
    private Timer animation = new Timer(40,this);
    private Timer update = new Timer(40,this);
    private Timer Begin = new Timer(100,this);
    private Timer countdownTimer = new Timer(1000,this);
    private Timer nextLevelTimer = new Timer(200,this);
    private Timer gameOverTimer = new Timer(200,this);
    private Character bob = new Character();
    private Questions question = new Questions();
    private ArrayList <Villain> villains = new ArrayList();
    private ArrayList <Answers> answers = new ArrayList();
    
    public Map()
    {
    	this.setBackground(new Color(253,253,150));
        for(int i = 0; i < 2; i++)
        {
            addVillain();
            villains.get(i).setColourOG();
        }
        for(int i = 0; i < 4; i++)
        {
            addAnswer();
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
        for(int i = 0; i < answers.size(); i++)
        {
            answers.get(i).draw(g2,answers.get(i).getx(), answers.get(i).gety());
            g2.setFont(new Font("Sans Serif", Font.PLAIN, 80));  
            Rectangle r = new Rectangle(answers.get(i).getx(),answers.get(i).gety(),answers.get(i).getWidth(),answers.get(i).getWidth());
            drawCenteredString(g2, "" + answers.get(i).getText(), r, g2.getFont());
        }
        for(int i = 0; i < villains.size(); i++)
        {
            villains.get(i).draw(g2,villains.get(i).getx(),villains.get(i).gety());
        }
        bob.draw(g2,bob.getx(),bob.gety());
        g2.setColor(new Color(45,91,107));
        g2.setFont(new Font("Courier New", Font.PLAIN, question.getFontSize()));
        Rectangle displayRect = new Rectangle(0,0,getWidth(),getHeight()-400);
        drawCenteredString(g2,question.getDisplay(),displayRect,g2.getFont());
        Rectangle scoreRect = new Rectangle(0,0,getWidth()/2,100);
        drawCenteredString(g2, "LEVEL:" + level, scoreRect,new Font("Monospaced", Font.BOLD, 50));    
        Rectangle speedRect = new Rectangle(getWidth()/2,0,getWidth()/2,100);
        drawCenteredString(g2,"VILLAIN SPEED:" + villains.get(0).getSingleMovement() * 10 + "%", speedRect, new Font("Monospaced", Font.BOLD, 50));
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == Begin)
        {
         answers.get(0).setx(50);
            answers.get(0).sety(50);
            answers.get(1).setx(getWidth()-50-answers.get(1).getWidth());
            answers.get(1).sety(50);
            answers.get(2).setx(50);
            answers.get(2).sety(getHeight()-50-answers.get(1).getWidth());
            answers.get(3).setx(getWidth()-50-answers.get(1).getWidth());
            answers.get(3).sety(getHeight()-50-answers.get(1).getWidth());
            setUp();
            Begin.stop();
        }
        if(e.getSource() == gameOverTimer)
        {
        	gameoverCount++;
        	if(gameoverCount % 2 != 0) 
        	{
        		question.setDisplay("");
        	}
        	if(gameoverCount % 2 == 0) 
        	{
        		question.setDisplay("GAME OVER");
        	}
        	if(gameoverCount == 6)
        	{
	        	gameOverTimer.stop();
	            gameoverCount = 0;
	            question.setDisplay("PRESS [ENTER] TO RESTART");
        	}
        }     
        if(e.getSource() == nextLevelTimer) 
        {
        	nextlevelCount++;
        	if(nextlevelCount % 2 != 0) 
        	{
        		question.setDisplay("");
        	}
        	if(nextlevelCount % 2 == 0) 
        	{
        		question.setDisplay("LEVEL " + (level-1) + " COMPLETE");
        	}
        	if(nextlevelCount == 6)
        	{
	        	nextLevelTimer.stop();
	        	reset();
	            countdown(3);
	            nextlevelCount = 0;
        	}        	
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
                int used1 = -1;
                int used2 = -1;
                int used3 = -1;       
                for(int i = 0; i < answers.size(); i++)
                {
                	int num = rand.nextInt(7)+ 1;
                	int posORneg = rand.nextInt(2);             	
                	if(posORneg == 0) 
                	{
                		num = num * -1;
                	}
                	
                	
                	
                	
                	
                	
                	
                	//1 second glitch gets stuck in this while 
                	
                	
                	
                	
                	
                	while(question.getAnswer() + num < 0 || num == used1 || num == used2 || num == used3) 
                	{
                		num = rand.nextInt(7)+ 1;
                    	posORneg = rand.nextInt(2);  
                    	if(posORneg == 0) 
                    	{
                    		num = num * -1;
                    	}
                	}
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	if(i == 0) 
                	{
                		used1 = num;
                	}
                	else if(i == 1)
                	{
                		used2 = num;
                	}
                	else if(i == 2)
                	{
                		used3 = num;
                	}                
                	answers.get(i).setText("" + (question.getAnswer()+num));
                }                        
                int correct = rand.nextInt(4);
                answers.get(correct).setText("" + question.getAnswer());
                answers.get(correct).setCorrect(true);                              
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
            	if(bob.getx() + bob.getXMove() < 0) 
            	{
            		bob.setx(0);
            	}
            	else if(bob.getx() + bob.getXMove() > getWidth() - bob.getwidth()) 
            	{
            		bob.setx(getWidth() - bob.getwidth());
            	}
            	else 
            	{
            		 bob.setx(bob.getx() + bob.getXMove());
            	}
            	
            	if(bob.gety() + bob.getYMove() < 0) 
            	{
            		bob.sety(0);
            	}
            	else if(bob.gety() + bob.getYMove() > getHeight() - bob.getwidth()) 
            	{
            		bob.sety(getHeight() - bob.getwidth());
            	}
            	else 
            	{
            		bob.sety(bob.gety() + bob.getYMove());
            	}
            }
            for(int i = 0; i < villains.size(); i++)
            {
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
                    villains.get(i).setColourCollision();
                    update.stop();
                    gameOver();                   
                }
            }
            for(int i = 0; i < answers.size(); i++) 
            {
            	if(((bob.getMidx()-bob.getwidth()/2) >= answers.get(i).getx()) && (bob.getMidx()+bob.getwidth()/2 <= answers.get(i).getx() + answers.get(i).getWidth()) && (bob.getMidy()-bob.getwidth()/2) >= answers.get(i).gety() && (bob.getMidy()+bob.getwidth()/2) <= answers.get(i).gety() + answers.get(i).getWidth())
            	{
            		if(answers.get(i).getCorrect() == true) 
            		{
            			correctAnswer();
            			answers.get(i).setcolour(new Color(0,255,0));
            		}
            		else 
            		{
            			gameOver();
            			answers.get(i).setcolour(new Color(255,0,0));           		
            		}
            		update.stop();           		
            	}
            }            
            repaint();
        }
    }
    public void correctAnswer() 
    {
    	question.setDisplay("LEVEL " + level + " COMPLETE");
    	level = level + 1;
    	nextlevelCountdown(); 	
    	if(level % 2 == 0) 
    	{
    		question.addNumbers(2);
    	}   	
    	if(level == 2) 
    	{
    		for(int i = 0; i < villains.size(); i++) 
    		{
    			villains.get(i).setSingleMovement(6);
    		}
    	}
    	if(level == 3) 
    	{
    		for(int i = 0; i < villains.size(); i++) 
    		{
    			villains.get(i).setSingleMovement(7);
    		}
    	}
    	if(level == 4) 
    	{
    		addVillain();
    		villains.get(villains.size()-1).setx(0);
        	villains.get(villains.size()-1).sety(getHeight()/2 - villains.get(2).getwidth()/2);
        	villains.get(villains.size()-1).setSingleMovement(7);
    	}
    	if(level == 5) 
    	{
    		for(int i = 0; i < villains.size(); i++) 
    		{
    			villains.get(i).setSingleMovement(8);
    		}
    	}
    	if(level == 6) 
    	{
    		for(int i = 0; i < villains.size(); i++) 
    		{
    			villains.get(i).setSingleMovement(9);
    		}
    	}
    	if(level == 7) 
    	{
    		addVillain();
    		villains.get(villains.size()-1).setx(getWidth() - villains.get(villains.size()-1).getwidth());
        	villains.get(villains.size()-1).sety(getHeight()/2 - villains.get(2).getwidth()/2);
        	villains.get(villains.size()-1).setSingleMovement(9);
    	} 	
    }
    public void gameOver() 
    {
    	level = 1;
    	question.setDisplay("GAME OVER");
    	gameoverCountdown();  	
    	for(int i = 0; i < villains.size() - 1; i++) 
    	{
    		villains.get(i).setSingleMovement(5);
    	}  	
    	if(villains.size() == 4) 
    	{
    		villains.remove(3); 
    	}	
    	if(villains.size() >= 3) 
    	{
    		villains.remove(2);
    	}
    	question.resetNumbers();
    }
    public void setUp()
    {
        question.setFontSize(100);
        question.setDisplay("[SPACE BAR]");
        bob.setx(getWidth()/2 - bob.getwidth()/2);
        bob.sety(getHeight()/2 - bob.getwidth()/2);
        villains.get(0).setx(getWidth()/2 - villains.get(0).getwidth()/2);
        villains.get(0).sety(0);
        villains.get(1).setx(getWidth()/2 - villains.get(1).getwidth()/2);
        villains.get(1).sety(getHeight() - villains.get(1).getwidth());
        if(villains.size() >= 3) 
        {
        	villains.get(2).setx(0);
        	villains.get(2).sety(getHeight()/2 - villains.get(2).getwidth()/2);
        }
        if(villains.size() == 4) 
        {
        	villains.get(3).setx(getWidth() - villains.get(villains.size()-1).getwidth());
        	villains.get(3).sety(getHeight()/2 - villains.get(2).getwidth()/2);
        }
        for(int i = 0; i < answers.size(); i++) 
        {
     	   answers.get(i).setText("");
     	   answers.get(i).setCorrect(false);
        }
    }
    public void reset()
    {
        for(int i = 0; i < villains.size(); i++)
        {
            villains.get(i).setColourOG();
        } 
        for(int i = 0; i < answers.size(); i++) 
        {
        	answers.get(i).setcolour(new Color(255,174,188));
        }      
        countdownTimer.stop();
        update.stop();
        setUp();     
    }  
    public void gameoverCountdown()
    {
    	gameOverTimer.start();
    } 
    public void nextlevelCountdown() 
    {
    	nextLevelTimer.start();
    }  
    public void countdown(int x)
    {
        count = x;
        question.setFontSize(300);
        question.setDisplay(count);
        count--;
        countdownTimer.start();
    }  
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }   
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == 32)
        {
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

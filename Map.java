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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

//creates a Map class which extends JPanel and implements ActionListener and Keylistener
public class Map extends JPanel implements ActionListener, KeyListener
{
    //integer which is displayed to show a countdown
    private int count = 0;

    //integer value which is used to determine what is displayed when the nextLevelTimer is called
    private int nextlevelCount = 0;

    //integer value which is used to determine what is displayed when the gameOverTimer is called
    private int gameoverCount = 0;

    //integer value for the current level in the game which the player is on
    private int level = 1;

    //creates an instance of Random()
    private Random rand = new Random();

    //Timer used to update the drawing of the Character
    private Timer animation = new Timer(40,this);

    //Timer used to update the coordinates and states of all instances of Character, Villain and Answers
    private Timer update = new Timer(40,this);

    //Timer used to set the user interface to its starting position when the program is run
    private Timer Begin = new Timer(500,this);

    //Timer used to signal when to change an integer while displaying a countdown
    private Timer countdownTimer = new Timer(1000,this);

    //Timer used to signal when to change the display while transitioning into the next level
    private Timer nextLevelTimer = new Timer(200,this);

    //Timer used to signal when to change the display after game over occurs
    private Timer gameOverTimer = new Timer(200,this);

    //creates an instance of Character and Questions
    private Character bob = new Character();
    private Questions question = new Questions();

    //creates an ArrayList which stores instances of Villain
    private ArrayList <Villain> villains = new ArrayList();

    //creates an ArrayList which stores instances of Answers
    private ArrayList <Answers> answers = new ArrayList();


    //allows instances of Map to be created
    public Map()
    {
        //sets the background colour for the GUI
        this.setBackground(new Color(253,253,150));

        //adds 2 instances of Villain to the villains ArrayList and sets their colour to the original colour for Villains
        for(int i = 0; i < 2; i++)
        {
            addVillain();
            villains.get(i).setColourOG();
        }

        //adds 4 instances of Answers
        for(int i = 0; i < 4; i++)
        {
            addAnswer();
        }

        //starts the animation Timer and the begin Timer
        animation.start();
        Begin.start();
    }

    //creates a new instance of Answers and adds it to the answers ArrayList
    private void addAnswer()
    {
        answers.add(new Answers());
    }

    //creates a new instance of Villain and adds it to the villains ArrayLIst
    private void addVillain()
    {
        villains.add(new Villain());
    }

    //method which is used to draw on all aspects of the GUI
    public void paintComponent(Graphics g)
    {
        //sets up Graphics2D to be used for drawing
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //makes lines appear smoother when drawn
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //for loop to iterate through all instances of Answers in the answers ArrayList
        for(int i = 0; i < answers.size(); i++)
        {
            //calls the Answers draw function and draws an Answer
            answers.get(i).draw(g2,answers.get(i).getx(), answers.get(i).gety());

            //sets the font and draws the Answers text centered inside the Answer box
            g2.setFont(new Font("Sans Serif", Font.PLAIN, 40));
            Rectangle r = new Rectangle(answers.get(i).getx(),answers.get(i).gety(),answers.get(i).getWidth(),answers.get(i).getWidth());
            drawCenteredString(g2, "" + answers.get(i).getText(), r, g2.getFont());
        }

        //for loop to iterate through all instances of Villain in the villains ArrayList
        for(int i = 0; i < villains.size(); i++)
        {

            //calls the Villains draw function and draws a Villain
            villains.get(i).draw(g2,villains.get(i).getx(),villains.get(i).gety());
        }

        //calls the Characters draw function and draws the Character
        bob.draw(g2,bob.getx(),bob.gety());

        //sets a new colour and font
        g2.setColor(new Color(45,91,107));
        g2.setFont(new Font("Courier New", Font.PLAIN, question.getFontSize()));

        //draws the Questions display text centered near the top of the GUI
        Rectangle displayRect = new Rectangle(0,0,getWidth(),getHeight()-200);
        drawCenteredString(g2,question.getDisplay(),displayRect,g2.getFont());

        //draws the level number text centered near the top left of the GUI
        Rectangle scoreRect = new Rectangle(0,0,getWidth()/2,50);
        drawCenteredString(g2, "LEVEL:" + level, scoreRect,new Font("Monospaced", Font.BOLD, 25));

        //draws the Villains speed text centered near the top right of the GUI
        Rectangle speedRect = new Rectangle(getWidth()/2,0,getWidth()/2,50);
        drawCenteredString(g2,"VILLAIN SPEED:" + villains.get(0).getSingleMovement() * 10 + "%", speedRect, new Font("Monospaced", Font.BOLD, 25));
    }

    //method which detects ActionEvents such as Timers being called
    public void actionPerformed(ActionEvent e)
    {

        //if the source of the ActionEvent is from the Begin Timer
        if(e.getSource() == Begin)
        {
            //sets the x and y coordinates for all 4 Answers
            answers.get(0).setx(50);
            answers.get(0).sety(50);
            answers.get(1).setx(getWidth()-50-answers.get(1).getWidth());
            answers.get(1).sety(50);
            answers.get(2).setx(50);
            answers.get(2).sety(getHeight()-50-answers.get(1).getWidth());
            answers.get(3).setx(getWidth()-50-answers.get(1).getWidth());
            answers.get(3).sety(getHeight()-50-answers.get(1).getWidth());

            //calls the setup method to arrange the GUI into its starting position
            setUp();

            //stops the Begin Timer
            Begin.stop();
        }

        //if the source of the ActionEvent is from the gameOver Timer
        if(e.getSource() == gameOverTimer)
        {

            //implements gameoverCount by 1
            gameoverCount++;

            //if the gamoverCount is odd
            if(gameoverCount % 2 != 0)
            {
                //set the Questions display text to be blank
                question.setDisplay("");
            }

            //if the gamoverCount is even
            if(gameoverCount % 2 == 0)
            {
                //sets the Question display text to say "GAME OVER"
                question.setDisplay("GAME OVER");
            }

            //if the gameoverCount is equal to 6
            if(gameoverCount == 6)
            {
                //stops the gameOver Timer, resets the gameoverCount to 0, and changes the Questions display text
                gameOverTimer.stop();
                gameoverCount = 0;
                question.setDisplay("PRESS [ENTER] TO RESTART");
            }
        }

        //if the source of the ActionEvent is from the nextLevel Timer
        if(e.getSource() == nextLevelTimer)
        {
            //implements the nextlevelCount by 1
            nextlevelCount++;

            //if the nextlevel count is odd
            if(nextlevelCount % 2 != 0)
            {

                //sets the Questions display text to be blank
                question.setDisplay("");
            }

            //if the nextlevel count is even
            if(nextlevelCount % 2 == 0)
            {

                //sets the Questions display text to say which level the player have completed
                question.setDisplay("LEVEL " + (level-1) + " COMPLETE");
            }

            //if the nextlevel count is equal to 6
            if(nextlevelCount == 6)
            {

                //stops the nextLevel Timer and calls the reset method to set the GUI up for the next level
                nextLevelTimer.stop();
                reset();

                //starts a 3 seconds countdown for the next level using the countdown method
                countdown(3);

                //resets the nextlevel count to equal 0
                nextlevelCount = 0;
            }
        }

        //if the source of the ActionEvent is from the countdown Timer
        if(e.getSource() == countdownTimer)
        {
            //sets the Questions display text equal to the count
            question.setDisplay(count);

            //decrease the count by 1
            count--;

            //if the count is smaller than 0
            if(count < 0)
            {
                //starts the update Timer
                update.start();

                //stops the countdown Timer
                countdownTimer.stop();

                //sets the font size for the Questions display text equal to 50
                question.setFontSize(50);

                //sets the maths question in Question equal to a new random maths question
                question.setQuestion();

                //sets the Question display text equal to the current maths question
                question.setDisplay(question.getQuestion());

                //3 integer values used to represent the incorrect answers given to the first 3 answer boxes
                int used1 = -1;
                int used2 = -1;
                int used3 = -1;

                //for loop to iterate through all the instances of Answers is the answers ArrayList
                for(int i = 0; i < answers.size(); i++)
                {
                    //generates a random integer between 1-7
                    int num = rand.nextInt(7)+ 1;

                    //generates a random integer between 0-1
                    int posORneg = rand.nextInt(2);

                    //if posORneg is equal to 0-1 it makes num negative, meaning num is a random number between -7-7 excluding 0
                    if(posORneg == 0)
                    {
                        num = num * -1;
                    }

                    //repeats steps until the all 4 incorrect answers are positive and unique
                    while(question.getAnswer() + num < 0 || num == used1 || num == used2 || num == used3)
                    {
                        //repeats the original steps from above for generating a random number between -7-7 excluding 0
                        num = rand.nextInt(7)+ 1;
                        posORneg = rand.nextInt(2);
                        if(posORneg == 0)
                        {
                            num = num * -1;
                        }
                    }

                    //if the instance of Answer at position i is the first one in the Answers ArrayList
                    if(i == 0)
                    {
                        used1 = num;
                    }

                    //if the instance of Answer at position i is the second one in the Answers ArrayList
                    else if(i == 1)
                    {
                        used2 = num;
                    }

                    //if the Answer at position i is the third one in the Answers ArrayList
                    else if(i == 2)
                    {
                        used3 = num;
                    }

                    //sets the Answers text equal to the correct answer + num
                    answers.get(i).setText("" + (question.getAnswer()+num));
                }

                //sets a random instance of Answer to be correct
                int correct = rand.nextInt(4);

                //sets the text of the correct instance of Answer equal to the correct answer to the maths question
                answers.get(correct).setText("" + question.getAnswer());

                //set the boolean value for correct to be true for the correct instance of Answer
                answers.get(correct).setCorrect(true);
            }
        }

        //if the source of the ActionEvent is from the animation Timer
        if(e.getSource() == animation)
        {
            //adds 10 degrees to the starting angle which the Character is drawn from then calls the paintComponent function
            bob.setAngle(bob.getAngle() + 10);
            repaint();
        }

        //if the source of the ActionEvent is from the update Timer
        if(e.getSource() == update)
        {
            //imports and store information about the mouse
            Point p = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(p,  this);

            //sets the midpoints of the character using its current x and y coordinates which it's drawn from
            bob.setMidpoint(bob.getx()+bob.getwidth(),bob.gety() + bob.getwidth());

            //sets the next moves in terms of x and y which the Character will move towards the mouse pointer with
            bob.setmoves(p.x, p.y, bob.getMidx(), bob.getMidy());

            //if the hypotenuse between the Character and the mouse pointer is longer than the maximum distance the Character can move in one update
            if(bob.getHypoteneuse() > bob.getSingleMovement())
            {

                //prevents the x coordinate of the Character from being out of the boundaries of the left edge of the GUI
                if(bob.getx() + bob.getXMove() < 0)
                {
                    bob.setx(0);
                }

                //prevents the x coordinate of the Character from being out of the boundaries of the right edge of the GUI
                else if(bob.getx() + bob.getXMove() > getWidth() - bob.getwidth())
                {
                    bob.setx(getWidth() - bob.getwidth());
                }

                //sets the x coordinate of the Character equal to its current x coordinate + xmove
                else
                {
                    bob.setx(bob.getx() + bob.getXMove());
                }

                //prevents the y coordinate of the Character from being out of the boundaries of the top edge of the GUI
                if(bob.gety() + bob.getYMove() < 0)
                {
                    bob.sety(0);
                }

                //prevents the y coordinate of the Character from being out of the boundaries of the bottom edge of the GUI
                else if(bob.gety() + bob.getYMove() > getHeight() - bob.getwidth())
                {
                    bob.sety(getHeight() - bob.getwidth());
                }

                //sets the y coordinate of the Character equal to its current y coordinate + ymove
                else
                {
                    bob.sety(bob.gety() + bob.getYMove());
                }
            }

            //iterates through all instances of Villain in the villains ArrayList
            for(int i = 0; i < villains.size(); i++)
            {
                //sets the midpoints of a Villains using its x and y coordinates which it's drawn from
                villains.get(i).setMidpoint(villains.get(i).getx()+villains.get(i).getwidth(),villains.get(i).gety() + villains.get(i).getwidth());

                //sets the next moves in terms of x and y which the Villain will move towards the Character with
                villains.get(i).setmoves(bob.getMidx(), bob.getMidy(), villains.get(i).getMidx(), villains.get(i).getMidy());

                //if the hypotenuse between a Villain and the Character is bigger than the maximum distance the Villain can move in one update
                if(villains.get(i).getHypoteneuse() > villains.get(i).getSingleMovement())
                {
                    //sets the Villains x coordinate equal to its current x coordinate + its next movement in x
                    villains.get(i).setx(villains.get(i).getx() + villains.get(i).getXMove());

                    //sets the Villains y coordinate equal to its current y coordinate + its next movement in y
                    villains.get(i).sety(villains.get(i).gety() + villains.get(i).getYMove());
                }

                //if the hypotenuse between a Villain and the Character is not bigger than the maximum distance the Villain can move in one update
                else
                {
                    //sets the Villains coordinates equal to the Characters coordinates
                    villains.get(i).setx(bob.getx());
                    villains.get(i).sety(bob.gety());
                }

                //calls a method to calculate the length of the hypotenuse between a Villain and the Character
                villains.get(i).setHypoteneuseFromBob(bob.getMidx(), villains.get(i).getMidx(), bob.getMidy(), villains.get(i).getMidy());

                //if the hypotenuse between a Villain and the Character are touching
                if(villains.get(i).getHypoteneuseFromBob() <= bob.getwidth())
                {
                    //change the colour of the Villain to its collision colour
                    villains.get(i).setColourCollision();

                    //stop the update Timer
                    update.stop();

                    //calls the gameOver method
                    gameOver();
                }
            }

            //iterates through all instances of Answer in the answers ArrayList
            for(int i = 0; i < answers.size(); i++)
            {
                //if the Character is fully inside an Answer box
                if(((bob.getMidx()-bob.getwidth()/2) >= answers.get(i).getx()) && (bob.getMidx()+bob.getwidth()/2 <= answers.get(i).getx() + answers.get(i).getWidth()) && (bob.getMidy()-bob.getwidth()/2) >= answers.get(i).gety() && (bob.getMidy()+bob.getwidth()/2) <= answers.get(i).gety() + answers.get(i).getWidth())
                {
                    //if the Answer which has been entered is correct
                    if(answers.get(i).getCorrect() == true)
                    {
                        //calls the correctAnswer method and changes the Answer's colour to green
                        correctAnswer();
                        answers.get(i).setcolour(new Color(0,255,0));
                    }

                    //if the Answer which has been entered is incorrect
                    else
                    {
                        //calls the gameOver method and changes the Answer's colour to red
                        gameOver();
                        answers.get(i).setcolour(new Color(255,0,0));
                    }

                    //stops the update Timer
                    update.stop();
                }
            }

            //calls the paintComponent method
            repaint();
        }
    }

    //method which is carried out after the player selects the correct Answer
    public void correctAnswer()
    {
        //sets the Question display text to show which level has been completed
        question.setDisplay("LEVEL " + level + " COMPLETE");

        //increases the level number by 1
        level = level + 1;

        //calls the nextlevelCountdown method to transition into the next level
        nextlevelCountdown();

        //if the level number is even, the next 2 consecutive numbers are added to the numbers list which can be used in the maths questions
        if(level % 2 == 0)
        {
            question.addNumbers(2);
        }

        //if the level number is equal to 2, the maximum single movement for the Villains is set to 6
        if(level == 2)
        {
            for(int i = 0; i < villains.size(); i++)
            {
                villains.get(i).setSingleMovement(6);
            }
        }

        //if the level number is equal to 3, the maximum single movement for the Villains is set to 7
        if(level == 3)
        {
            for(int i = 0; i < villains.size(); i++)
            {
                villains.get(i).setSingleMovement(7);
            }
        }

        //if the level number is equal to 4, a new Villain is added on the left hand side of the screen
        if(level == 4)
        {
            addVillain();
            villains.get(villains.size()-1).setx(0);
            villains.get(villains.size()-1).sety(getHeight()/2 - villains.get(2).getwidth()/2);

            //the maximum single movement for the new Villain is set to 7
            villains.get(villains.size()-1).setSingleMovement(7);
        }

        //if the level number is equal to 5, the maximum single movement for the Villains is set to 8
        if(level == 5)
        {
            for(int i = 0; i < villains.size(); i++)
            {
                villains.get(i).setSingleMovement(8);
            }
        }

        //if the level number is equal to 6, the maximum single movement for the Villains is set to 9
        if(level == 6)
        {
            for(int i = 0; i < villains.size(); i++)
            {
                villains.get(i).setSingleMovement(9);
            }
        }

        //if the level number is equal to 7, a new Villain is added on the right hand side of the screen
        if(level == 7)
        {
            addVillain();
            villains.get(villains.size()-1).setx(getWidth() - villains.get(villains.size()-1).getwidth());
            villains.get(villains.size()-1).sety(getHeight()/2 - villains.get(2).getwidth()/2);

            //the maximum single movement for the new Villain is set to 9
            villains.get(villains.size()-1).setSingleMovement(9);
        }
    }

    //method which is called when the game is over
    public void gameOver()
    {
        //the level number is reset to 1
        level = 1;

        //the question display text is set to show GAME OVER
        question.setDisplay("GAME OVER");

        //a method for transitioning through a gamer over is called
        gameoverCountdown();

        //iterates through all instances of Villain in the villains ArrayList
        for(int i = 0; i < villains.size() - 1; i++)
        {
            //sets the maximum single movement per update for a villain equal to 5
            villains.get(i).setSingleMovement(5);
        }

        //if there are 4 Villains in the villains ArrayList, remove the 4th one
        if(villains.size() == 4)
        {
            villains.remove(3);
        }

        //if there are 3 Villains in the villains ArrayList, remove the 3rd one
        if(villains.size() >= 3)
        {
            villains.remove(2);
        }

        //reset the numbers List equal to the numbers 1-10
        question.resetNumbers();
    }

    //method used the setup the GUI into it's beginning format before starting a level
    public void setUp()
    {
        //sets the font size for the Questions display text to 50
        question.setFontSize(50);

        //sets the Questions display text to its beginning format
        question.setDisplay("[SPACE BAR]");

        //sets the Characters coordinates to its starting coordinates
        bob.setx(getWidth()/2 - bob.getwidth()/2);
        bob.sety(getHeight()/2 - bob.getwidth()/2);

        //sets the coordinates for the first two Villains in the villains ArrayList to their starting coordinates
        villains.get(0).setx(getWidth()/2 - villains.get(0).getwidth()/2);
        villains.get(0).sety(0);
        villains.get(1).setx(getWidth()/2 - villains.get(1).getwidth()/2);
        villains.get(1).sety(getHeight() - villains.get(1).getwidth());

        //if there are more than 3 Villains in the villains ArrayList
        if(villains.size() >= 3)
        {
            //set the coordinates for the third Villain in the villains ArrayList to its starting coordinates
            villains.get(2).setx(0);
            villains.get(2).sety(getHeight()/2 - villains.get(2).getwidth()/2);
        }

        //if there are more than 4 Villains in the villains ArrayList
        if(villains.size() == 4)
        {
            //set the coordinates for the fourth Villain in the villains ArrayList to its starting coordinates
            villains.get(3).setx(getWidth() - villains.get(villains.size()-1).getwidth());
            villains.get(3).sety(getHeight()/2 - villains.get(2).getwidth()/2);
        }

        //iterates through all the instances of Answer in the answers ArrayList
        for(int i = 0; i < answers.size(); i++)
        {
            //sets the text in each Answer to be blank
            answers.get(i).setText("");

            //sets the boolean for if an Answer is correct to false
            answers.get(i).setCorrect(false);
        }
    }

    //resets the GUI to its original format
    public void reset()
    {
        //sets the colour of each instance of Villain in the villains ArrayList their original colour
        for(int i = 0; i < villains.size(); i++)
        {
            villains.get(i).setColourOG();
        }

        //sets the colour of each instance of Answer in the answers ArrayList their original colour
        for(int i = 0; i < answers.size(); i++)
        {
            answers.get(i).setcolour(new Color(255,174,188));
        }

        //stops the countdown Timer and the update Timer
        countdownTimer.stop();
        update.stop();

        //call the setup method
        setUp();
    }

    //method which starts the gameOver Timer
    public void gameoverCountdown()
    {

        gameOverTimer.start();
    }

    //method which starts the nextLevel Timer
    public void nextlevelCountdown()
    {
        nextLevelTimer.start();
    }

    //method which displays a countdown from the integer value passed through to 0
    public void countdown(int x)
    {
        //sets the count equal to the integer value passed through
        count = x;

        //sets the font size for the Questions display text to 150
        question.setFontSize(150);

        //sets the Questions display text equal to count
        question.setDisplay(count);

        //decreases count by 1 and starts the countdown Timer
        count--;
        countdownTimer.start();
    }

    //method which allows you to draw strings centred inside an area
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        //imports FontMetrics and stores it as a variable
        FontMetrics metrics = g.getFontMetrics(font);

        //calculates the starting x and y coordinates for the string to be drawn
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        //sets the current font equal to the font passed through
        g.setFont(font);

        //draws the string using the new coordinates
        g.drawString(text, x, y);
    }

    //method which detects when a key is pressed
    public void keyPressed(KeyEvent e)
    {
        //if the space bar was pressed the reset method is called and the countdown method is called and passed the integer of 3
        if(e.getKeyCode() == 32)
        {
            reset();
            countdown(3);
        }

        //if the enter key was pressed the reset method is called 
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
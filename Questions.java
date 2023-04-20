import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Questions
{
    //integer values for the first and second numbers in the maths questions
    private int num1;
    private int num2;
    //integer value for the font size of the writing in the display
    private int fontSize = 50;
    //integer value for the correct answer to the current maths question
    private int answer;
    //String value for the mathematical symbol used in the current maths question
    private String sign;
    //creates an instance of Random()
    private Random rand = new Random();
    //String value for the contents of the writing in the display
    private String display = "";
    //String value for the current maths question
    private String question = " ";
    //List of all the numbers which can be used in the maths questions, it is initialised with the numbers 1-10
    private List <Integer> numbers = new ArrayList <Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    //List of all the mathematical signs which can be used in the maths questions
    private List <String> signs = new ArrayList <String> (Arrays.asList("+","-","x","÷"));
    //allows instances of Question to be created
    public Questions()
    {

    }

    //sets display equal to a String value passed through
    /**
     *
     * @param x
     */
    public void setDisplay(String x)
    {
        display = x;
    }
    //sets display equal to an integer value passed through, as a String
    /**
     *
     * @param x
     */
    public void setDisplay(int x)
    {
        display = "" + x;
    }

    //returns display
    public String getDisplay()
    {
        return display;
    }

    //returns question
    public String getQuestion()
    {
        return question;
    }

    //method which generates a maths question
    public void setQuestion()
    {
        //generates a random sign from the signs List
        sign = signs.get(rand.nextInt(signs.size()));
        //generates 2 random numbers from the numbers List
        num1 = numbers.get(rand.nextInt(numbers.size()));
        num2 = numbers.get(rand.nextInt(numbers.size()));
        //if the sign is a minus, it continues generating num1 and num2 until num1 is bigger than or equal to num2
        if(sign == "-")
        {
            while(num1 < num2)
            {
                num1 = numbers.get(rand.nextInt(numbers.size()));
                num2 = numbers.get(rand.nextInt(numbers.size()));
            }
        }
        //if the sign is a division, it continues generating num1 and num2 until the remainder of num1/num2 is equal to 0 and num2 is not equal to 0
        if(sign == "÷")
        {
            while(num2 == 0 || num1 % num2 != 0)
            {
                num1 = numbers.get(rand.nextInt(numbers.size()));
                num2 = numbers.get(rand.nextInt(numbers.size()));
            }
        }
        //assembles the random question using both numbers and the sign
        question = (num1 + " " + sign + " " + num2);
    }

    //sets fontSize equal to an integer value passed through
    public void setFontSize(int x)
    {
        fontSize = x;
    }

    //returns fontSize
    public int getFontSize()
    {
        return fontSize;
    }

    //resets the contents of the numbers List back to the numbers 1-10
    public void resetNumbers()
    {
        numbers = new ArrayList <Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    }

    //adds a chosen number of consecutive numbers to the numbers List
    /**
     *
     * @param x
     */
    public void addNumbers(int x)
    {
        for(int i = 0; i < x; i++)
        {
            numbers.add(numbers.get(numbers.size()-1)+1);
        }
    }

    //solves the current question to work out the answer and then returns it
    /**
     *
     * @return
     */
    public int getAnswer()
    {
        if(sign == "+")
        {
            answer = num1 + num2;
        }
        if(sign == "-")
        {
            answer = num1 - num2;
        }
        if(sign == "x")
        {
            answer = num1 * num2;
        }
        if(sign == "÷")
        {
            answer = num1 / num2;
        }
        return answer;
    }
}
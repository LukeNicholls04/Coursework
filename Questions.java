import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JLabel;

public class Questions extends JLabel
{
    private int num1;
    private int num2;
    private int length;
    private int fontSize = 100;
    private int answer;
    private String sign;
    private Random rand = new Random();
    private String display = "";
    private String question = " ";
    private List <Integer> numbers = new ArrayList <Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12));
    private List <String> signs = new ArrayList <String> (Arrays.asList("+","-","x","÷"));

    public Questions()
    {

    }

    public void setDisplay(String x)
    {
        display = x;
        length = display.length();
    }

    public void setDisplay(int x)
    {
        display = "" + x;
    }

    public String getDisplay()
    {
        return display;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion()
    {
        sign = signs.get(rand.nextInt(signs.size()));

        num1 = numbers.get(rand.nextInt(numbers.size()));
        num2 = numbers.get(rand.nextInt(numbers.size()));

        if(sign == "-")
        {
            while(num1 < num2)
            {
                num1 = numbers.get(rand.nextInt(numbers.size()));
                num2 = numbers.get(rand.nextInt(numbers.size()));
            }
        }

        if(sign == "÷")
        {
            while(num2 == 0 || num1 % num2 != 0)
            {
                num1 = numbers.get(rand.nextInt(numbers.size()));
                num2 = numbers.get(rand.nextInt(numbers.size()));
            }
        }

        question = (num1 + " " + sign + " " + num2);
    }

    public void setFontSize(int x)
    {
        fontSize = x;
    }

    public int getFontSize()
    {
        return fontSize;
    }

    public int getDisplayLength()
    {
        return length;
    }

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

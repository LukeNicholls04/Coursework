import javax.swing.JFrame;
public class Runner
{
    public static void main(String[] args)
    {
        //creates a new instance of the Map() class
        Map map = new Map();

        //creates a JFrame() and sets its properties
        JFrame window = new JFrame();
        window.setSize(900,500);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.addKeyListener(map);

        //sets the contents of the JFrame() to be the instance of the Map() class
        window.setContentPane(map);
        window.setVisible(true);
    }
}

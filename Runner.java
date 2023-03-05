import javax.swing.JFrame;

public class Runner
{
    public static void main(String[] args)
    {
        Map map = new Map();
        JFrame window = new JFrame();
        window.setSize(900,500);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.addKeyListener(map);
        window.setContentPane(map);
        window.setVisible(true);
    }
}

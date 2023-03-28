import javax.swing.JFrame;

public class runner 
{
	public static void main(String[] args) 
	{
		Map map = new Map();
		JFrame window = new JFrame();
		window.setSize(1800,1000);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addKeyListener(map);
		window.setContentPane(map);
		window.setVisible(true);
	}
}

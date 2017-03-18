package panels;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
	public MyFrame()
	{
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("TestGridBagLayout");
		JPanel panel = new GridBagLayoutPanel();
		this.setContentPane(panel);
		this.setBounds(0, 0, 200, 150);
		this.setVisible(true);
	}
}

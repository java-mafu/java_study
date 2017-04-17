package interpret;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ChoiceArray extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Interpret frame;
	JLabel label;
	int arraySum;

	private JButton[] buttons;

	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoiceArray frame = new ChoiceArray();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public ChoiceArray() {
		setLayout();
	}

	private void setLayout() {
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		label = new JLabel();

		buttons = new JButton[arraySum];
		for (int i = 0; i < arraySum; i++) {
			buttons[i] = new JButton("array["+i+"]");
			buttons[i].setBounds(100, 20 * (i * 2 + 1), 200, 25);
			contentPanel.add(buttons[i]);
		}
		label.setBounds(100, 20 * (arraySum * 2 + 1), 200, 25);
		label.setText("操作する配列を選択してください");
	}
	
	
	

}

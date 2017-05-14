package interpret;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ArrayDialog extends JDialog {

	private int arraySum;
	private String arrayName;
	private JButton buttons[];
	private List<JDialog> arrayDialogs;
	private JPanel panel;
	private JScrollPane scrollPane;

	public ArrayDialog(int arraySum, String name) {
		setTitle("Choose Array Dialog");
		this.arraySum = arraySum;
		this.arrayName = name;
		buttons = new JButton[arraySum];
		arrayDialogs = new ArrayList<JDialog>();
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		initLayout();
	}

	public Object[] getArrayObject() {
		if (arraySum <= 0)
			return null;
		Object[] objs = new Object[arraySum];
		for (int i = 0; i < arraySum; i++) {
			JDialog dialog = arrayDialogs.get(i);
			objs[i] = ((EditObjectDialog) dialog).getObject();
		}
		return objs;
	}

	public Object getObject(int idx) {
		return ((EditObjectDialog) arrayDialogs.get(idx)).getObject();
	}


	private void initLayout() {
		setBounds(150, 150, 300, 100);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new ArrayDialogListener());
		for (int i = 0; i < arraySum; i++) {
			buttons[i] = new JButton(arrayName + "[" + i + "]");
			final int idx = i;
			buttons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					refleshDialog();
					arrayDialogs.get(idx).setVisible(true);
				}
			});
			panel.add(buttons[i]);
		}

		scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(0, 0, getWidth(), getHeight());
		add(scrollPane);
	}

	public void addButton(JDialog arrayDialog) {
		arrayDialogs.add(arrayDialog);
	}

	private void refleshDialog() {
		for (JDialog dialog : arrayDialogs) {
			dialog.setVisible(false);
		}
	}

	private class ArrayDialogListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			for (JDialog dialog : arrayDialogs) {
				dialog.setVisible(false);
				dialog = null;
			}

		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO 自動生成されたメソッド・スタブ

		}
	}

}

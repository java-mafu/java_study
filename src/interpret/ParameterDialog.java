package interpret;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ParameterDialog extends JDialog {
	Class<?>[] types;
	JTextField[] parameterTextField;
	Object[] parameter;


	public Object[] getParameters() {
		return parameter;
	}

	public ParameterDialog(Class<?>[] types) {
		this.types = types;
		parameterTextField = new JTextField[types.length];
		parameter = new Object[types.length];
		setLayout();
	}

	private void setLayout() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		setLayout(null);
		setTitle("Edit Dialog");

		for (int idx = 0; idx < types.length; idx++) {
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			String typeStr = types[idx].getTypeName();
			JLabel typeName = new JLabel(typeStr.substring(typeStr.lastIndexOf(".") + 1));
			parameterTextField[idx] = new JTextField();
			parameterTextField[idx].setColumns(20);
			panel.add(typeName);
			panel.add(parameterTextField[idx]);
			panel.setBounds(0, idx * 30, getWidth() - 15, 30);
			add(panel);
		}

		{
			setLayout(new BorderLayout());
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			ButtonListener bl = new ButtonListener();
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(bl);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(bl);
			}
		}
	}

	// Button Listener
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String btnstr = e.getActionCommand();
			switch (btnstr) {
			case "OK":
				for (int i = 0; i < types.length; i++) {
					parameter[i] = MyCastClass.castStringToAny(types[i], parameterTextField[i].getText());
				}
				dispose();
				break;
			case "Cancel":
				dispose();
				break;
			default:
				break;
			}
		}
	}
}

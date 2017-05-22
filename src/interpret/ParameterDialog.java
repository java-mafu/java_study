package interpret;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ParameterDialog extends JDialog {
	Class<?>[] types;
	JTextField[] parameterTextField;
	JScrollPane scrollPane;
	Object[] parameter;

	public Object[] getParameters() {
		return parameter;
	}

	public ParameterDialog(Class<?>[] types) {
		this.types = types;
		parameterTextField = new JTextField[types.length];
		scrollPane = new JScrollPane();
		parameter = new Object[types.length];
		setLayout();
	}

	private void setLayout() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		setLayout(null);
		setTitle("Edit Dialog");
		scrollPane.setLayout(null);
		scrollPane.setBounds(0, 0, getWidth() - 15, getHeight() - 100);

		for (int idx = 0; idx < types.length; idx++) {
			JPanel panel = new JPanel();
			panel.setLayout(null);
			String typeStr = types[idx].getTypeName();
			JLabel typeName = new JLabel(typeStr.substring(typeStr.lastIndexOf(".") + 1));
			typeName.setBounds(0, 0, 150, 20);
			parameterTextField[idx] = new JTextField();
			parameterTextField[idx].setBounds(180, 0, 100, 20);
			panel.add(typeName);
			panel.add(parameterTextField[idx]);
			panel.setBounds(0, idx * 30, getWidth() - 15, 30);
			scrollPane.add(panel);
		}
		add(scrollPane);

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
					if ("null" == parameterTextField[i].getText()) {
						parameter[i] = null;
						continue;
					}
					parameter[i] = MyCastClass.castStringToAny(types[i], parameterTextField[i].getText());
					if (parameter[i] == null) {
						JDialog dialog = ClassSearchFrame.instances.get(parameterTextField[i].getText());
						if (types[i].isArray()) {
							if (dialog instanceof EditObjectDialog) {
								JFrame exceptionFrame = new JFrame("ExceptionIn");
								JOptionPane.showMessageDialog(exceptionFrame, "引数がおかしいです");
								dispose();
							}
							parameter[i] = ((ArrayDialog) dialog).getArrayObject();
						} else {
							if (dialog instanceof EditObjectDialog) {
								parameter[i] = ((EditObjectDialog) dialog).getObject();
							} else {
								String str = parameterTextField[i].getText();
								int stridx = str.indexOf("[");
								if(stridx == -1){
									JFrame exceptionFrame = new JFrame("Exception");
									JOptionPane.showMessageDialog(exceptionFrame, "引数がおかしいです");
									dispose();
									break;
								}
								String name = str.substring(0, stridx);
								String idx = str.substring(str.indexOf("[") + 1, str.indexOf("]"));
								dialog = ClassSearchFrame.instances.get(name);
								try {
									parameter[i] = ((ArrayDialog) dialog).getObject(Integer.parseInt(idx));
								} catch (NumberFormatException nfe) {
									JFrame exceptionFrame = new JFrame("Exception");
									JOptionPane.showMessageDialog(exceptionFrame, "引数がおかしいです");
								}
							}
						}
						if (parameter[i] == null) {
							JFrame exceptionFrame = new JFrame("ExceptionIn");
							JOptionPane.showMessageDialog(exceptionFrame, "引数がおかしいです");
						}
					}
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

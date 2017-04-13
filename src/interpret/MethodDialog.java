package interpret;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MethodDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Object object;
	private Method method;
	JLabel label;
	java.lang.reflect.Type[] types;

	public Object[] getParameterValues() {
		return parameterValues;
	}

	private JLabel[] parameterslabel;
	private JTextField[] parametersTexts;
	private Object[] parameterValues;
	private Object returnValue;

	private final Action action = new SwingAction();

	public Object getReturnValue() {
		return returnValue;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MethodDialog dialog = new MethodDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MethodDialog(Object object, Method method) {
		this.object = object;
		this.method = method;
		setLayout();
	}

	public MethodDialog() {
		setLayout();
	}

	private void setLayout() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		types = method.getGenericParameterTypes();
		if (types.length == 0)
			dispose();

		parameterslabel = new JLabel[types.length];
		parametersTexts = new JTextField[types.length];
		parameterValues = new Object[types.length];
		for (int i = 0; i < types.length; i++) {
			JLabel plabel = new JLabel(CreateObject.erasePackageName(types[i].getTypeName()));
			plabel.setBounds(40, 20 * (i * 2 + 1), 80, 25);
			contentPanel.add(plabel);
			parameterslabel[i] = plabel;

			JTextField ptext = new JTextField();
			ptext.setBounds(180, 20 * (i * 2 + 1), 200, 25);
			contentPanel.add(ptext);
			parametersTexts[i] = ptext;
		}

		label = new JLabel("");
		label.setBounds(180, 20 * (types.length * 2 + 1), 80, 25);
		contentPanel.add(label);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(action);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(action);
			}
		}
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			if (cmd.equals("OK")) {
				try {
					for (int i = 0; i < parameterValues.length; i++) {
						parameterValues[i] = MyCastClass.castStringToAny((Class<?>) types[i],
								parametersTexts[i].getText());
					}
					returnValue = method.invoke(object, parameterValues);
					dispose();
				} catch (Exception e0) {
					label.setText(e0.toString());

				}
			} else if (cmd.equals("Cancel")) {
				for (int i = 0; i < parameterValues.length; i++) {
					parametersTexts[i].setText("");
				}
			}
		}
	}
}

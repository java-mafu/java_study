package interpret;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MethodDialog extends JDialog {

	JFrame frame;
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
		label = new JLabel();
		if (types.length == 0) {
			try {
				returnValue = method.invoke(object, null);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

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
		label.setBounds(180, 20 * (types.length * 2 + 1), 200, 25);
		label.setText("プリミティブ型は値．それ以外はクラス名を入力してください");
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
		frame = new JFrame("Exception");
		frame.setBounds(100, 100, 100, 200);
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
						if (isPrimitive(parameterslabel[i].getText())) {
							parameterValues[i] = MyCastClass.castStringToAny((Class<?>) types[i],
									parametersTexts[i].getText());
						} else{
							parameterValues[i] = Class.forName(parametersTexts[i].getText()).newInstance();
						}
					}
					returnValue = method.invoke(object, parameterValues);
					dispose();
				} catch (Exception e0) {
					JOptionPane.showMessageDialog(frame, e0.toString());
					return;

				}
			} else if (cmd.equals("Cancel")) {
				for (int i = 0; i < parameterValues.length; i++) {
						parametersTexts[i].setText("");

				}
			}
		}
	}

	private boolean isPrimitive(String className) {
		switch (className) {
		case "int":
		case "long":
		case "short":
		case "byte":
		case "boolean":
		case "char":
		case "float":
		case "double":
		case "String":
			return true;
		default:
			return false;
		}
	}

}

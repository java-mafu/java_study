package interpret;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FieldDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Field field;
	private Object obj;
	private JTextField textField;
	JLabel label;
	JLabel lblType;
	JLabel lblValue;

	private Object value;

	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Object o = new JFrame();
		try {
			FieldDialog dialog = new FieldDialog(o, o.getClass().getField("WIDTH"));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FieldDialog(Object obj, Field field) {
		this();
		field.setAccessible(true);
		this.field = field;
		this.obj = obj;
		String mod = "";
		if (Modifier.isStatic(field.getModifiers()))
			mod = "static ";
		if (Modifier.isPrivate(field.getModifiers()))
			mod += "private ";
		if (Modifier.isFinal(field.getModifiers()))
			mod += "final ";
		lblType.setText(mod + field.getType().getName());

		try {
			value = field.get(obj);
			lblValue.setText(value.toString());
			textField.setText(value.toString());
		} catch (NullPointerException e) {
			lblValue.setText("null");
			textField.setText("null");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public FieldDialog() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setBounds(87, 15, 265, 19);
		contentPanel.add(lblType);

		JLabel lblNowValue = new JLabel("New Value");
		lblNowValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblNowValue.setBounds(112, 108, 87, 26);
		contentPanel.add(lblNowValue);

		textField = new JTextField();
		textField.setBounds(216, 109, 136, 25);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblCurrentValue = new JLabel("Current Value");
		lblCurrentValue.setBounds(98, 66, 115, 19);
		contentPanel.add(lblCurrentValue);

		lblValue = new JLabel("value");
		lblValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblValue.setBounds(216, 66, 136, 19);
		contentPanel.add(lblValue);

		label = new JLabel("");
		label.setBounds(185, 161, 73, 19);
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(action);
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(action);
				buttonPane.add(cancelButton);
			}
		}
	}

	public final Object getValue() {
		return value;
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
					value = MyCastClass.castStringToAny(field.getType(), textField.getText());
					// field.set(obj, value);
					dispose();
				} catch (ClassCastException e0) {
					label.setText("パラメーターが不正またはnullです");

				} catch (IllegalArgumentException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			} else if (cmd.equals("Cancel")) {
				if (value != null)
					textField.setText(value.toString());
				else
					textField.setText("null");
			}
		}
	}
}

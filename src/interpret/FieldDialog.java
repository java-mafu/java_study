package interpret;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

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
	JLabel label;
	private JTextField textField;
	JLabel lblType;

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
	public FieldDialog(Object obj, Field field){
		this();
		this.field = field;
		lblType.setText(field.getType().getName());
		try {
			value = field.get(obj);
			textField.setText(value.toString());
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
		lblType.setBounds(174, 15, 73, 19);
		contentPanel.add(lblType);

		JLabel lblNowValue = new JLabel("Value");
		lblNowValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblNowValue.setBounds(109, 66, 55, 26);
		contentPanel.add(lblNowValue);

		textField = new JTextField();
		textField.setBounds(216, 67, 136, 25);
		contentPanel.add(textField);
		textField.setColumns(10);

		label = new JLabel("");
		label.setBounds(174, 149, 73, 19);
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

	public final Object getValue(){
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
					dispose();
				} catch (ClassCastException e0) {
					label.setText("パラメーターが不正またはnullです");

				}
			} else if (cmd.equals("Cancel")) {
				textField.setText(value.toString());
			}
		}
	}
}

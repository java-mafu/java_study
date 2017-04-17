package interpret;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class ConstructorDialog extends JDialog {

	private JPanel contentPanel = new JPanel();
	private Class<?>[] types;
	private Object[] parameters;
	private JTextField textField;
	private JLabel[] parameterslabel;
	private JTextField[] parametersTexts;

	JTextPane txtpnMessageBox;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConstructorDialog dialog = new ConstructorDialog();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * typeはコンストラクタ作成で使用 */
	public final Class<?>[] getTypes(){
		return types;
	}

	/**
	 * Create the dialog.
	 */
	public ConstructorDialog(Class<?>[] types) {
		this.types = types;
		setLayout();
	}

	public ConstructorDialog() {}

	private void setLayout() {

		contentPanel = new JPanel();
		setModal(true);
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		txtpnMessageBox = new JTextPane();
		txtpnMessageBox.setText("message box");
		txtpnMessageBox.setBounds(67, 267, 306, 25);
		contentPanel.add(txtpnMessageBox);

		parameterslabel = new JLabel[types.length];
		parametersTexts = new JTextField[types.length];

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

	public Object[] getParameters() {
		return parameters;
	}

	private void castParameters() throws ClassCastException {
		Object[] objs = new Object[types.length];

		for (int i = 0; i < types.length; i++) {
			// Class c = types[i];
			objs[i] = MyCastClass.castStringToAny(types[i], parametersTexts[i].getText());
			if (objs[i] == null)
				throw new ClassCastException();
		}
		parameters = objs;
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
					castParameters();
					dispose();
				} catch (ClassCastException e0) {
					txtpnMessageBox.setText("パラメーターが不正またはnullです");

				}
			} else if (cmd.equals("Cancel")) {
				dispose();
			}
		}
	}
}

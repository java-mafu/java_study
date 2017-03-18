package interpret;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InterpretSwing extends JFrame {

	CreateObject object;

	ConstructorDialog constructorDialog;



	private JPanel contentPane;
	private JTextField textField;
	private final Action action = new SwingAction();
	JLabel lblText;
	JList list;
	JList list_1;
	JList list_2;
	DefaultListModel constructorlistmodel = new DefaultListModel();
	DefaultListModel fieldlistmodel = new DefaultListModel();
	DefaultListModel methodlistmodel = new DefaultListModel();
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterpretSwing frame = new InterpretSwing();
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
	public InterpretSwing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 790);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		constructorDialog = new ConstructorDialog();



		JLabel lblClass = new JLabel("Class");
		lblClass.setBounds(88, 10, 41, 19);

		textField = new JTextField();
		textField.setBounds(194, 6, 190, 27);
		textField.setColumns(10);

		JButton Search = new JButton("Search");
		Search.setBounds(402, 6, 85, 27);
		Search.addActionListener(action);

		JLabel lblField = new JLabel("Field");
		lblField.setBounds(123, 253, 36, 19);

		JLabel lblMethod = new JLabel("Method");
		lblMethod.setBounds(413, 253, 73, 19);

		lblText = new JLabel("text");
		lblText.setBounds(105, 559, 429, 88);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 275, 242, 179);
		contentPane.setLayout(null);
		contentPane.add(lblClass);
		contentPane.add(textField);
		contentPane.add(Search);
		contentPane.add(lblField);
		contentPane.add(lblMethod);
		contentPane.add(scrollPane);

		list = new JList(fieldlistmodel);
		scrollPane.setViewportView(list);
		contentPane.add(lblText);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(304, 277, 283, 177);
		contentPane.add(scrollPane_1);

		list_1 = new JList(methodlistmodel);
		scrollPane_1.setViewportView(list_1);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(165, 48, 247, 158);
		contentPane.add(scrollPane_2);

		list_2 = new JList(constructorlistmodel);
		scrollPane_2.setViewportView(list_2);

		JButton btnCreateinstance = new JButton("createInstance");
		btnCreateinstance.setBounds(194, 208, 182, 27);
		btnCreateinstance.addActionListener(action);
		contentPane.add(btnCreateinstance);

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			if (cmd.equals("Search")) {
				showConstructors();
			} else if (cmd.equals("createInstance")) {
				createInstance();
				showClass();
			}
		}

		private void showConstructors() {
			try {
				object = new CreateObject(textField.getText());
				constructorlistmodel.clear();
				fieldlistmodel.clear();
				methodlistmodel.clear();
				lblText.setText("");
				List<java.lang.reflect.Type[]> constructorList = object.showConstructorList();
				for (java.lang.reflect.Type[] tarray : constructorList) {
					String str = new String();
					str += CreateObject.erasePackageName(textField.getText());
					str += "(";
					if (tarray.length != 0) {
						for (java.lang.reflect.Type t : tarray)
							str += CreateObject.erasePackageName(t.getTypeName()) + ",";
						str = str.substring(0, str.lastIndexOf(","));
					}
					str += ")";
					constructorlistmodel.addElement(str);
				}
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				lblText.setText("Class not found");
			}

		}

		private void createInstance(){
			constructorDialog.setVisible(true);
		}

		private void showClass() {
			if (object == null)
				return;
			try {
				object.newInstanceCreate();
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
					| IllegalArgumentException | InvocationTargetException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			String str = "Field:";
			for (Field f : object.getFields()) {
				str += "\n" + f.getName();
				fieldlistmodel.addElement(f.getName());
			}
			list.ensureIndexIsVisible(fieldlistmodel.getSize() - 1);
			str += "\n Method:";
			for (Method m : object.getMethods()) {
				str += "\n" + m.getName();
				String methodfullname = CreateObject.erasePackageName(m.getGenericReturnType().getTypeName()) + " "
						+ m.getName();
				java.lang.reflect.Type[] types;
				if ((types = m.getGenericParameterTypes()).length != 0) {
					methodfullname += "(";
					for (java.lang.reflect.Type t : types) {
						methodfullname += CreateObject.erasePackageName(t.getTypeName()) + ", ";
					}
					methodfullname = methodfullname.substring(0, methodfullname.lastIndexOf(", "));
					methodfullname += ")";
				}
				methodlistmodel.addElement(methodfullname);
			}
			list_1.ensureIndexIsVisible(fieldlistmodel.getSize() - 1);
			lblText.setText(str);
		}

	}
}

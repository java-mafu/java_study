package interpret;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InterpretSwing extends JFrame {

	CreateObject object;

	ConstructorDialog constructorDialog;
	FieldDialog fieldDialog;
	MethodDialog methodDialog;

	// ３つのListで選択されているindex(constructor,field,methodの順番)
	private int clickedList[];
	private List<Class<?>[]> constructorType;
	private List<Field> fieldList;
	private List<Method> methodList;

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
	private JScrollPane scrollPane_1;
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
		clickedList = new int[3];
		constructorType = new LinkedList<Class<?>[]>();
		fieldList = new LinkedList<Field>();
		methodList = new LinkedList<Method>();

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
		lblText.setFont(new Font("MS UI Gothic", Font.PLAIN, 30));
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

		scrollPane_1 = new JScrollPane();
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

		JButton btnFieldEdit = new JButton("Field Edit");
		btnFieldEdit.setBounds(82, 469, 119, 27);
		btnFieldEdit.addActionListener(action);
		contentPane.add(btnFieldEdit);

		JButton btnMethodEdit = new JButton("Method Edit");
		btnMethodEdit.setBounds(368, 469, 166, 27);
		btnMethodEdit.addActionListener(action);
		contentPane.add(btnMethodEdit);

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
				if (list_2.isSelectionEmpty()) {
					return;
				}
				createInstance();
				try {
					object.newInstanceCreate(constructorDialog.getParameters());
				} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
						| IllegalArgumentException | InvocationTargetException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
				showClass();
			} else if (cmd.equals("Field Edit")) {
				editField();
			} else if (cmd.equals("Method Edit")) {
				doMethod();
			}

		}

		private void editField() {
			fieldDialog = new FieldDialog(object.getOperatedObject(), fieldList.get(list.getSelectedIndex()));
			fieldDialog.setVisible(true);
			Field[] f = object.getFields();
			try {
				f[list.getSelectedIndex()].set(object.getOperatedObject(), fieldDialog.getValue());
				object.setFields(f);
			} catch (IllegalArgumentException e0) {
				lblText.setText("値が不正です");
			} catch (IllegalAccessException e1) {
				lblText.setText("final値の対応はまだできていません");
			}
		}

		private void doMethod() {
			System.out.println(list_1.getSelectedIndex());
			methodDialog = new MethodDialog(object.getOperatedObject(), methodList.get(list_1.getSelectedIndex()));
			methodDialog.setVisible(true);
			if (methodDialog.getReturnValue() != null)
				lblText.setText("戻り値：" + methodDialog.getReturnValue().toString());
		}

		private void reset() {
			clickedList = new int[3];
			constructorType.clear();
			constructorlistmodel.clear();
			fieldlistmodel.clear();
			fieldList.clear();
			methodlistmodel.clear();
			methodList.clear();
			list = new JList(fieldlistmodel);
			scrollPane.setViewportView(list);
			list_1 = new JList(methodlistmodel);
			scrollPane_1.setViewportView(list_1);
			list_2 = new JList(constructorlistmodel);
			scrollPane_2.setViewportView(list_2);
			lblText.setText("");
		}

		private void showConstructors() {
			try {
				object = new CreateObject(textField.getText());
				reset();
				List<java.lang.reflect.Type[]> constructorList = object.showConstructorList();
				for (java.lang.reflect.Type[] tarray : constructorList) {
					String str = new String();
					str += CreateObject.erasePackageName(textField.getText());
					str += "(";
					Class<?>[] constParams = new Class[tarray.length];
					if (tarray.length != 0) {
						int index = 0;
						for (java.lang.reflect.Type t : tarray) {
							constParams[index++] = (Class<?>) t;
							str += CreateObject.erasePackageName(t.getTypeName()) + ",";
						}
						str = str.substring(0, str.lastIndexOf(","));

					}
					constructorType.add(constParams);
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

		private void createInstance() {
			if (constructorType.get(list_2.getSelectedIndex()).length != 0) {
				constructorDialog = new ConstructorDialog(constructorType.get(list_2.getSelectedIndex()));
				constructorDialog.setVisible(true);
			} else
				constructorDialog = new ConstructorDialog();
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
			fieldlistmodel.clear();
			methodlistmodel.clear();
			String str = "Field:";
			for (Field f : object.getFields()) {
				str += "\n" + f.getName();
				fieldList.add(f);
				fieldlistmodel.addElement(f.getName());
			}
			list.ensureIndexIsVisible(fieldlistmodel.getSize() - 1);
			str += "\n Method:";
			for (Method m : object.getMethods()) {
				str += "\n" + m.getName();
				methodList.add(m);
				String methodfullname = CreateObject.erasePackageName(m.getGenericReturnType().getTypeName()) + " "
						+ m.getName();
				java.lang.reflect.Type[] types;
				methodfullname += "(";
				if ((types = m.getGenericParameterTypes()).length != 0) {

					for (java.lang.reflect.Type t : types) {
						methodfullname += CreateObject.erasePackageName(t.getTypeName()) + ", ";
					}
					methodfullname = methodfullname.substring(0, methodfullname.lastIndexOf(", "));

				}
				methodfullname += ")";
				methodlistmodel.addElement(methodfullname);
			}
			list_1.ensureIndexIsVisible(fieldlistmodel.getSize() - 1);

		}

	}

	private class ListListener implements ListSelectionListener {
		int index;

		public ListListener(int index) {
			this.index = index;
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			index = e.getLastIndex();
		}
	}
}

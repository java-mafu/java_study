package interpret;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("rawtypes")
public class EditObjectDialog extends JDialog {
	Class<?> editClass;
	String title;
	ComponentParts components[];
	ButtonListener bl;
	CreatedObject createdObject;

	List<Constructor> constructorList;
	List<Field> fieldList;
	JTextField fieldValue;

	List<Method> methodList;
	List<Method> methodAllList;
	JTextField methodSearchField;
	JLabel methodReturnLabel;
	JTextField methodReturn;
	JButton methodSearchButton;

	private class ComponentParts {
		JList list;
		DefaultListModel initListModel;
		DefaultListModel listmodel;
		JButton button;

		ComponentParts(JList list, DefaultListModel listmodel, JButton button) {
			this.list = list;
			this.initListModel = listmodel;
			this.listmodel = new DefaultListModel();
			for (int i = 0; i < listmodel.size(); i++)
				this.listmodel.addElement(listmodel.getElementAt(i));
			this.button = button;

		}
	}

	JFrame exceptionFrame;
	JFrame messageFrame;

	public EditObjectDialog(Class<?> c, int index) {
		this(c);
		title += "[" + index + "]";
		setTitle(title);
	}

	@SuppressWarnings("unchecked")
	public EditObjectDialog(Class<?> c) {
		editClass = c;
		title = editClass.getName();
		setTitle(title);
		components = new ComponentParts[3];
		constructorList = new ArrayList<Constructor>();
		fieldList = new ArrayList<Field>();
		fieldValue = new JTextField();
		methodList = new ArrayList<Method>();
		methodAllList = new ArrayList<Method>();
		methodSearchField = new JTextField();
		methodSearchButton = new JButton("Search");
		methodReturn = new JTextField();
		methodReturnLabel = new JLabel("Return:");

		exceptionFrame = new JFrame("Exception");
		messageFrame = new JFrame();

		bl = new ButtonListener();
		setBounds(300, 100, 555, 600);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initLayout();

	}

	@SuppressWarnings("unchecked")
	private void initLayout() {
		setLayout(null);
		components[0] = layoutParts("Constructor", "newInstance", 0);
		setConstructorList();

		components[1] = layoutParts("Field", "editField", 1);
		components[2] = layoutParts("Method", "useMethod", 2);

	}

	/** 値が配列の場合に表示を正しく行うためのメソッド */
	public String returnArray(Object arrayObject) {
		String result = "{";
		for (int i = 0; i < Array.getLength(arrayObject); i++) {
			result += Array.get(arrayObject, i).toString() + ", ";
		}
		if (result.lastIndexOf(",") != -1)
			result = result.substring(0, result.lastIndexOf(","));
		result += "}";

		return result;
	}

	@SuppressWarnings("unchecked")
	private void setConstructorList() {
		for (Constructor con : editClass.getConstructors()) {
			constructorList.add(con);
			String str = con.getName();
			int lastDot = str.lastIndexOf(".");
			if (lastDot != -1)
				str = str.substring(lastDot + 1);
			java.lang.reflect.Type[] paramList = con.getGenericParameterTypes();
			str += "(";
			if (paramList.length != 0) {
				for (java.lang.reflect.Type type : paramList) {
					str += type.getTypeName();
					str += ", ";
				}
				str = str.substring(0, str.lastIndexOf(","));
			}
			str += ")";
			components[0].listmodel.addElement(str);
		}
		components[0].list.setModel(components[0].listmodel);
		components[0].button.addActionListener(bl);
	}

	@SuppressWarnings("unchecked")
	private void setFieldList() {
		fieldList.clear();
		components[1].listmodel.clear();
		for (Field field : createdObject.getFields()) {
			fieldList.add(field);
			String mod = "";
			if (Modifier.isStatic(field.getModifiers()))
				mod = "static ";
			if (Modifier.isPrivate(field.getModifiers()))
				mod += "private ";
			if (Modifier.isFinal(field.getModifiers()))
				mod += "final ";
			String str = mod + field.getType().getTypeName();
			int lastDot = str.lastIndexOf(".");
			if (lastDot != -1)
				str = str.substring(lastDot + 1);
			str += " " + field.getName();
			components[1].listmodel.addElement(str);
		}
		components[1].list.setModel(components[1].listmodel);
		components[1].list.addListSelectionListener(new ListListener());
		components[1].button.addActionListener(bl);
	}

	@SuppressWarnings("unchecked")
	private void setMethodList() {
		methodAllList.clear();
		components[2].initListModel.clear();
		for (Method method : createdObject.getMethods()) {
			methodAllList.add(method);
			methodList.add(method);
			String str = method.getReturnType().getTypeName();
			int lastDot = str.lastIndexOf(".");
			if (lastDot != -1)
				str = str.substring(lastDot + 1);
			str += " " + method.getName();
			java.lang.reflect.Type[] paramList = method.getGenericParameterTypes();
			str += "(";
			if (paramList.length != 0) {
				for (java.lang.reflect.Type type : paramList) {
					str += type.getTypeName();
					str += ", ";
				}
				str = str.substring(0, str.lastIndexOf(","));
			}
			str += ")";
			components[2].initListModel.addElement(str);
		}
		components[2].list.setModel(components[2].initListModel);
		components[2].button.addActionListener(bl);
		methodSearchButton.addActionListener(bl);
	}

	private ComponentParts layoutParts(String labelName, String buttonName, int pos) {

		JLabel label;
		JList list;
		DefaultListModel listmodel;
		JButton button;
		JScrollPane scrollPane;
		label = new JLabel(labelName);
		JPanel panel = new JPanel();
		panel.add(label);
		listmodel = new DefaultListModel();
		list = new JList(listmodel);
		scrollPane = new JScrollPane(list);
		button = new JButton(buttonName);

		// Layout
		switch (pos) {
		case 0:
			panel.setBounds(getWidth() / 4 - 50, 0, 100, 30);
			scrollPane.setBounds(getWidth() / 4 - 100, 30, 200, 100);
			button.setBounds(getWidth() / 4 - 100, 140, 200, 20);
			break;
		case 1:
			panel.setBounds(getWidth() / 4 - 50, getHeight() * 2 / 5 - 30, 100, 30);
			scrollPane.setBounds(getWidth() / 4 - 100, getHeight() * 2 / 5, 200, 200);
			fieldValue.setBounds(getWidth() / 4 - 100, getHeight() * 2 / 5 + 210, 200, 20);
			add(fieldValue);
			button.setBounds(getWidth() / 4 - 100, getHeight() * 2 / 5 + 240, 200, 20);
			break;
		case 2:
			panel.setBounds(getWidth() * 3 / 4 - 50, 0, 100, 30);
			methodSearchField.setBounds(getWidth() * 3 / 4 - 100, 30, 100, 30);
			methodSearchButton.setBounds(getWidth() * 3 / 4, 30, 100, 30);
			add(methodSearchField);
			add(methodSearchButton);
			scrollPane.setBounds(getWidth() * 3 / 4 - 100, 60, 200, 400);
			methodReturn.setBounds(getWidth() * 3 / 4 - 50, 470, 150, 20);
			methodReturnLabel.setBounds(getWidth() * 3 / 4 - 100, 470, 100, 20);
			add(methodReturn);
			add(methodReturnLabel);
			button.setBounds(getWidth() * 3 / 4 - 100, 500, 200, 20);
			break;
		default:
			break;
		}

		// add
		add(panel);
		add(scrollPane);
		add(button);

		ComponentParts cp = new ComponentParts(list, listmodel, button);
		return cp;
	}

	// ListListener
	private class ListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			String obj;
			try {
				Class<?> fieldClass = createdObject.getFieldValue(fieldList.get(components[1].list.getSelectedIndex()))
						.getClass();
				if (fieldClass.isArray()) {
					obj = returnArray(
							createdObject.getFieldValue(fieldList.get(components[1].list.getSelectedIndex())));
				} else
					obj = createdObject.getFieldValue(fieldList.get(components[1].list.getSelectedIndex())).toString();
				fieldValue.setText(obj);

			} catch (IllegalArgumentException | IllegalAccessException e1) {
				JOptionPane.showMessageDialog(exceptionFrame, "Cannot access Field");
			}

		}

	}

	// Button Listener
	private class ButtonListener implements ActionListener {

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			Object btnObj = e.getSource();
			if (btnObj == components[0].button) {
				newInstanceButtonAction();
			} else if (btnObj == components[1].button) {
				editFieldButtonAction();
			} else if (btnObj == components[2].button) {
				editMethodAction();
			} else if (btnObj == methodSearchButton) {
				String methodname;
				if ((methodname = methodSearchField.getText()) == "") {
					methodList = methodAllList;
					components[2].list.setModel(components[2].initListModel);
					return;
				} else {
					List<Method> newMethodList = new ArrayList<Method>();
					components[2].listmodel.clear();
					for (int i = 0; i < methodAllList.size(); i++) {
						if (methodAllList.get(i).getName().contains(methodname)) {
							newMethodList.add(methodAllList.get(i));
							components[2].listmodel.addElement(components[2].initListModel.getElementAt(i));
						}
						methodList = newMethodList;
					}
					components[2].list.setModel(components[2].listmodel);
				}
			}
		}

		private void newInstanceButtonAction() {
			JList<?> list = components[0].list;
			int selectedClassIndex;
			if ((selectedClassIndex = list.getSelectedIndex()) == -1) {
				JOptionPane.showMessageDialog(exceptionFrame, "Please select Constructor");
				return;
			}
			Constructor constructor = constructorList.get(selectedClassIndex);
			createdObject = new CreatedObject(editClass, constructor);
			if (constructor.getParameterTypes().length == 0) {
				try {
					createdObject.newInstanceCreate();
				} catch (InstantiationException | IllegalAccessException e1) {
					e1.printStackTrace();
				}
			} else {
				ParameterDialog pd = new ParameterDialog(constructor.getParameterTypes());
				pd.setVisible(true);
				Object[] parameter = pd.getParameters();
				if (parameter != null) {
					createdObject.newInstanceCreate(parameter);
				}
			}
			if (createdObject.getFields() != null && createdObject.getMethods() != null) {
				setFieldList();
				setMethodList();
			}
		}

		private void editFieldButtonAction() {
			JList<?> list = components[1].list;
			int selectedClassIndex;
			if ((selectedClassIndex = list.getSelectedIndex()) == -1) {
				JOptionPane.showMessageDialog(exceptionFrame, "Please select Field");
				return;
			}
			Field field = fieldList.get(selectedClassIndex);
			Class<?> type = field.getType();
			try {
				if (type.isArray()) {
					Object[] arrayObj = MyCastClass.castStringToArray(type.getComponentType(), fieldValue.getText());

					createdObject.setFieldValue(field, arrayObj);
				} else
					createdObject.setFieldValue(field, MyCastClass.castStringToAny(type, fieldValue.getText()));
				JOptionPane.showMessageDialog(messageFrame, "Value changed");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				JOptionPane.showMessageDialog(messageFrame, e.toString());
				try {
					Object createdObjectFieldValue;
					if ((createdObjectFieldValue = createdObject.getFieldValue(field)).getClass().isArray())
						fieldValue.setText(returnArray(createdObjectFieldValue));
					else
						fieldValue.setText(createdObjectFieldValue.toString());
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}
			}
		}

		private void editMethodAction() {
			JList<?> list = components[2].list;
			int selectedIndex;
			if ((selectedIndex = list.getSelectedIndex()) == -1) {
				JOptionPane.showMessageDialog(exceptionFrame, "Please select Method");
				return;
			}
			Method method = methodList.get(selectedIndex);
			Class<?>[] types = method.getParameterTypes();
			Object[] parameter = null;
			if (types.length != 0) {
				ParameterDialog pd = new ParameterDialog(types);
				pd.setVisible(true);
				parameter = pd.getParameters();
			}
			try {
				Object obj = createdObject.callObjectMethod(method, parameter);
				if (obj != null) {
					Class<?> methodClass = obj.getClass();
					String returnStr;
					if (methodClass.isArray()) {
						returnStr = returnArray(obj);
					} else {
						returnStr = obj.toString();
					}
					methodReturn.setText(returnStr);

				}
			} catch (Exception e) {
				if (e.toString().contains("reflect"))
					JOptionPane.showMessageDialog(exceptionFrame, e.getCause().toString());
				else
					JOptionPane.showMessageDialog(exceptionFrame, e.toString());
			}

		}
	}
}

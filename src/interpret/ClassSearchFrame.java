package interpret;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ClassSearchFrame extends JFrame {
	JTextField textField;
	JButton searchButton;
	JScrollPane scrollPane;
	JList list;
	DefaultListModel listmodel;
	JTextField arrayNumText;
	JTextField nameText;
	JButton selectClassButton;
	JFrame exceptionFrame;
	List<Class<?>> classList;

	// インスタンス関係
	JList insJList;
	DefaultListModel insJListmodel;
	JScrollPane insScrollPane;
	static Map<String, JDialog> instances;
	JButton editObjectButton;

	// editClass
	Class<?> editClass;

	// ArrayDialog
	ArrayDialog arrayDialog;

	public static void main(String[] args) {
		ClassSearchFrame csd = new ClassSearchFrame();
		csd.setVisible(true);
	}

	public ClassSearchFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		setLayout(null);
		setTitle("Interpret");

		// Exception frame
		exceptionFrame = new JFrame();

		JLabel info = new JLabel("Please input ClassName !\n");
		JLabel addInfo = new JLabel("\"java.lang\", \"java.util\", \"java.awt\"は省略可");
		textField = new JTextField();
		searchButton = new JButton("Class Search");
		listmodel = new DefaultListModel();
		list = new JList(listmodel);
		scrollPane = new JScrollPane(list);
		arrayNumText = new JTextField("1");
		nameText = new JTextField();
		selectClassButton = new JButton("Create Object");
		JLabel arrayInfo = new JLabel("Array Number");
		JLabel nameInfo = new JLabel("Name");

		insJListmodel = new DefaultListModel();
		insJList = new JList(insJListmodel);
		insScrollPane = new JScrollPane(insJList);
		editObjectButton = new JButton("Edit Object");

		// Layout
		info.setBounds(10, 0, 265, 15);
		addInfo.setBounds(10, 20, 265, 15);
		textField.setBounds(10, 40, 265, 20);
		searchButton.setBounds(70, 70, 150, 20);
		scrollPane.setBounds(10, 100, 265, 100);
		arrayInfo.setBounds(10, 210, 80, 20);
		nameInfo.setBounds(10, 240, 40, 20);
		arrayNumText.setBounds(110, 210, 40, 20);
		nameText.setBounds(50, 240, 100, 20);
		selectClassButton.setBounds(70, 260, 150, 20);

		insScrollPane.setBounds(300, 40, 265, 200);
		editObjectButton.setBounds(300, 250, 265, 20);
		// add
		add(info);
		add(addInfo);
		add(textField);
		add(searchButton);
		add(scrollPane);
		add(arrayInfo);
		add(arrayNumText);
		add(nameText);
		add(nameInfo);
		add(selectClassButton);
		add(editObjectButton);

		JLabel ins = new JLabel("Created Objects");
		ins.setBounds(300, 20, 265, 20);
		add(ins);
		add(insScrollPane);
		// ButtonListener
		ButtonListener bl = new ButtonListener();
		searchButton.addActionListener(bl);
		selectClassButton.addActionListener(bl);
		editObjectButton.addActionListener(bl);

		instances = new HashMap<String, JDialog>();
	}

	/** class search */
	private List<Class<?>> searchClass(String className) {
		String[] searchPackage = { "java.lang.", "java.util.", "java.awt." };
		List<Class<?>> expectedClass = new ArrayList<Class<?>>();
		Class<?> selectedClass;
		try {
			// if className=full pass, return Class<?>
			selectedClass = Class.forName(className);
			expectedClass.add(selectedClass);
			return expectedClass;
		} catch (ClassNotFoundException e) {
			// if not fullname
		}
		for (String str : searchPackage) {
			try {
				selectedClass = Class.forName(str + className);
				expectedClass.add(selectedClass);

			} catch (ClassNotFoundException e) {
				//
			}
		}
		return expectedClass;
	}

	public final JDialog getInstance(String key) {
		return instances.get(key);
	}

	public boolean addInstance(String key, JDialog value) {
		if (!instances.containsKey(key)) {
			instances.put(key, value);
			return true;
		}
		JOptionPane.showMessageDialog(exceptionFrame, "Instance名かぶり");
		return false;
	}

	// Class search Button
	private class ButtonListener implements ActionListener {
		List<Class<?>> classList;

		public ButtonListener() {
			classList = new ArrayList<Class<?>>();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object btnObj = e.getSource();
			if (btnObj == searchButton) {
				listmodel.clear();
				classList = searchClass(textField.getText());
				for (Class<?> c : classList) {
					listmodel.addElement(c.getName());
				}
				list.setModel(listmodel);
			} else if (btnObj == selectClassButton) {
				int selectedClassIndex;
				if ((selectedClassIndex = list.getSelectedIndex()) == -1) {
					JOptionPane.showMessageDialog(exceptionFrame, "Class not selected");
					return;
				}
				editClass = classList.get(selectedClassIndex);
				try {

					int objectNum = Integer.parseInt(arrayNumText.getText());
					String objectName = nameText.getText();
					if (objectNum <= 0) {
						JOptionPane.showMessageDialog(exceptionFrame, "Please write Object number at least 1");
					} else if (objectName.equals("")) {
						JOptionPane.showMessageDialog(exceptionFrame, "Please write Object Name");
					} else if (objectNum == 1) {
						EditObjectDialog eod = new EditObjectDialog(editClass);
						if (addInstance(objectName, eod))
							insJListmodel.addElement(objectName + "(" + editClass.getName() + ")");
						// eod.setVisible(true);
					} else {
						arrayDialog = new ArrayDialog(objectNum, objectName);
						for (int i = 0; i < objectNum; i++) {
							EditObjectDialog eod = new EditObjectDialog(editClass, i);
							arrayDialog.addButton(eod);
						}
						if (addInstance(objectName, arrayDialog))
							insJListmodel.addElement(objectName + "(" + editClass.getName() + "[])");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(exceptionFrame, ex.toString());
					return;
				}

			} else if (btnObj == editObjectButton) {
				int selectedObjIndex;
				if ((selectedObjIndex = list.getSelectedIndex()) == -1) {
					JOptionPane.showMessageDialog(exceptionFrame, "Object not selected");
					return;
				}
				JDialog eod = getInstance((insJList.getSelectedValue().toString().substring(0,
						insJList.getSelectedValue().toString().indexOf("("))));
				eod.setVisible(true);
			}
		};
	}

}

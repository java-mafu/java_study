package interpret;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
	JButton selectClassButton;
	JFrame exceptionFrame;
	List<Class<?>> classList;

	//editClass
	Class<?> editClass;

	//ArrayDialog
	ArrayDialog arrayDialog;

	public static void main(String[] args) {
		ClassSearchFrame csd = new ClassSearchFrame();
		csd.setVisible(true);
	}

	public ClassSearchFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 350);
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
		selectClassButton = new JButton("Create Object");
		JLabel arrayInfo = new JLabel("Array Number");

		// Layout
		info.setBounds(10, 0, 265, 15);
		addInfo.setBounds(10, 20, 265, 15);
		textField.setBounds(10, 40, 265, 20);
		searchButton.setBounds(70, 70, 150, 20);
		scrollPane.setBounds(10, 100, 265, 100);
		arrayInfo.setBounds(10, 210, 80, 20);
		arrayNumText.setBounds(50, 240, 40, 20);
		selectClassButton.setBounds(100, 240, 150, 20);
		// add
		add(info);
		add(addInfo);
		add(textField);
		add(searchButton);
		add(scrollPane);
		add(arrayInfo);
		add(arrayNumText);
		add(selectClassButton);
		// ButtonListener
		ButtonListener bl = new ButtonListener();
		searchButton.addActionListener(bl);
		selectClassButton.addActionListener(bl);
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
					if (objectNum <= 0) {
						JOptionPane.showMessageDialog(exceptionFrame, "Please write Object number at least 1");
					} else if (objectNum == 1) {
						EditObjectDialog eod = new EditObjectDialog(editClass);
						eod.setVisible(true);
					} else {
						arrayDialog = new ArrayDialog(objectNum);
						for (int i = 0; i < objectNum; i++) {
							EditObjectDialog eod = new EditObjectDialog(editClass, i);
							arrayDialog.addButton(eod);
						}
						arrayDialog.setVisible(true);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(exceptionFrame, "Please write Object number");
					return;
				}

			}
		};
	}

}

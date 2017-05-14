package interpret;

import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class InstanceList extends JFrame {

	JList list;
	DefaultListModel listmodel;
	JScrollPane scrollPane;
	Map<String, Object> instances;

	JFrame exceptionFrame;

	public InstanceList() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 350);
		setLayout(null);
		setTitle("Interpret");

		listmodel = new DefaultListModel();
		list = new JList(listmodel);
		scrollPane = new JScrollPane(list);

		// Layout
		scrollPane.setBounds(10, 100, 265, 100);
		// add
		add(scrollPane);

		instances = new HashMap<String, Object>();
		exceptionFrame = new JFrame("Exception");
	}

	public final Object getInstance(String key) {
		return instances.get(key);
	}

	public void addInstance(String key, Object value) {
		if (!instances.containsKey(key)){
			instances.put(key, value);
			listmodel.addElement(key + "(" + value.getClass().getName() + ")");
		}
		else
			JOptionPane.showMessageDialog(exceptionFrame, "Instance名かぶり");
	}

	public static void main(String[] args) {
		InstanceList il = new InstanceList();
		il.addInstance("hoge", new JButton());
		il.addInstance("fuga", new JButton());
		il.addInstance("test", new JButton());
		il.setVisible(true);
	}
}

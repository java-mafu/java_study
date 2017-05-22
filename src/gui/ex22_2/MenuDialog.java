package gui.ex22_2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MenuDialog extends JDialog {

	private Font font;
	private int fontSize;
	private Color fontColor;
	private Color backGroundColor;

	private JList fontList;
	private JScrollPane fontListPane;
	private DefaultListModel model;
	private JList fontSizeList;
	private JTextField[] fontColorField;
	private JTextField[] backgroundColorField;

	// 環境情報の参照
	GraphicsEnvironment ge;
	Font fonts[];

	public MenuDialog() {
		super();
		initLayout();
	}

	private void setClockFont(Font font) {
		this.font = font;
	}

	private void setClockFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	private void setClockFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	private void setClockBackgroundColor(Color backgroundColor) {
		this.backGroundColor = backGroundColor;
	}

	private void initLayout() {
		setLayout(null);
		fontList = new JList();
		fontListPane = new JScrollPane();
		fontListPane.setPreferredSize(new Dimension(200, 120));
		fontSizeList = new JList();
		fontColorField = new JTextField[3];
		backgroundColorField = new JTextField[3];
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		fonts = ge.getAllFonts();
		model = new DefaultListModel();
		for (Font font : fonts)
			model.addElement(font.getFontName());
		fontList.setModel(model);
		fontListPane.add(fontList);
		add(fontListPane);
	}
}

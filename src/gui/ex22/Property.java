package gui.ex22;


import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class Property extends WindowAdapter implements ActionListener{
	private static final int WINDOW_WIDTH = 80;
	private static final int WINDOW_HEIGHT = 150;
	private Frame frame;
	private String fontname;
	private int fsize;
	private Color fontColor;
	private Color backgroundColor;
	private Font font;
	static private enum FontSize{
		LARGE,
		MEDIUM,
		SMALL;

		public static int getFontSize(FontSize f){
			switch(f){
			case LARGE:
				return 200;
			case MEDIUM:
				return 100;
			case SMALL:
				return 50;
			default:
				return 100;
			}
		}

	}


	static private class TransPoint{
		static int x,y;

	}

	public Property(){
		fsize = 50;
		TransPoint.x = fsize*7;
		TransPoint.y = fsize + 80;
		fontname = "Serif";
		fontColor = Color.black;
		backgroundColor = Color.white;
		font = new Font(fontname, Font.PLAIN, fsize);
	}

	void setSize(FontSize f){
		fsize = FontSize.getFontSize(f);
		TransPoint.x = fsize*7;
		TransPoint.y = fsize + 80;
	}

	public int getWindowSizeX(){
		return TransPoint.x;
	}

	public int getWindowSizeY(){
		return TransPoint.y + fsize;
	}

	public void addMenuber(Frame f){
		frame = f;
		MenuBar mb = new MenuBar();
		frame.addWindowListener(this);
		Menu menu = mb.add(new Menu("property"));

		Menu fontmenu = (Menu)menu.add(new Menu("フォントの指定"));
		MenuItem serif = fontmenu.add(new MenuItem("Serif"));
		MenuItem sans_serif = fontmenu.add(new MenuItem("Sans-serif"));
		MenuItem monospace = fontmenu.add(new MenuItem("Monospace"));
		MenuItem dialog = fontmenu.add(new MenuItem("Dialog"));
		MenuItem dialogInput = fontmenu.add(new MenuItem("DialogInput"));
		MenuItem original = fontmenu.add(new MenuItem("Original"));

		Menu fontsize = (Menu)menu.add(new Menu("フォントサイズの指定"));
		MenuItem big = fontsize.add(new MenuItem("大"));
		MenuItem medium = fontsize.add(new MenuItem("中"));
		MenuItem small = fontsize.add(new MenuItem("小"));

		Menu fontColor = (Menu)menu.add(new Menu("文字色の指定"));
		MenuItem fred = fontColor.add(new MenuItem("文字赤"));
		MenuItem fblue = fontColor.add(new MenuItem("文字青"));
		MenuItem fyellow = fontColor.add(new MenuItem("文字黄"));
		MenuItem fblack = fontColor.add(new MenuItem("文字黒"));

		Menu backgroundColor = (Menu)menu.add(new Menu("背景色の指定"));
		MenuItem bred = backgroundColor.add(new MenuItem("背景赤"));
		MenuItem bblue = backgroundColor.add(new MenuItem("背景青"));
		MenuItem byellow = backgroundColor.add(new MenuItem("背景黄"));
		MenuItem bblack = backgroundColor.add(new MenuItem("背景黒"));
		MenuItem bwhite = backgroundColor.add(new MenuItem("背景白"));
		serif.addActionListener(this);
		sans_serif.addActionListener(this);
		monospace.addActionListener(this);
		dialog.addActionListener(this);
		dialogInput.addActionListener(this);
		original.addActionListener(this);

		big.addActionListener(this);
		medium.addActionListener(this);
		small.addActionListener(this);

		bred.addActionListener(this);
		bblue.addActionListener(this);
		byellow.addActionListener(this);
		bblack.addActionListener(this);
		bwhite.addActionListener(this);

		fred.addActionListener(this);
		fblue.addActionListener(this);
		fyellow.addActionListener(this);
		fblack.addActionListener(this);

		frame.setMenuBar(mb);

	}


	public class SubMenuItem extends MenuItem{
		SubMenuItem(String str, String superStr){
			super(str);
		}
	}
	public void actionPerformed(ActionEvent e) {
		changeFont(e);
		changeFontSize(e);
		changeFontColor(e);
		changeBackgroundColor(e);
	}

	public final Font getPropertyFont(){
		font = new Font(fontname, Font.PLAIN, fsize);
		return font;
	}

	public final Color getFontColor(){
		return fontColor;
	}


	public final Color getBackgroundColor(){
		return backgroundColor;
	}

	private void changeFont(ActionEvent e) {
		if (e.getActionCommand() == "Serif"
				||e.getActionCommand() == "Sans-serif"
				||e.getActionCommand() == "Monospace"
				||e.getActionCommand() == "Dialog"
				||e.getActionCommand() == "DialogInput"){
			fontname = e.getActionCommand();
		}
	}

	private void changeFontSize(ActionEvent e){
		if (e.getActionCommand() == "大") setSize(FontSize.LARGE);
		if (e.getActionCommand() == "中") setSize(FontSize.MEDIUM);
		if (e.getActionCommand() == "小") setSize(FontSize.SMALL);
	}

	private void changeFontColor(ActionEvent e){
		if (e.getActionCommand() == "文字赤") fontColor = Color.red;
		if (e.getActionCommand() == "文字青") fontColor = Color.blue;
		if (e.getActionCommand() == "文字黄") fontColor = Color.yellow;
		if (e.getActionCommand() == "文字黒") fontColor = Color.black;
		if (e.getActionCommand() == "文字白") fontColor = Color.white;
	}


	private void changeBackgroundColor(ActionEvent e) {
		if (e.getActionCommand() == "背景赤") backgroundColor = Color.red;
		if (e.getActionCommand() == "背景青") backgroundColor = Color.blue;
		if (e.getActionCommand() == "背景黄") backgroundColor = Color.yellow;
		if (e.getActionCommand() == "背景黒") backgroundColor = Color.black;
		if (e.getActionCommand() == "背景白") backgroundColor = Color.white;
	}
}
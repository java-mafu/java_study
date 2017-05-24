package gui.ex23;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.lang.reflect.Field;

public class ClockPopupMenu extends WindowAdapter implements ActionListener {

	DigitalClock window;
	private String fontname;
	private int fsize;
	private Color fontColor;
	private Color backgroundColor;
	private Font font;

	static private enum FontSize {
		LARGE, MEDIUM, SMALL;

		public static int getFontSize(FontSize f) {
			switch (f) {
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

	public ClockPopupMenu(DigitalClock tw, PopupMenu pm) {
		window = tw;
		init(pm);
	}

	private void init(PopupMenu pm) {
		window.addWindowListener(this);

		fsize = 50;
		TransPoint.x = fsize * 7;
		TransPoint.y = fsize + 80;
		fontname = "Serif";
		fontColor = Color.black;
		backgroundColor = Color.white;
		font = new Font(fontname, Font.PLAIN, fsize);

		Menu fontmenu = (Menu) pm.add(new Menu("フォントの指定"));
		Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		MenuItem fontList[] = new MenuItem[fonts.length];
		for (int i = 0; i < fontList.length; i++) {
			fontList[i] = fontmenu.add(new MenuItem(fonts[i].getFontName()));
			fontList[i].addActionListener(new FontChanger());
		}

		Menu fontsize = (Menu) pm.add(new Menu("フォントサイズの指定"));
		MenuItem[] fontSizes = new MenuItem[FontPixel.MAX_FONT];
		for (int i = 0; i < fontSizes.length; i++) {
			fontSizes[i] = fontsize.add(new MenuItem("" + (i + 1)));
			fontSizes[i].addActionListener(new FontSizeChanger());

		}
		Field[] colorFieldAll = Color.class.getFields();
		Field[] colorField = new Field[colorFieldAll.length/2-1];
		for(int i = 0; i< colorField.length;i++)
			colorField[i] = colorFieldAll[i*2];

		Menu fontColor = (Menu) pm.add(new Menu("文字色の指定"));
		Menu backgroundColor = (Menu) pm.add(new Menu("背景色の指定"));
		MenuItem[] ColorItems = new MenuItem[colorField.length];
		MenuItem[] ColorBackItems = new MenuItem[colorField.length];
		FontColorSetter csF = new FontColorSetter();
		BackColorSetter csB = new BackColorSetter();
		for (int i = 0; i < colorField.length; i++) {
			ColorItems[i] = fontColor.add(new MenuItem(colorField[i].getName()));
			ColorItems[i].addActionListener(csF);
			ColorBackItems[i] = backgroundColor.add(new MenuItem(colorField[i].getName()));
			ColorBackItems[i].addActionListener(csB);
		}

		MenuItem exit = (MenuItem) pm.add(new MenuItem("exit"));
		exit.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "exit") {
			System.exit(0);
		}
	}

	static private class TransPoint {
		static int x, y;

	}

	void setSize(FontSize f) {
		fsize = FontSize.getFontSize(f);
		TransPoint.x = fsize * 7;
		TransPoint.y = fsize + 80;
	}

	public int getWindowSizeX() {
		return TransPoint.x;
	}

	public int getWindowSizeY() {
		return TransPoint.y + fsize;
	}

	public final Font getPropertyFont() {
		font = new Font(fontname, Font.PLAIN, fsize);
		return font;
	}

	public final Color getFontColor() {
		return fontColor;
	}

	public final Color getBackgroundColor() {
		return backgroundColor;
	}

	private class FontChanger implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			fontname = e.getActionCommand();

		}
	}

	private class FontSizeChanger implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			fsize = Integer.parseInt(e.getActionCommand());

		}
	}

	private class FontColorSetter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				fontColor = (Color) Color.class.getField(e.getActionCommand()).get(null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private class BackColorSetter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				backgroundColor = (Color) Color.class.getField(e.getActionCommand()).get(null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}

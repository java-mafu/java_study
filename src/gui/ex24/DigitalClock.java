package gui.ex24;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DigitalClock extends JFrame {

	static DigitalClock clock;

	public static void main(String[] args) {
		clock = new DigitalClock();
		clock.setVisible(true);
	}


	public TimerPanel tp;
	MenuDialog md;
	Thread dialogThread;

	Font font;
	Color fontColor;
	Color backgroundColor;


	private Preferences prefs;
    private static final String KEY_BY_FT[] = {"point_x_Do_or_Die", "point_y_Do_or_Die"};

	public DigitalClock() {
		prefs = Preferences.userNodeForPackage(this.getClass());
		addWindowListener(new MyWindowAdapter());
		setBounds(prefs.getInt(KEY_BY_FT[0], 100), prefs.getInt(KEY_BY_FT[1], 100), 171, 80);
		setResizable(false);
		tp = new TimerPanel();
		setLayout(new BorderLayout());
		add(tp);
		md = new MenuDialog();
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem menuItem = new JMenuItem("Property");
		menu.add(menuItem);
		menubar.add(menu);
		setJMenuBar(menubar);
		menuItem.addActionListener(new MenuAction());

		dialogThread = new Thread(new DialogRunnable());
		dialogThread.start();
	}

	private class DialogRunnable implements Runnable {
		@Override
		public void run() {
			while (true) {
				font = md.getNewFont();
				fontColor = md.getFontColor();
				backgroundColor = md.getBackgroundColor();
				tp.setData(font, fontColor, backgroundColor);
				try {
					DigitalClock.this.setSize(
							(int) FontPixel.getFontPixelSize(font.getSize() - 1).getWidth() * 9,
							(int) (FontPixel.getFontPixelSize(font.getSize() - 1).getHeight()*0.7) + 50);
				} catch (NullPointerException e) {
					// 一回目はnull
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}

	}


	class MyWindowAdapter extends WindowAdapter {
	    public void windowClosing(WindowEvent e) {
	    	 try {
	    	prefs.putInt(KEY_BY_FT[0], clock.getLocationOnScreen().x);
	    	prefs.putInt(KEY_BY_FT[1], clock.getLocationOnScreen().y);
	    	prefs.flush();
	    	 } catch (BackingStoreException ex) {
	             ex.printStackTrace();
	         }
	    	System.exit(0);
	    }
	}

	/**
	 * メニューのアクションがあるのであればこのクラスに追加する
	 *
	 * @author p000526831
	 *
	 */
	class MenuAction implements ActionListener {
		// メニューのイベント処理
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand() == "Property") {
				md.setVisible(true);
			}
		}
	}
}

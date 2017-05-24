package gui.ex22;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DigitalClock extends JFrame {

	public static void main(String[] args) {
		DigitalClock clock = new DigitalClock();
		clock.setVisible(true);
	}

	public TimerPanel tp;
	MenuDialog md;
	Thread dialogThread;

	Font font;
	Color fontColor;
	Color backgroundColor;

	public DigitalClock() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 171, 80);
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
			boolean initfrag = true;
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
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
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

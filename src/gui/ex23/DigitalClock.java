package gui.ex23;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.PopupMenu;

import javax.swing.JWindow;

public class DigitalClock extends JWindow {

	public static void main(String[] args) {
		DigitalClock clock = new DigitalClock();
		clock.setVisible(true);
	}

	public TimerPanel tp;
	Thread dialogThread;

	MouseResponse mouse;
	PopupMenu pop;
	ClockPopupMenu cmenu;

	Font font;
	Color fontColor;
	Color backgroundColor;

	public DigitalClock() {
		setBounds(100, 100, 171, 30);
		tp = new TimerPanel();
		setLayout(new BorderLayout());
		add(tp);
		pop = new PopupMenu();
	    add(pop);
	    mouse = new MouseResponse(this, pop);
	    cmenu = new ClockPopupMenu(this, pop);

		dialogThread = new Thread(new DialogRunnable());
		dialogThread.start();
	}

	private class DialogRunnable implements Runnable {
		@Override
		public void run() {
			while (true) {
				font = cmenu.getPropertyFont();
				fontColor = cmenu.getFontColor();
				backgroundColor = cmenu.getBackgroundColor();
				tp.setData(font, fontColor, backgroundColor);
				try {
					DigitalClock.this.setSize(
							(int) FontPixel.getFontPixelSize(font.getSize() - 1).getWidth() * 9,
							(int) (FontPixel.getFontPixelSize(font.getSize() - 1).getHeight()*0.7));
				} catch (NullPointerException e) {
					// 一回目はnull
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}

	}
}

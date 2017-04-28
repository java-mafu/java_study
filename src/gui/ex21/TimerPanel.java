package gui.ex21;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimerPanel extends JPanel {
	boolean firstFrag;
	Thread th;
	String hourText;
	String minuteText;
	String secondText;

	public class FontData {
		private static final String name = "Monospaced";
		private static final int style = Font.BOLD;
		private static final int size = 32;
	}

	Font largeFont = new Font(FontData.name, FontData.style, FontData.size);
	Font smallFont = new Font(FontData.name, Font.BOLD, 16);

	public TimerPanel() {
		super();
		firstFrag = true;
		this.setLayout(new FlowLayout());
		th = new Thread(new Timer());
		th.start();
	}

	public void paintComponent(Graphics g) {
		if (firstFrag) {
			firstFrag = false;
			new FontPixel(g);
		}
		g.setColor(this.getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.blue);
		g.setFont(largeFont);
		g.drawString(alignDigit(hourText), 10, getHeight() - 10);
		g.drawString(":", 50, getHeight() - 10);
		g.drawString(alignDigit(minuteText), 70, getHeight() - 10);
		g.setFont(smallFont);
		g.drawString(alignDigit(secondText), 110, getHeight() - 10);
	}

	private String alignDigit(String number) {
		switch (number.length()) {
		case 1:
			return 0 + number;
		case 2:
			return number;
		default:
			return "00";
		}
	}

	class Timer implements Runnable {
		@Override
		public void run() {
			while (true) {
				hourText = String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
				minuteText = String.valueOf(Calendar.getInstance().get(Calendar.MINUTE));
				secondText = String.valueOf(Calendar.getInstance().get(Calendar.SECOND));
				repaint();
			}
		}
	}
}

package gui.ex24;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Calendar;

import javax.swing.JPanel;

public class TimerPanel extends JPanel {
	boolean firstFrag;
	Thread th;
	String hourText;
	String minuteText;
	String secondText;

	Font font;
	Color fontColor;
	Color backgroundColor;

	public void setData(Font font, Color fontColor, Color backgroundColor) {
		this.font = font;
		this.fontColor = fontColor;
		this.backgroundColor = backgroundColor;
	}

	public TimerPanel() {
		super();
		fontColor = Color.BLACK;
		backgroundColor = Color.WHITE;
		font = new Font("Monospaced", Font.BOLD, 32);
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

		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(fontColor);
		g.setFont(font);
		g.drawString(alignDigit(hourText), 10, getHeight());
		g.drawString(":", 10 + (int) FontPixel.getFontPixelSize(font.getSize() - 1).getWidth() * 2, getHeight());
		g.drawString(alignDigit(minuteText), 10 + (int) FontPixel.getFontPixelSize(font.getSize() - 1).getWidth() * 3,
				getHeight());
		g.drawString(":", 10 + (int) FontPixel.getFontPixelSize(font.getSize() - 1).getWidth() * 5, getHeight());
		g.drawString(alignDigit(secondText), 10 + (int) FontPixel.getFontPixelSize(font.getSize() - 1).getWidth() * 6,
				getHeight());
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

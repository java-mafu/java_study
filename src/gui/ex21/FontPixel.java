package gui.ex21;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FontPixel {

	private static final int MAX_FONT = 100;

	private static Rectangle[] fontPixelSize;

	public FontPixel(Graphics g) {
		Font font;
		fontPixelSize = new Rectangle[MAX_FONT];
		for (int i = 0; i < MAX_FONT; i++) {
			font = new Font("Monospaced", Font.PLAIN, i + 1);
			fontPixelSize[i] = new Rectangle();
			fontPixelSize[i].height = g.getFontMetrics(font).getHeight();
			fontPixelSize[i].width = g.getFontMetrics(font).charWidth('0');
		}
	}

	public static final Rectangle getFontPixelSize(int idx) {
		return fontPixelSize[idx];
	}
}

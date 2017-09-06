package tetris_0;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tetrimino {

	private Rectangle[] minos;
	private Color minoColor;
	private Rectangle tetriminoRect;
	private MinoForm form;
	private final int minoSize;

	public Tetrimino(MinoForm form, int startPosition, int minoSize) {
		this.form = form;
		this.minoSize = minoSize;
		minos = form.rotate();
		minoColor = form.getColor();
		minoRectCreate(startPosition, 0);
	}

	private void minoRectCreate(int startPositionX, int startPositionY) {
		tetriminoRect = new Rectangle(Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 0);
		for (int i = 0; i < 4; i++) {
			minos[i].setX(minoSize * minos[i].getX() + startPositionX);
			minos[i].setY(minoSize * minos[i].getY() + startPositionY);
			minos[i].setWidth(minoSize);
			minos[i].setHeight(minoSize);
			if (tetriminoRect.getX() > minos[i].getX())
				tetriminoRect.setX(minos[i].getX());
			if (tetriminoRect.getY() > minos[i].getY())
				tetriminoRect.setY(minos[i].getY());
			if (tetriminoRect.getWidth() < minos[i].getX() + minoSize)
				tetriminoRect.setWidth(minos[i].getX() + minoSize);
			if (tetriminoRect.getHeight() < minos[i].getY() + minos[i].getHeight())
				tetriminoRect.setHeight(minos[i].getY() + minoSize);
		}
	}

	public Rectangle[] getMinos() {
		return minos;
	}

	public Color getColor() {
		return minoColor;
	}

	public Rectangle getSize() {
		return tetriminoRect;
	}

	public void drop() {
		for (Rectangle rect : minos)
			rect.setY(rect.getY() + rect.getHeight());
		tetriminoRect.setY(tetriminoRect.getY() + minos[0].getHeight());
	}

	public void minoUp(){
		for (int i = 0; i < minos.length; i++) {
			minos[i].setY(minos[i].getY() - minoSize);
		}
	}

	public void minoLeft(){
		for (int i = 0; i < minos.length; i++) {
			minos[i].setX(minos[i].getX() - minoSize);
		}
	}

	public void move(List<String> commandList) {
		if (commandList.contains("LEFT")) {
			for (int i = 0; i < minos.length; i++) {
				minos[i].setX(minos[i].getX() - minos[i].getWidth());
			}
			tetriminoRect.setX(tetriminoRect.getX() - minoSize);
			commandList.remove("LEFT");
		}
		if (commandList.contains("RIGHT")) {
			for (int i = 0; i < minos.length; i++) {
				minos[i].setX(minos[i].getX() + minos[i].getWidth());
			}
			tetriminoRect.setX(tetriminoRect.getX() + minoSize);
			commandList.remove("RIGHT");
		}
		if (commandList.contains("DOWN")) {
			for (int i = 0; i < minos.length; i++) {
				minos[i].setY(minos[i].getY() + minoSize);
			}
			tetriminoRect.setY(tetriminoRect.getY() + minoSize);
			commandList.remove("DOWN");
		}
		if (commandList.contains("SPACE")) {
			minos = form.rotate();
			minoRectCreate((int) tetriminoRect.getX(), (int) tetriminoRect.getY());
			commandList.remove("SPACE");
		}
	}

}

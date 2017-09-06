package tetris_0;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public enum MinoForm {

	I(Color.BLUE),
	O(Color.YELLOW),
	S(Color.GREEN),
	Z(Color.ORANGE),
	J(Color.PINK),
	L(Color.CYAN),
	T(Color.RED);

	Color color;
	int form;
	int size;

	MinoForm(Color color) {
		form = 0;
		this.color = color;
	};

	public Color getColor() {
		return color;
	}

	public int getForm() {
		return form;
	}

	private Rectangle createRectangle(int i) {
		switch (i) {
		case 0:
			return new Rectangle(0, 0, 1, 1);
		case 1:
			return new Rectangle(1, 0, 1, 1);
		case 2:
			return new Rectangle(2, 0, 1, 1);
		case 3:
			return new Rectangle(3, 0, 1, 1);
		case 4:
			return new Rectangle(0, 1, 1, 1);
		case 5:
			return new Rectangle(1, 1, 1, 1);
		case 6:
			return new Rectangle(2, 1, 1, 1);
		case 7:
			return new Rectangle(3, 1, 1, 1);
		case 8:
			return new Rectangle(0, 2, 1, 1);
		case 9:
			return new Rectangle(1, 2, 1, 1);
		case 10:
			return new Rectangle(2, 2, 1, 1);
		case 11:
			return new Rectangle(3, 2, 1, 1);
		case 12:
			return new Rectangle(0, 3, 1, 1);
		case 13:
			return new Rectangle(1, 3, 1, 1);
		case 14:
			return new Rectangle(2, 3, 1, 1);
		case 15:
			return new Rectangle(3, 3, 1, 1);
		default:
			return null;
		}
	}

	public Rectangle[] rotate() {
		form++;
		if(form>3)
			form=0;
		switch (this) {
		case I:
			return rotateI();
		case O:
			return rotateO();
		case S:
			return rotateS();
		case Z:
			return rotateZ();
		case J:
			return rotateJ();
		case L:
			return rotateL();
		case T:
			return rotateT();
		default:
			return null;
		}
	}

	private Rectangle[] rotateI() {
		switch (form) {
		case 0:
		case 2:
			return new Rectangle[] { createRectangle(0),createRectangle(4),createRectangle(8),createRectangle(12) };
		case 1:
		case 3:
			return new Rectangle[] { createRectangle(0),createRectangle(1),createRectangle(2),createRectangle(3) };
		default:
			return null;
		}
	}

	private Rectangle[] rotateO() {
		return new Rectangle[] { createRectangle(0),createRectangle(1),createRectangle(4),createRectangle(5) };
	}

	private Rectangle[] rotateS() {
		switch (form) {
		case 0:
		case 2:
			return new Rectangle[] { createRectangle(0),createRectangle(4),createRectangle(5),createRectangle(9) };
		case 1:
		case 3:
			return new Rectangle[] { createRectangle(1),createRectangle(2),createRectangle(4),createRectangle(5) };
		default:
			return null;
		}
	}

	private Rectangle[] rotateZ() {
		switch (form) {
		case 0:
		case 2:
			return new Rectangle[] { createRectangle(1),createRectangle(4),createRectangle(5),createRectangle(8) };
		case 1:
		case 3:
			return new Rectangle[] { createRectangle(0),createRectangle(1),createRectangle(5),createRectangle(6) };
		default:
			return null;
		}
	}

	private Rectangle[] rotateJ() {
		switch (form) {
		case 0:
			return new Rectangle[] { createRectangle(0),createRectangle(1),createRectangle(4),createRectangle(8) };
		case 1:
			return new Rectangle[] { createRectangle(0),createRectangle(1),createRectangle(2),createRectangle(6) };
		case 2:
			return new Rectangle[] { createRectangle(1),createRectangle(5),createRectangle(8),createRectangle(9) };
		case 3:
			return new Rectangle[] { createRectangle(0),createRectangle(4),createRectangle(5),createRectangle(6) };
		default:
			return null;
		}
	}
	private Rectangle[] rotateL() {
		switch (form) {
		case 0:
			return new Rectangle[] { createRectangle(0),createRectangle(1),createRectangle(5),createRectangle(9) };
		case 1:
			return new Rectangle[] { createRectangle(2),createRectangle(4),createRectangle(5),createRectangle(6) };
		case 2:
			return new Rectangle[] { createRectangle(0),createRectangle(4),createRectangle(8),createRectangle(9) };
		case 3:
			return new Rectangle[] { createRectangle(0),createRectangle(1),createRectangle(2),createRectangle(4) };
		default:
			return null;
		}
	}

	private Rectangle[] rotateT() {
		switch (form) {
		case 0:
			return new Rectangle[] { createRectangle(0),createRectangle(4),createRectangle(5),createRectangle(8) };
		case 1:
			return new Rectangle[] { createRectangle(0),createRectangle(1),createRectangle(2),createRectangle(5) };
		case 2:
			return new Rectangle[] { createRectangle(1),createRectangle(4),createRectangle(5),createRectangle(9) };
		case 3:
			return new Rectangle[] { createRectangle(1),createRectangle(4),createRectangle(5),createRectangle(6) };
		default:
			return null;
		}
	}

}

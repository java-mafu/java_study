package gui.botu;

public class Scaffold extends StageElement {

	double basePosition;

	public Scaffold(int basePosition, int positionX, int positionY) {
		this.basePosition = basePosition;
		setPosition(positionX, basePosition - positionY);
	}

	/** 障害物までたどり着いていない場合は-1を返す */
	public double relativeStandPosition(double heroPositionX, double heroPositionY, double heroWidth,
			double heroHeight) {
		if (heroPositionX + heroWidth > getPositionX() && heroPositionX < getPositionX() + getWidth()) {
			if (heroPositionY <= getPositionY())
				return getPositionY() - heroHeight;
		}
		return basePosition;
	}

	public double relativeWallLeft(double heroPositionX, double heroPositionY, double heroWidth, double heroHeight) {
		if (heroPositionY + heroHeight > getPositionY()) {
			if(heroPositionX > getPositionX() + getWidth())
				return getPositionX() + getWidth();
		}
		return 0;
	}

	public double relativeWallRight(double heroPositionX, double heroPositionY, double heroWidth, double heroHeight) {
		if (heroPositionY + heroHeight > getPositionY()) {
			if (heroPositionX + heroWidth < getPositionX())
				return getPositionX();
		}
		return 1000;
	}

}

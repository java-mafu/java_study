package gui.botu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Hero extends StageElement {

	List<String> commandList;
	Map<Poses, Image> poseMap;
	int width;
	int height;
	double leftLimit;
	double rightLimit;
	double lowLimit;
	boolean stayBoolean;

	public Hero(Canvas canvas, List<String> commandList) {
		this.commandList = commandList;
		leftLimit = 0;
		rightLimit = canvas.getWidth();
		lowLimit = canvas.getHeight();

		initImage();
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	public void setLimit(double left, double right, double low) {
		leftLimit = left;
		rightLimit = right;
		lowLimit = low;
	}

	enum Poses {
		STAY, RUNR, RUNL, JUMP,
	}

	@Override
	public void update(double time) {
		super.update(time);
		if (getVelocityX() == 0) {
			if (getVelocityY() == 0)
				setImage(poseMap.get(Poses.STAY));
			else
				setImage(poseMap.get(Poses.JUMP));
		} else if (getVelocityX() > 0)
			setImage(poseMap.get(Poses.RUNR));
		else
			setImage(poseMap.get(Poses.RUNL));
	}

	private void initImage() {
		poseMap = new HashMap<Poses, Image>();
		poseMap.put(Poses.STAY, new Image("gui/botu/images/stay_boy.png"));
		poseMap.put(Poses.RUNR, new Image("gui/botu/images/run_boyR.png"));
		poseMap.put(Poses.RUNL, new Image("gui/botu/images/run_boyL.png"));
		poseMap.put(Poses.JUMP, new Image("gui/botu/images/jump_boy.png"));

		width = Math.max((int) poseMap.get(Poses.STAY).getWidth(), (int) poseMap.get(Poses.RUNR).getWidth());
		width = Math.max(width, (int) poseMap.get(Poses.RUNL).getWidth());
		width = Math.max(width, (int) poseMap.get(Poses.JUMP).getWidth());

		height = Math.max((int) poseMap.get(Poses.STAY).getHeight(), (int) poseMap.get(Poses.RUNR).getHeight());
		height = Math.max(height, (int) poseMap.get(Poses.RUNL).getHeight());
		height = Math.max(height, (int) poseMap.get(Poses.JUMP).getHeight());

		stayBoolean = true;
	}

	public void setKeyResponse(double elapsedTime, double standPosition) {
		if (commandList.contains("LEFT") && !commandList.contains("RIGHT") && getVelocityX() > -10) {
			addVelocity(-1, 0);
		} else if (!commandList.contains("LEFT") && commandList.contains("RIGHT") && getVelocityX() < 10) {
			addVelocity(1, 0);
		} else {
			if (getVelocityX() > 0)
				addVelocity(-1, 0);
			else if (getVelocityX() < 0)
				addVelocity(1, 0);
		}

		if (commandList.contains("UP") && stayBoolean) {
			stayBoolean = false;
			addVelocity(0, -15);
		} else {
			if (getPositionY() < standPosition)
				addVelocity(0, 1 * elapsedTime);
			else {
				setVelocity(getVelocityX(), 0);
				stayBoolean=true;
				//setPosition(getPositionX(), standPosition);
			}
		}

	}

}

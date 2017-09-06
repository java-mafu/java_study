package gui.botu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class AbstractStage extends AnimationTimer {
	GraphicsContext gc;
	List<String> commandList;
	int standPosition;
	double old;

	Rectangle stageRect;
	byte[][] stageObstacle;

	// game logic
	Hero briefcase;
	List<Scaffold> scafList;

	public AbstractStage(Canvas canvas, GraphicsContext gc, List<String> commandList) {
		stageRect = new Rectangle();
		stageRect.setWidth(canvas.getWidth());
		stageRect.setHeight(canvas.getHeight());
		stageObstacle = new byte[(int) canvas.getWidth()][(int) canvas.getHeight()];
		this.gc = gc;
		this.commandList = commandList;
		old = System.currentTimeMillis();
		briefcase = new Hero(canvas, commandList);
		standPosition = (int) (stageRect.getHeight() - 2 - briefcase.getHeight());
		briefcase.setPosition(0, standPosition);
		briefcase.setVelocity(0, 0);

		setupObstacle();

	}

	private void setupObstacle() {
		scafList = new ArrayList<Scaffold>();
		scafList.add(new Scaffold(standPosition - 1, 100, 0));
		scafList.add(new Scaffold(standPosition - 1, 300, 100));
		scafList.add(new Scaffold(standPosition - 1, 500, 200));
		for (Scaffold s : scafList)
			s.setImage("gui/botu/images/right.png");
		for (int x = 0; x < stageObstacle.length; x++) {
			stageObstacle[x][0] = 1;
			stageObstacle[x][stageObstacle[x].length - 1] = 1;
		}
		for (int y = 0; y < stageObstacle[0].length; y++) {
			stageObstacle[0][y] = 1;
			stageObstacle[stageObstacle.length - 1][y] = 1;
		}
		ExecutorService ex = Executors.newCachedThreadPool();
		for (Scaffold s : scafList) {
			ex.execute(() -> {
				for (int x = (int) s.getPositionX(); x < (int) (s.getPositionX() + (int) s.getWidth()); x++)
					for (int y = (int) s.getPositionY(); y < (int) (s.getPositionY() + s.getHeight()); y++) {
						if (endValueBool(x, y, x, y))
							stageObstacle[x][y] = 1;
					}
			});
		}
		ex.shutdown();
	}

	private void renderObstacle() {
		for (Scaffold s : scafList)
			s.render(gc);
	}

	@Override
	public void handle(long now) {
		// calculate time since last update.
		double elapsedTime = (now - old) / 20000000.0;
		old = now;
		briefcase.setKeyResponse(elapsedTime, standPosition);
		assertObstacle();

		briefcase.update(elapsedTime);

		gc.clearRect(0, 0, stageRect.getWidth(), stageRect.getHeight());
		renderObstacle();
		briefcase.render(gc);
	}

	private void assertObstacle() {
		double px, py, vx, vy, pw, ph;
		px = briefcase.getPositionX();
		py = briefcase.getPositionY();
		vx = briefcase.getVelocityX();
		vy = briefcase.getVelocityY();
		pw = briefcase.getPositionX() + briefcase.getWidth();
		ph = briefcase.getPositionY() + briefcase.getHeight();
		// 当たり判定

		if ((int) Math.round(ph) < stageRect.getHeight()
				||(int) Math.round(px) >=0
				||(int) Math.round(pw) < stageRect.getWidth()) {
			for (int y = (int) Math.round(ph); y < stageRect.getHeight(); y++)
				if (stageObstacle[(int) Math.round(px)][y] == 1 || stageObstacle[(int) Math.round(pw)][y] == 1) {
					standPosition = y - (int) briefcase.getHeight() - 1;
					break;
				}
		}

		if (vy > 0) {
			if (ph + vy > stageRect.getHeight() - 1) {
				briefcase.setVelocity(vx, 0);
				briefcase.setPosition(px, standPosition);
			} else if (stageObstacle[(int) Math.round(px)][(int) Math.round(ph + vy)] == 1
					|| stageObstacle[(int) Math.round(pw)][(int) Math.round(ph + vy)] == 1) {
				int xl = (int) Math.round(px);
				int xr = (int) Math.round(pw);
				int y = (int) Math.round(ph);
				while (endValueBool(xl,y,xr,y) && stageObstacle[xl][y] == 0 && stageObstacle[xr][y] == 0)
					y++;
				briefcase.setVelocity(vx, 0);
				briefcase.setPosition(px, y - Math.round(briefcase.getHeight()) + 2);
			}
		}
		if (vy < 0) {
			if (py + vy < 0) {
				briefcase.setVelocity(vx, 0);
				briefcase.setPosition(px, 1);
			} else if (stageObstacle[(int) Math.round(px)][(int) Math.round(py + vy)] == 1
					|| stageObstacle[(int) Math.round(pw)][(int) Math.round(py + vy)] == 1) {
				int xl = (int) Math.round(px);
				int xr = (int) Math.round(pw);
				int y = (int) Math.round(py);
				while (endValueBool(xl,y,xr,y) && stageObstacle[xl][y] == 0 && stageObstacle[xr][y] == 0)
					y--;
				briefcase.setVelocity(vx, 0);
				briefcase.setPosition(px, y);
			}
		}
		if (vx > 0) {
			if (pw + vx > stageRect.getWidth() - 1) {
				briefcase.setVelocity(0, vy);
				briefcase.setPosition(Math.round(stageRect.getWidth() - briefcase.getWidth()) - 2, py);
			} else if (stageObstacle[(int) Math.round(pw + vx)][(int) Math.round(py)] == 1
					|| stageObstacle[(int) Math.round(pw + vx)][(int) Math.round(ph)] == 1) {
				int x = (int) Math.round(pw);
				int yl = (int) Math.round(py);
				int yh = (int) Math.round(ph);
				while (endValueBool(x,yl,x,yh) && stageObstacle[x][yl] == 0 && stageObstacle[x][yh] == 0)
					x++;
				briefcase.setVelocity(0, vy);
				briefcase.setPosition(x - Math.round(briefcase.getWidth()), py);
			}
		}
		if (vx < 0) {
			if (px + vx < 0) {
				briefcase.setVelocity(0, vy);
				briefcase.setPosition(1, py);
			} else if (stageObstacle[(int) Math.round(px + vx)][(int)(py)] == 1
					|| stageObstacle[(int) Math.round(px + vx)][(int)(ph)] == 1) {
				int x = (int) Math.round(px);
				int yl = (int) Math.round(py);
				int yh = (int) Math.round(ph);
				while (endValueBool(x,yl,x,yh) && stageObstacle[x][yl] == 0 && stageObstacle[x][yh] == 0)
					x--;
				briefcase.setVelocity(0, vy);
				briefcase.setPosition(x+1, py);
			}
		}
	}

	private boolean endgetVelocityBool(StageElement element) {
		if (element.getVelocityX() > 0) {
			if (element.getVelocityY() > 0)
				return endValueBool(0, 0, element.getPositionX() + element.getWidth() + 1,
						element.getPositionY() + element.getHeight() + 1);
			else if (element.getVelocityY() < 0)
				return endValueBool(0, element.getPositionY() - 1, element.getPositionX() + element.getWidth() + 1, 0);
			else
				return endValueBool(0, 0, element.getPositionX() + element.getWidth() + 1, 0);
		} else if (element.getVelocityX() < 0) {
			if (element.getVelocityY() > 0)
				return endValueBool(element.getPositionX() - 1, 0, 0, element.getPositionY() + element.getHeight() + 1);
			else if (element.getVelocityY() < 0)
				return endValueBool(element.getPositionX() - 1, element.getPositionY() - 1, 0, 0);
			else
				return endValueBool(element.getPositionX() - 1, 0, 0, 0);
		} else {
			if (element.getVelocityY() > 0)
				return endValueBool(0, 0, 0, element.getPositionY() + element.getHeight() + 1);
			else if (element.getVelocityY() < 0)
				return endValueBool(0, element.getPositionY() - 1, 0, 0);
			else
				return true;
		}
	}

	private boolean endValueBool(double xmin, double ymin, double xmax, double ymax) {
		return xmin >= 0 && ymin >= 0 && xmax < stageRect.getWidth() && ymax < stageRect.getHeight();
	}

}

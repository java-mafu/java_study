package gui.tetris;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Tetris extends AnimationTimer {
	final int minoSize = 10;
	BorderPane root;
	Stage parentStage;
	GraphicsContext gc;
	Canvas canvas;
	ScorePane scoreBoard;
	List<String> commandList;
	boolean commandFlag;
	long old;
	long level;
	long delayBaseTime = 500000000;
	final int width;
	final int height;
	boolean ObjectMap[];

	List<RectColor> minoList;
	Map<Integer, Integer> minoCounter;
	Tetrimino mino;

	public class RectColor {
		Rectangle rect;
		Color color;

		public RectColor(Rectangle rect, Color color) {
			this.rect = rect;
			this.color = color;
		}

		public Rectangle getRect() {
			return rect;
		}

		public Color getColor() {
			return color;
		}
	}

	public Tetris(Stage stage, BorderPane root) {
		this(stage, root, null);
	}

	public Tetris(Stage stage, BorderPane root, List<String> commandList) {
		this.root = root;
		parentStage = stage;
		canvas = new Canvas(102, 201);// 壁用に左右と下1pixel用意
		scoreBoard = new ScorePane();
		width = (int) canvas.getWidth();
		height = (int) canvas.getHeight();
		gc = canvas.getGraphicsContext2D();
		if (commandList != null)
			this.commandList = commandList;
		commandFlag = true;
		root.setCenter(canvas);

		BorderPane pane = new BorderPane();
		pane.setCenter(scoreBoard);
		root.setLeft(scoreBoard);

		level = 1 * delayBaseTime;
		minoList = new LinkedList<RectColor>();
		ObjectMap = new boolean[width * height];
		for (int y = 0; y < height; y++) {
			ObjectMap[width * y] = true;
			ObjectMap[width * y + width - 1] = true;
		}
		for (int x = 0; x < width; x++) {
			ObjectMap[width * (height - 1) + x] = true;
		}
		mino = new Tetrimino(MinoForm.O, (int) canvas.getWidth() / 2, minoSize);
	}

	@Override
	public void handle(long now) {
		// calculate time since last update.
		double elapsedTime = (now - old) / level;
		boolean contactFlag = false;
		try {
			if (elapsedTime > 1) {

				for (Rectangle rect : mino.getMinos()) {
					if (ObjectMap[(int) (width * (rect.getY() + rect.getHeight()) + (int) rect.getX()
							+ (int) rect.getWidth() / 2)])
						contactFlag = true;
				}
				if (contactFlag) {
					for (Rectangle rect : mino.getMinos()) {
						minoList.add(new RectColor(rect, mino.getColor()));
						for (int y = 0; y < rect.getHeight(); y++)
							for (int x = 0; x < rect.getWidth(); x++) {
								ObjectMap[width * (y + (int) (rect.getY())) + (x + (int) (rect.getX()))] = true;
							}
					}
					mino = new Tetrimino(MinoForm.values()[(int) (Math.random() * MinoForm.values().length)],
							(int) canvas.getWidth() / 2, 10);
					minoCounter = new HashMap<Integer, Integer>();
					for (RectColor r : minoList) {
						if (minoCounter.containsKey((int) r.getRect().getY()))
							minoCounter.put((int) r.getRect().getY(), minoCounter.get((int) r.getRect().getY()) + 1);
						else
							minoCounter.put((int) r.getRect().getY(), 1);
					}
					int point = 1;
					List<Integer> removeMino = new LinkedList<Integer>();
					for (Integer i : minoCounter.keySet()) {
						System.out.println("(y,num)=(" + i + "," + minoCounter.get(i) + ")");
						if (minoCounter.get(i) >= width / minoSize) {
							point *= 5;
							removeMino.add(i);
						}
					}

					if (point != 1) {
						scoreBoard.addScore(point);
						List<RectColor> newMinoList = new LinkedList<RectColor>(minoList);
						for (int m = 0; m < minoList.size(); m++) {
							for (int i : removeMino) {
								if (minoList.get(m).getRect().getY() == i) {
									newMinoList.remove(minoList.get(m));
								}
							}
						}

						for (int m = 0; m < newMinoList.size(); m++) {
							Rectangle r = new Rectangle();
							r.setX(newMinoList.get(m).getRect().getX());
							r.setY(newMinoList.get(m).getRect().getY());
							r.setWidth(newMinoList.get(m).getRect().getWidth());
							r.setHeight(newMinoList.get(m).getRect().getHeight());
							for (int i : removeMino) {
								if (newMinoList.get(m).getRect().getY() < i) {
									r.setY(r.getY() + minoSize);
								}
							}
							newMinoList.set(m, new RectColor(r, newMinoList.get(m).getColor()));
						}
						minoList = newMinoList;
						ObjectMap = new boolean[width * height];
						for (int y = 0; y < height; y++) {
							ObjectMap[width * y] = true;
							ObjectMap[width * y + width - 1] = true;
						}
						for (int x = 0; x < width; x++) {
							ObjectMap[width * (height - 1) + x] = true;
						}
						for (RectColor r : minoList) {
							for (int y = 0; y < r.getRect().getHeight(); y++)
								for (int x = 0; x < r.getRect().getWidth(); x++) {
									ObjectMap[width * (y + (int) (r.getRect().getY() - 1))
											+ (x + (int) (r.getRect().getX()))] = true;
								}
						}
					}
				} else {
					if (commandFlag)
						mino.drop();
				}
				old = now;
				commandFlag = true;

			}
			if (!commandList.isEmpty())
				commandFlag = false;
			for (Rectangle rect : mino.getMinos()) {
				if (ObjectMap[(int) (width * (rect.getY() + rect.getHeight() / 2) + (int) rect.getX()
						+ (int) rect.getWidth() + 1)])
					commandList.remove("RIGHT");
				if (ObjectMap[(int) (width * (rect.getY() + rect.getHeight() / 2) + (int) rect.getX() - 1)])
					commandList.remove("LEFT");
				if (ObjectMap[(int) (width * (rect.getY() + rect.getHeight()) + (int) rect.getX()
						+ (int) rect.getWidth() / 2)])
					commandList.remove("DOWN");
			}

			mino.move(commandList);
			// rotate対応
			contactFlag = true;
			while (contactFlag) {
				contactFlag = false;
				boolean moveLeft = false;
				for (Rectangle rect : mino.getMinos()) {
					if ((int) rect.getX() + (int) rect.getWidth() / 2 > width) {
						moveLeft = true;
						contactFlag = true;
					} else if (ObjectMap[(int) (width * (rect.getY() + rect.getHeight() / 2) + (int) rect.getX()
							+ (int) rect.getWidth() / 2)])
						contactFlag = true;
				}
				if (contactFlag) {
					if (moveLeft)
						mino.minoLeft();
					else
						mino.minoUp();
				}
			}

			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			gc.strokeLine(0, 0, 0, canvas.getHeight());
			gc.strokeLine(canvas.getWidth(), 0, canvas.getWidth(), canvas.getHeight());
			gc.strokeLine(0, canvas.getHeight(), canvas.getWidth(), canvas.getHeight());
			for (RectColor rectC : minoList) {
				Rectangle rect = rectC.getRect();
				gc.setFill(rectC.getColor());
				gc.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
				gc.strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
			}

			for (Rectangle rect : mino.getMinos()) {
				gc.setFill(mino.getColor());
				gc.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
				gc.strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
			}
		} catch (Exception e) {
			dispModalDialog("Finish");
			this.stop();
			return;
		}
	}

	/** ダイアログ表示 */
	private void dispModalDialog(String title) {
		/** ダイアログ生成 */
		Window parentWindow = parentStage;
		Stage dialog = new Stage();
		dialog.setTitle(title);
		dialog.initModality(Modality.WINDOW_MODAL);
		if (parentWindow != null)
			dialog.initOwner(parentWindow);

		// ダイアログ内容
		final StackPane pane = new StackPane();
		{
			final VBox vbox = new VBox();
			pane.getChildren().add(vbox);

			vbox.setAlignment(Pos.CENTER);
			vbox.setMinWidth(150);
			vbox.getChildren().add(new Label(""));// 空行
			vbox.getChildren().add(new Label("Game Over"));
			vbox.getChildren().add(new Label("Score: " + scoreBoard.getScore()));
			vbox.getChildren().add(new Label(""));// 空行

			Button btnOk = new Button();
			btnOk.setText("close");
			btnOk.setOnAction((ActionEvent) -> {
				dialog.close();
			});

			vbox.getChildren().add(btnOk);
			vbox.getChildren().add(new Label(""));// 空行
		}

		/** ダイアログ表示設定 */
		dialog.setScene(new Scene(pane));
		dialog.show();
	}
}

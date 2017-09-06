package gui.botu;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainApp extends Application {

	public final int cWidth = 1024;
	public final int cHeight = 512;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage theStage) {
		theStage.setTitle("Ma○io");

		BorderPane root = new BorderPane();
		Scene theScene = new Scene(root);
		theStage.setScene(theScene);

		Canvas canvas = new Canvas(cWidth,cHeight);
		root.setCenter(canvas);
		Text time = new Text();
		new Thread(new Clock(time)).start();
		root.setTop(time);

		ArrayList<String> input = new ArrayList<String>();

		theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (!input.contains(code))
					input.add(code);
			}
		});

		theScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				input.remove(code);
			}
		});

		GraphicsContext gc = canvas.getGraphicsContext2D();

		Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
		gc.setFont(theFont);
		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);



		new AbstractStage(canvas, gc,input).start();
		theStage.show();
	}
}

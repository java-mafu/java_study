package tetris;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainApp extends Application {

	Tetris tetris;
	Stage stage;

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		stage.setTitle("テトリス");
		BorderPane root = new BorderPane();
		Scene theScene = new Scene(root);
		stage.setScene(theScene);

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

		HBox hbox = new HBox();
		Text time = new Text("00:00:00");
		Clock clock = new Clock(time);
		new Thread(clock).start();
		hbox.getChildren().add(time);

		tetris = new Tetris(stage, root, input);
		tetris.start();
		stage.setOnCloseRequest(req -> {
			clock.finish();
			tetris.stop();
			Platform.exit();
		});
		root.setTop(hbox);

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

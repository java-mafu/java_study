package java8.ch04.ex01;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {


	@Override
	public void start(Stage stage) throws Exception {
		// Groupを作成
		Group root = new Group();
		// text作成
		TextField textField = new TextField();
		root.getChildren().add(textField);

		// Label作成
		Label message = new Label("Hello, JavaFX!");
		message.setFont(new Font(100));
		textField.textProperty().bindBidirectional(message.textProperty());
		root.getChildren().add(message);

		stage.setScene(new Scene(root));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

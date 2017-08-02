package java8.ch04.ex09;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Stars extends Application {
	public void start(Stage stage) throws Exception {

		// 配置
		Group root = new Group();
		final double centerX = 100;
		final double centerY = 100;
		final double radiusX = 100;
		final double radiusY = 80;

		// 楕円
		Ellipse ellipse = new Ellipse();
		ellipse.setCenterX(centerX);
		ellipse.setCenterY(centerY);
		ellipse.setRadiusX(radiusX);
		ellipse.setRadiusY(radiusY);
		ellipse.setFill(Color.WHITE);
		ellipse.setStroke(Color.BLUE);
		root.getChildren().add(ellipse);

		// 中心
		Circle circle1 = new Circle(2);
		circle1.setCenterX(centerX);
		circle1.setCenterY(centerY);
		circle1.setCenterX(centerX - Math.sqrt(Math.pow(radiusX, 2) - Math.pow(radiusY, 2)));
		circle1.setCenterY(centerY);
		circle1.setFill(Color.BLACK);
		root.getChildren().add(circle1);
		Circle circle2 = new Circle(2);
		circle2.setCenterX(centerX + Math.sqrt(Math.pow(radiusX, 2) - Math.pow(radiusY, 2)));
		circle2.setCenterY(centerY);
		circle2.setFill(Color.BLACK);
		root.getChildren().add(circle2);

		// 惑星
		Circle planet = new Circle(5);
		planet.setFill(Color.BLUE);

		root.getChildren().add(planet);
		PathTransition pathTransition = new PathTransition();
		pathTransition.setNode(planet);
		pathTransition.setDuration(Duration.millis(5000));
		pathTransition.setPath(ellipse);
		pathTransition.setInterpolator(Interpolator.LINEAR);
		pathTransition.setCycleCount(PathTransition.INDEFINITE);
		pathTransition.play();

		stage.setScene(new Scene(root, 200, 200));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

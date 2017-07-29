package java8.ch03.ex06;

import java.io.File;
import java.util.function.BiFunction;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ColorTrans extends Application {

	@FunctionalInterface
	public interface ColorTransformer {
		Color apply(int x, int y, Color colorAtXY);
	}

	public static Image transform(Image in, ColorTransformer ct) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y, ct.apply(x, y, in.getPixelReader().getColor(x, y)));
		return out;
	}

	public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y), arg));
		return out;
	}

	@Override
	public void start(Stage primaryStage) {
		ImageView imageView = new ImageView();
		ImageView resultView = new ImageView();
		Image image = new Image(new File("src/images/cat.jpg").toURI().toString());
		Image result = transform(image, (c, factor) -> c.deriveColor(0, 1, factor, 1), 2.0);

		imageView.setImage(image);
		resultView.setImage(result);
		HBox hbox = new HBox();
		hbox.getChildren().add(imageView);
		hbox.getChildren().add(resultView);
		Scene scene = new Scene(hbox);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

package java8.ch03.ex08;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ColorTrans extends Application {

	@FunctionalInterface
	public interface ColorTransformer {
		Color apply(Rectangle r, int x, int y, Color colorAtXY);
	}

	public static ColorTransformer transformerMethod(int width, Color color){
		return (r, x, y, c) -> {
			if (x < width || y < width || x >= r.getWidth() - width || y >= r.getHeight() - width)
				return color;
			else
				return c;
		};
	}

	public static Image transform(Image in, ColorTransformer ct) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		Rectangle r = new Rectangle(width, height);
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y, ct.apply(r, x, y, in.getPixelReader().getColor(x, y)));
		return out;
	}

	@Override
	public void start(Stage primaryStage) {
		ImageView imageView = new ImageView();
		ImageView resultView = new ImageView();
		Image image = new Image(new File("src/images/cat.jpg").toURI().toString());
		Image result = transform(image, transformerMethod(20, Color.BLUE));

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

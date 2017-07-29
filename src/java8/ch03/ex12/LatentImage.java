package java8.ch03.ex12;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LatentImage extends Application {
	private Image in;
	private List<ColorTransformer> pendingOperations;

	@FunctionalInterface
	public interface ColorTransformer {
		Color apply(int x, int y, Color colorAtXY);
	}

	static LatentImage from(Image image) {
		LatentImage li = new LatentImage();
		li.in = image;
		li.pendingOperations = new LinkedList<ColorTransformer>();
		return li;
	}

	LatentImage transform(UnaryOperator<Color> f) {
		pendingOperations.add((x, y, c) -> f.apply(c));
		return this;
	}

	LatentImage transform(ColorTransformer ct) {
		pendingOperations.add(ct);
		return this;
	}

	public Image toImage() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				for (ColorTransformer f : pendingOperations)
					c = f.apply(x, y, c);
				out.getPixelWriter().setColor(x, y, c);
			}
		return out;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ImageView imageView = new ImageView();
		ImageView resultView = new ImageView();
		Image image = new Image(new File("src/images/cat.jpg").toURI().toString());

		Image latent = LatentImage.from(image).transform(Color::brighter).transform(Color::grayscale).toImage();
		imageView.setImage(image);
		resultView.setImage(latent);
		HBox hbox = new HBox();
		hbox.getChildren().add(imageView);
		hbox.getChildren().add(resultView);
		Scene scene = new Scene(hbox);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

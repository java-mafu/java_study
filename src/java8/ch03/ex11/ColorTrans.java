package java8.ch03.ex11;

import java.io.File;
import java.util.function.UnaryOperator;

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

	public static ColorTransformer compoundTransformer(ColorTransformer a, ColorTransformer b) {
		return (x, y, colorAtXY) -> b.apply(x, y, a.apply(x, y, colorAtXY));
	}

	public static ColorTransformer fromUnaryOperatortoColorTransformer(UnaryOperator<Color> f){
		return (x, y, c)-> f.apply(c);
	}

	@Override
	public void start(Stage primaryStage) {
		ImageView imageView = new ImageView();
		ImageView resultView = new ImageView();
		Image image = new Image(new File("src/images/cat.jpg").toURI().toString());

		UnaryOperator<Color> op = Color::brighter;
		ColorTransformer opfirst = fromUnaryOperatortoColorTransformer(op);
		ColorTransformer opsecond = (x, y, color) -> {
			if (x < 10 || y < 10 || x >= image.getWidth() - 10 || y >= image.getHeight() - 10)
				return Color.GRAY;
			else
				return color;
		};
		Image finalImage = transform(image, compoundTransformer(opfirst, opsecond));

		imageView.setImage(image);
		resultView.setImage(finalImage);
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

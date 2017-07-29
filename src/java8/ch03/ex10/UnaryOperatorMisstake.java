package java8.ch03.ex10;

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

/**composeの戻り値は、Function<V,R>である。
 * 今回の例では、transformはUnaryOperator<Color>にFunction<Color,Color>を代入することとなってしまうため、
 * 型の不一致となり、コンパイルエラーとなる
 * ラムダ式で、applyをつけることで対応可能*/

public class UnaryOperatorMisstake extends Application {

	public static Image transform(Image in, UnaryOperator<Color> f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y)));
		return out;
	}


	@Override
	public void start(Stage primaryStage) {
		ImageView imageView = new ImageView();
		ImageView resultView = new ImageView();
		Image image = new Image(new File("src/images/cat.jpg").toURI().toString());

		UnaryOperator<Color> op = Color::brighter;
		Image finalImage = transform(image, op.compose(Color::grayscale)::apply);

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

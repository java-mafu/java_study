package java8.ch03.ex13;

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

	// 隣接するpixel処理用の配列
	int[] xn4 = { 0, -1, 1, 0 };
	int[] xn8 = { -1, 0, 1, -1, 1, -1, 0, 1 };
	int[] yn4 = { -1, 0, 0, 1 };
	int[] yn8 = { -1, -1, -1, 0, 0, 1, 1, 1 };

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

	// ぼやけ処理
	LatentImage blur() {
		if (!pendingOperations.isEmpty()) {
			in = toImage();
			pendingOperations.clear();
		}
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				double[] rgb = { c.getRed(), c.getGreen(), c.getBlue() };
				int weightnum = 1;
				for (int i = 0; i < 8; i++) {
					if (x + xn8[i] >= 0 && y + yn8[i] >= 0 && x + xn8[i] < width && y + yn8[i] < height) {
						Color cn = in.getPixelReader().getColor(x + xn8[i], y + yn8[i]);
						weightnum++;
						rgb[0] += cn.getRed();
						rgb[1] += cn.getGreen();
						rgb[2] += cn.getBlue();
					}
				}
				out.getPixelWriter().setColor(x, y,
						Color.color(rgb[0] / weightnum, rgb[1] / weightnum, rgb[2] / weightnum));
			}
		in = out;
		return this;
	}

	// エッジ検出
	LatentImage edge() {
		if (!pendingOperations.isEmpty()) {
			in = toImage();
			pendingOperations.clear();
		}
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				double[] rgb = { 0.0, 0.0, 0.0 };
				int weightnum = 0;
				for (int i = 0; i < 4; i++) {
					if (x + xn4[i] >= 0 && y + yn4[i] >= 0 && x + xn4[i] < width && y + yn4[i] < height) {
						Color cn = in.getPixelReader().getColor(x + xn4[i], y + yn4[i]);
						weightnum++;
						rgb[0] += cn.getRed();
						rgb[1] += cn.getGreen();
						rgb[2] += cn.getBlue();
					}
				}
				double[] resultRGB = { Math.abs(c.getRed() * weightnum - rgb[0]),
						Math.abs(c.getGreen() * weightnum - rgb[1]), Math.abs(c.getBlue() * weightnum - rgb[2]) };
				out.getPixelWriter().setColor(x, y, Color.color(resultRGB[0] > 1 ? 1 : resultRGB[0],
						resultRGB[1] > 1 ? 1 : resultRGB[1], resultRGB[2] > 1 ? 1 : resultRGB[2]));
			}
		in = out;
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
		ImageView blurView = new ImageView();
		ImageView edgeView = new ImageView();
		Image image = new Image(new File("src/images/cat.jpg").toURI().toString());

		Image blurImg = LatentImage.from(image).transform(Color::brighter).blur().toImage();
		Image edgeImg = LatentImage.from(image).transform(Color::brighter).edge().toImage();
		imageView.setImage(image);
		blurView.setImage(blurImg);
		edgeView.setImage(edgeImg);
		HBox hbox = new HBox();
		hbox.getChildren().add(imageView);
		hbox.getChildren().add(blurView);
		hbox.getChildren().add(edgeView);
		Scene scene = new Scene(hbox);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

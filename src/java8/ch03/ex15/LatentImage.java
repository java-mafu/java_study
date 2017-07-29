package java8.ch03.ex15;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
	private List<UnaryOperator<Color>> pendingOperations;


	static LatentImage from(Image image) {
		LatentImage li = new LatentImage();
		li.in = image;
		li.pendingOperations = new LinkedList<UnaryOperator<Color>>();
		return li;
	}

	LatentImage transform(UnaryOperator<Color> f) {
		pendingOperations.add(f);
		return this;
	}


	public static Color[][] parallelTransform(Color[][] in, UnaryOperator<Color> f){
		int n = Runtime.getRuntime().availableProcessors();
		int height = in.length;
		int width = in[0].length;
		Color[][] out = new Color[height][width];
		try{
			ExecutorService pool = Executors.newCachedThreadPool();
			for(int i = 0; i < n; i++){
				int fromY = i * height / n;
				int toY = (i + 1) *height/n;
				pool.submit(() -> {
					for (int x = 0; x < width; x++)
						for(int y = fromY; y<toY;y++)
							out[y][x] = f.apply(in[y][x]);
					});
				}
			pool.shutdown();
			pool.awaitTermination(1, TimeUnit.HOURS);
			}
		catch(InterruptedException ex){
			ex.printStackTrace();
		}
		return out;
	}

	//遅延処理だけの時のtoImage
	public Image oldtoImage() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				for (UnaryOperator<Color> f : pendingOperations)
					c = f.apply(c);
				out.getPixelWriter().setColor(x, y, c);
			}
		return out;
	}


	//並列処理と遅延処理の複合
	public Image toImage() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		Color[][] inArray = new Color[height][width];
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				inArray[y][x] = in.getPixelReader().getColor(x, y);
			}
		Color[][] outArray = null;

		//並列処理
		for (UnaryOperator<Color> f : pendingOperations)
			outArray = parallelTransform(inArray,f);
		final Color[][] outColorMap = outArray;

		//Imageへの代入も並列処理
		int n = Runtime.getRuntime().availableProcessors();
		try{
			ExecutorService pool = Executors.newCachedThreadPool();
			for(int i = 0; i < n; i++){
				int fromY = i * height / n;
				int toY = (i + 1) *height/n;
				pool.submit(() -> {
					for (int x = 0; x < width; x++)
						for(int y = fromY; y<toY;y++)
							out.getPixelWriter().setColor(x, y, outColorMap[y][x]);
					});
				}
			pool.shutdown();
			pool.awaitTermination(1, TimeUnit.HOURS);
			}
		catch(InterruptedException ex){
			ex.printStackTrace();
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

		//通常時の処理時間
		long start = System.currentTimeMillis();
		Image latent = LatentImage.from(image).transform(Color::brighter).transform(Color::grayscale).oldtoImage();
		long end = System.currentTimeMillis();
		System.out.println("並列処理なし："+ (end-start)+"msec");

		//並列処理の処理時間
		start = System.currentTimeMillis();
		latent = LatentImage.from(image).transform(Color::brighter).transform(Color::grayscale).toImage();
		end = System.currentTimeMillis();
		System.out.println("並列処理あり："+ (end-start)+"msec");
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

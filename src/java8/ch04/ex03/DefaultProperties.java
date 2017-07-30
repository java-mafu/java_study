package java8.ch04.ex03;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

public class DefaultProperties extends Application {

	private final String defaultText = "default";
	private StringProperty text = null;

	public final StringProperty textProperty() {
		if (text == null)
			text = new SimpleStringProperty(defaultText);

		return text;
	}

	public final void setText(String newValue) throws NullPointerException {
		text.set(newValue);
	}

	public final String getText() {
		return text != null ? text.get() : defaultText;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {


		// DefaultProperty使用
		DefaultProperties dp = new DefaultProperties();
		System.out.println("Default:"+dp.getText());
		dp.textProperty();
		dp.setText("hoge");
		System.out.println("setText:"+dp.getText());
	}

}

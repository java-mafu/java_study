package tetris_0;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ScorePane extends BorderPane{
	private int score;
	private Text scoreBord;
	VBox vbox;

	public ScorePane(){
		super();
		score = 0;
		vbox = new VBox();
		scoreBord = new Text("0");
		vbox.getChildren().add(new Label("score"));
		vbox.getChildren().add(scoreBord);
		this.setCenter(vbox);
	}

	public void addScore(int point){
		score += point;
		scoreBord.setText(Integer.toString(score));
	}

	public int getScore(){
		return score;
	}
}

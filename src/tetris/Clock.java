package tetris;

import java.util.Calendar;

import javafx.scene.text.Text;

public class Clock implements Runnable {
	Text text;
	boolean continueFrag;

	public Clock(Text text) {
		this.text = text;
		continueFrag = true;
	}

	synchronized public void finish(){
		continueFrag = false;
	}

	public void run() {
		int h, m, s;
		while (continueFrag && text != null) {
			h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); // 時を代入
			m = Calendar.getInstance().get(Calendar.MINUTE); // 分を代入
			s = Calendar.getInstance().get(Calendar.SECOND); // 秒を代入
			text.textProperty().set(h + ":" + m + ":" + s);
		}
	}

}
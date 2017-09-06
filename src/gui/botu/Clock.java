package gui.botu;

import java.util.Calendar;

import javafx.scene.text.Text;

public class Clock implements Runnable {
	Text text;

	public Clock(Text text) {
		this.text = text;
	}

	public void run() {
		int h, m, s;
		while (text != null) {
			h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); // 時を代入
			m = Calendar.getInstance().get(Calendar.MINUTE); // 分を代入
			s = Calendar.getInstance().get(Calendar.SECOND); // 秒を代入
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			text.textProperty().set(h + ":" + m + ":" + s);
		}
	}

}
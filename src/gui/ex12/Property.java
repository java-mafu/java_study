package gui.ex12;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.WindowAdapter;

public class Property extends WindowAdapter {


	public void addMenuber(Frame f){
		MenuBar mb = new MenuBar();
		Menu sakura = mb.add(new Menu("Sakura"));
		Menu tomoyo = mb.add(new Menu("Tomoyo" , true));

		sakura.add("絶対大丈夫だよ");
		sakura.add("ほえぇ～～");

		tomoyo.add(new MenuItem("さくらちゃんならきっと出来ますわ"));
		tomoyo.add(new MenuItem("大きな肉まん…"));
		f.setMenuBar(mb);
	}
}
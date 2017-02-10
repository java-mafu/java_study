package gui.ex14;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class DigitalClock extends Frame implements Runnable, ActionListener {


	private static final int WINDOW_WIDTH = 1080;
	private static final int WINDOW_HEIGHT = 800;

	static Thread th;
	static DigitalClock tw;
	int h;
	int m;
	int s;
	Dimension size;
	Dimension insetSize = new Dimension();
	//ダブルバッファリング用インスタンスをコンストラクタで作成
	Image backgroundImage;
	Graphics buffer;

	Property pro;

	//Preferences
	private Preferences prefs;
    private static final String KEY_BY_FT[] = {"point_x_Do_or_Die", "point_y_Do_or_Die"};

    public DigitalClock(){
    	prefs = Preferences.userNodeForPackage(this.getClass());
    }

	public void init(){
	    initClockMenu();
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setLocation(prefs.getInt(KEY_BY_FT[0], 100), prefs.getInt(KEY_BY_FT[1], 100));
		size = getSize();
	    setVisible(true);
    	backgroundImage = createImage(WINDOW_WIDTH, WINDOW_HEIGHT);
    	buffer = backgroundImage.getGraphics();
    	insetSize.setSize(getInsets().left + getInsets().right, getInsets().top + getInsets().bottom);
    	setResizable(false);
	}

	public void initClockMenu(){
		addWindowListener(new MyWindowAdapter());
	    pro = new Property(this);
		MenuBar mb = new MenuBar();
		Menu menu = mb.add(new Menu("property"));
		MenuItem item = menu.add(new MenuItem("property"));
		item.addActionListener(this);
		setMenuBar(mb);
	}

	@Override
	public void run(){
		while(true){
			h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); //時を代入
            m = Calendar.getInstance().get(Calendar.MINUTE);      //分を代入
            s = Calendar.getInstance().get(Calendar.SECOND);       //秒を代入
            repaint();
		}
	}


    public void update(Graphics g){
    	paint(g);
    }

	public void paint(Graphics g) {
		setSize(pro.getWinsize().width, pro.getWinsize().height + insetSize.height);
    	buffer = backgroundImage.getGraphics();
    	buffer.setColor(pro.getGraphicsBackgroundColor());
        buffer.fillRect(0, 0, this.getWidth(), this.getWidth());
    	drawClock(buffer);
    	g.drawImage(backgroundImage, 0, 0, this);
	}

	private void drawClock(Graphics g) {

		g.setColor(pro.getGraphicsFontColor());
		g.setFont(pro.getGraphicsFont().deriveFont(pro.getGraphicsFontSize()));
		g.drawString(h+":"+m+":"+s, 30, (int)pro.getGraphicsFontSize() + getInsets().top);

	}



	public static void main(String[] args) {

	tw = new DigitalClock();
	th = new Thread(tw);
	tw.init();
	th.start();
	}

	class MyWindowAdapter extends WindowAdapter {
	    public void windowClosing(WindowEvent e) {
	    	 try {
	    	prefs.putInt(KEY_BY_FT[0], tw.getLocationOnScreen().x);
	    	prefs.putInt(KEY_BY_FT[1], tw.getLocationOnScreen().y);
	    	prefs.flush();
	    	 } catch (BackingStoreException ex) {
	             ex.printStackTrace();
	         }
	    	System.exit(0);
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "property"){
			pro.setVisible(true);
		}

	}

}

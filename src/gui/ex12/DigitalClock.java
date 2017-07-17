package gui.ex12;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;


public class DigitalClock extends Frame implements Runnable{

	private static final int WINDOW_WIDTH = 80;
	private static final int WINDOW_HEIGHT = 150;
	static int h;
	static int m;
	static int s;
	Dimension size;

	boolean a = true;
	//インスタンス化
	static DigitalClock f = new DigitalClock();
	static Property pro = new Property();
	static Thread th = new Thread(f);
	Calendar now = Calendar.getInstance();
	//ダブルバッファリング用インスタンスをコンストラクタで作成
	Image backgroundImage;
	Graphics buffer;

    public void run(){
    	while(true){
    		h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); //時を代入
              m = Calendar.getInstance().get(Calendar.MINUTE);      //分を代入
              s= Calendar.getInstance().get(Calendar.SECOND);       //秒を代入
              repaint();

    }
    }

    public void init(){

    	size = getSize();
    	backgroundImage = createImage(size.width, size.height);
    	buffer = backgroundImage.getGraphics();
    }

    public void update(Graphics g){
    	paint(g);
    }
    public void paint(Graphics g) {
    	 f.setSize(pro.getWindowSizeX(), pro.getWindowSizeY());
    	backgroundImage = createImage(pro.getWindowSizeX(), pro.getWindowSizeY());
    	buffer = backgroundImage.getGraphics();
    	buffer.setColor(pro.getBackgroundColor());
        buffer.fillRect(0, 0, pro.getWindowSizeX(), pro.getWindowSizeY());
        buffer.translate(0,pro.getWindowSizeY()/2);//(int)(WINDOW_WIDTH/2-MAGNI*60),(int)(WINDOW_HEIGHT/2-MAGNI*10));
    	drawClock(buffer);
    	buffer.translate(0,-pro.getWindowSizeY()/2);//)-(int)(WINDOW_WIDTH/2-MAGNI*60),-(int)(WINDOW_HEIGHT/2-MAGNI*10));
    	g.drawImage(backgroundImage, 0, 0, this);

    }
	private void drawClock(Graphics g) {

		 g.setColor(pro.getFontColor());
		 drawFont(g);

	}


	private void drawFont(Graphics g){
		Font font = pro.getPropertyFont();
		g.setFont(font);
		g.drawString(h+":"+m+":"+s, 30, 40);
	}

class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
       System.exit(0);
    }
}
	public static void main(String[] args) {
		// 時計の表示
	       f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	       f.addWindowListener(f.new MyWindowAdapter());
	       f.setVisible(true);
	       f.init();
	       pro.addMenuber(f);

	       th.start();
	}

}
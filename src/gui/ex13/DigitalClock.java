package gui.ex13;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.Window;
import java.util.Calendar;

public class DigitalClock extends Window implements Runnable {


	private static final int WINDOW_WIDTH = 80;
	private static final int WINDOW_HEIGHT = 150;
	int h;
	int m;
	int s;
	Dimension size;
	//ダブルバッファリング用インスタンスをコンストラクタで作成
	Image backgroundImage;
	Graphics buffer;

	MouseResponse mouse;
	PopupMenu pop;
	ClockPopupMenu cmenu;

	public PopupMenu getPop() {
		return pop;
	}

	public void setPop(PopupMenu pop) {
		this.pop = pop;
	}

	public DigitalClock(){
		this(new Frame());
	}

	public DigitalClock(Frame owner) {
		super(owner);
	}

	public void init(){
	    pop = new PopupMenu();
	    this.add(pop);
	    mouse = new MouseResponse(this, pop);
	    cmenu = new ClockPopupMenu(this, pop);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		size = getSize();
	    setVisible(true);
    	backgroundImage = createImage(WINDOW_WIDTH, WINDOW_HEIGHT);
    	buffer = backgroundImage.getGraphics();
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
		setSize(cmenu.getWindowSizeX(), cmenu.getWindowSizeY());
    	backgroundImage = createImage(cmenu.getWindowSizeX(), cmenu.getWindowSizeY());
    	buffer = backgroundImage.getGraphics();
    	buffer.setColor(cmenu.getBackgroundColor());
        buffer.fillRect(0, 0, cmenu.getWindowSizeX(), cmenu.getWindowSizeY());
        buffer.translate(0,cmenu.getWindowSizeY()/2);
    	drawClock(buffer);
    	buffer.translate(0,-cmenu.getWindowSizeY()/2);
    	g.drawImage(backgroundImage, 0, 0, this);
	}
	
	private void drawClock(Graphics g) {

		 g.setColor(cmenu.getFontColor());
		 drawFont(g);

	}


	private void drawFont(Graphics g){
		Font font = cmenu.getPropertyFont();
		g.setFont(font);
		g.drawString(h+":"+m+":"+s, 30, 40);
	}

	public static void main(String[] args) {
	Thread th;
	DigitalClock tw = new DigitalClock();
	th = new Thread(tw);
	tw.init();
	th.start();
	}

}

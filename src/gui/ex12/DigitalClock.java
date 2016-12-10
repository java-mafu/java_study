package gui.ex12;

import static gui.ex11.NumberSeg.*;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import gui.ex11.NumberData;


public class DigitalClock extends Frame implements Runnable{

	static int h;
	static int m;
	static int s;


	boolean a = true;

	//インスタンス化
	static DigitalClock f = new DigitalClock();
	static Property pro = new Property();
	static Thread th = new Thread(f);
	Calendar now = Calendar.getInstance();

    public void run(){
        while(a==true){
              h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); //時を代入
              m = Calendar.getInstance().get(Calendar.MINUTE);      //分を代入
              s= Calendar.getInstance().get(Calendar.SECOND);       //秒を代入
              repaint();

              try{
            	  Thread.sleep(1000);  //スリープ１秒
              }catch(InterruptedException e){
              }
        }
    }
    public void paint(Graphics g) {
    	drawClock(g);
    }
	private void drawClock(Graphics g) {
		drawOriginFont(g);
	}

	private void drawOriginFont(Graphics g) {
		NumberData.drawNumberGraphics(g, h);
    	g.fillPolygon(tempsegColonHigh[0],tempsegColonHigh[1],4);
    	g.fillPolygon(tempsegColonLow[0],tempsegColonLow[1],4);
		g.translate(MAGNI*3,0);
    	NumberData.drawNumberGraphics(g, m);
    	g.fillPolygon(tempsegColonHigh[0],tempsegColonHigh[1],4);
    	g.fillPolygon(tempsegColonLow[0],tempsegColonLow[1],4);
		g.translate(MAGNI*3,0);
    	NumberData.drawNumberGraphics(g, s);
	}

class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
       System.exit(0);
    }
}
	public static void main(String[] args) {
		// 時計の表示
	       f.setSize(1000, 400);
	       f.addWindowListener(f.new MyWindowAdapter());
	       f.setVisible(true);
	       pro.addMenuber(f);
	       th.start();
	}

}
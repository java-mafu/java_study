package gui.ex11;

import static gui.ex11.NumberSeg.*;

import java.awt.Graphics;
public class NumberData {

	
	public static void drawNumberGraphics(Graphics g, int number){
		if(number >= 100 || number < 0){
			throw new IllegalArgumentException
            ("取得した時間がおかしいです");
		}
		//数字を10の位と一の位に分割
		int num[] = new int[2];
		num[0] = number / 10;//10の位
		num[1] = number % 10;//1の位

		for(int i = 0; i < 2; i++){
			drawBackGround(g);
			switch(num[i]){
			case 0:
				draw0(g);
				break;
			case 1:
				draw1(g);
				break;
			case 2:
				draw2(g);
				break;
			case 3:
				draw3(g);
				break;
			case 4:
				draw4(g);
				break;
			case 5:
				draw5(g);
				break;
			case 6:
				draw6(g);
				break;
			case 7:
				draw7(g);
				break;
			case 8:
				draw8(g);
				break;
			case 9:
				draw9(g);
				break;
			default:
				break;
			}
			g.translate((12+1)*MAGNI,0);
		}
	}
	
	private static void drawBackGround(Graphics g){
		g.drawPolygon(tempsega[0],tempsega[1],6);
		g.drawPolygon(tempsegb[0],tempsegb[1],6);
		g.drawPolygon(tempsegc[0],tempsegc[1],6);
		g.drawPolygon(tempsegd[0],tempsegd[1],6);
		g.drawPolygon(tempsege[0],tempsege[1],6);
		g.drawPolygon(tempsegf[0],tempsegf[1],6);
		g.drawPolygon(tempsegg[0],tempsegg[1],6);

	}

	private static void draw0(Graphics g){
		g.fillPolygon(tempsega[0],tempsega[1],6);
		g.fillPolygon(tempsegb[0],tempsegb[1],6);
		g.fillPolygon(tempsegc[0],tempsegc[1],6);
		g.fillPolygon(tempsegd[0],tempsegd[1],6);
		g.fillPolygon(tempsege[0],tempsege[1],6);
		g.fillPolygon(tempsegf[0],tempsegf[1],6);
	}
	private static void draw1(Graphics g){
		g.fillPolygon(tempsegb[0],tempsegb[1],6);
		g.fillPolygon(tempsegc[0],tempsegc[1],6);
	}
	private static void draw2(Graphics g){
		g.fillPolygon(tempsega[0],tempsega[1],6);
		g.fillPolygon(tempsegb[0],tempsegb[1],6);
		g.fillPolygon(tempsegd[0],tempsegd[1],6);
		g.fillPolygon(tempsege[0],tempsege[1],6);
		g.fillPolygon(tempsegg[0],tempsegg[1],6);
	}
	private static void draw3(Graphics g){
		g.fillPolygon(tempsega[0],tempsega[1],6);
		g.fillPolygon(tempsegb[0],tempsegb[1],6);
		g.fillPolygon(tempsegc[0],tempsegc[1],6);
		g.fillPolygon(tempsegd[0],tempsegd[1],6);
		g.fillPolygon(tempsegg[0],tempsegg[1],6);
	}
	private static void draw4(Graphics g){
		g.fillPolygon(tempsegb[0],tempsegb[1],6);
		g.fillPolygon(tempsegc[0],tempsegc[1],6);
		g.fillPolygon(tempsegf[0],tempsegf[1],6);
		g.fillPolygon(tempsegg[0],tempsegg[1],6);
	}
	private static void draw5(Graphics g){
		g.fillPolygon(tempsega[0],tempsega[1],6);
		g.fillPolygon(tempsegc[0],tempsegc[1],6);
		g.fillPolygon(tempsegd[0],tempsegd[1],6);
		g.fillPolygon(tempsegf[0],tempsegf[1],6);
		g.fillPolygon(tempsegg[0],tempsegg[1],6);
	}
	private static void draw6(Graphics g){
		g.fillPolygon(tempsega[0],tempsega[1],6);
		g.fillPolygon(tempsegc[0],tempsegc[1],6);
		g.fillPolygon(tempsegd[0],tempsegd[1],6);
		g.fillPolygon(tempsege[0],tempsege[1],6);
		g.fillPolygon(tempsegf[0],tempsegf[1],6);
		g.fillPolygon(tempsegg[0],tempsegg[1],6);
	}
	private static void draw7(Graphics g){
		g.fillPolygon(tempsega[0],tempsega[1],6);
		g.fillPolygon(tempsegb[0],tempsegb[1],6);
		g.fillPolygon(tempsegc[0],tempsegc[1],6);
		g.fillPolygon(tempsegf[0],tempsegf[1],6);
	}
	private static void draw8(Graphics g){
		g.fillPolygon(tempsega[0],tempsega[1],6);
		g.fillPolygon(tempsegb[0],tempsegb[1],6);
		g.fillPolygon(tempsegc[0],tempsegc[1],6);
		g.fillPolygon(tempsegd[0],tempsegd[1],6);
		g.fillPolygon(tempsege[0],tempsege[1],6);
		g.fillPolygon(tempsegf[0],tempsegf[1],6);
		g.fillPolygon(tempsegg[0],tempsegg[1],6);

	}
	private static void draw9(Graphics g){
		g.fillPolygon(tempsega[0],tempsega[1],6);
		g.fillPolygon(tempsegb[0],tempsegb[1],6);
		g.fillPolygon(tempsegc[0],tempsegc[1],6);
		g.fillPolygon(tempsegd[0],tempsegd[1],6);
		g.fillPolygon(tempsegf[0],tempsegf[1],6);
		g.fillPolygon(tempsegg[0],tempsegg[1],6);
	}
}
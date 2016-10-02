package jpl.ch01.ex08;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	final double TESTX = 5.1;
	final double TESTY = 10.5;


	@Test
	public void test() {
		Point inputobj = new Point();//引数用オブジェクト
		Point outputobj = new Point();//別オブジェクトを引数として座標を更新するオブジェクト
		inputobj.move(TESTX, TESTY);//x,y座標に値を設定

		outputobj.setPoint(inputobj);//テストしたいプログラム
		assertTrue("x座標の代入失敗",TESTX==outputobj.x);
		assertTrue("y座標の代入失敗",TESTY==outputobj.y);
	}

}

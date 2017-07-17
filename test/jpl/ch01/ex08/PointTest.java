package jpl.ch01.ex08;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import org.junit.Test;
import static java.lang.Math.*;

public class PointTest {

	final double TESTX = 5.1;
	final double TESTY = 10.5;

	@Test
	public void testClear() {
		Point inputobj = new Point();//引数用オブジェクト
		inputobj.clear();
		assertThat(inputobj.x, equalTo(0.0));
		assertThat(inputobj.y, equalTo(0.0));		
	}

	@Test
	public void testDistance() {
		double x = 7.3;
		double y = 8.1;
		Point point1 = new Point(x,y);
		Point point2 = new Point(TESTX,TESTY);
		assertThat(point1.distance(point2), equalTo(sqrt(pow(x - TESTX, 2) + pow(y - TESTY, 2))));
	}

	@Test
	public void testMove() {
		Point inputobj = new Point();//引数用オブジェクト
		inputobj.move(TESTX, TESTY);//x,y座標に値を設定
		assertThat(TESTX, equalTo(inputobj.x));
		assertThat(TESTY, equalTo(inputobj.y));
		
	}

	@Test
	public void testSetPoint() {
		Point inputobj = new Point(TESTX, TESTY);//引数用オブジェクト
		Point outputobj = new Point();//別オブジェクトを引数として座標を更新するオブジェクト
		outputobj.setPoint(inputobj);//テストしたいプログラム
		assertThat(TESTX, equalTo(outputobj.x));
		assertThat(TESTY, equalTo(outputobj.y));
	}

}

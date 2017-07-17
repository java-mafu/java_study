package jpl.ch09.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

public class PascalTriangleTest {

	/*計算した配列の特定の要素にアクセスしたときに，
	結果があっているのかを確認する*/
	@Test
	public void testCalc() {
		int[][] array = {{1},
				{1,1},
				{1,2,1},
				{1,3,3,1},
				{1,4,6,4,1},
				{1,5,10,10,5,1},
				{1,6,15,20,15,6,1}};
		PascalTriangle pt = new PascalTriangle();

		//計算結果があっているかのテスト(きりがないので6まで)
		assertArrayEquals(pt.getPascalTriangle(0),array[0]);
		assertArrayEquals(pt.getPascalTriangle(1),array[1]);
		assertArrayEquals(pt.getPascalTriangle(2),array[2]);
		assertArrayEquals(pt.getPascalTriangle(3),array[3]);
		assertArrayEquals(pt.getPascalTriangle(4),array[4]);
		assertArrayEquals(pt.getPascalTriangle(5),array[5]);
		assertArrayEquals(pt.getPascalTriangle(6),array[6]);

	}

	@Test
	public void testGetPascalTriangle() {
		PascalTriangle pt = new PascalTriangle();

		//配列の大きさ以上の領域にアクセスしたときに処理が止まらないかのテスト
		assertNull(pt.getPascalTriangle(12));
		assertEquals(pt.getPascalTriangle(10,150), 0);
	}

}

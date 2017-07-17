package jpl.ch01.ex14;

import static org.junit.Assert.*;

import org.junit.Test;

public class WalkmanPhoneTest {

	@Test
	public void test() {
		WalkmanPhone player = new WalkmanPhone();
		//電話できるかのテスト
		//portが両方挿入されているときは成功
		assertFalse(player.call());
		
		player.connectPort();
		assertFalse(player.call());
		player.disconnectPort();
		
		player.connectSecondPort();
		assertFalse(player.call());
		
		player.connectPort();
		assertTrue(player.call());
	}

}

package jpl.ch01.ex14;

import static org.junit.Assert.*;

import org.junit.Test;

public class WalkmanTest {

	Walkman player = new Walkman();
	@Test
	public void connecttest() {
		player.connectPort();
		assertTrue(player.getPort());
	}

	@Test
	public void discpnnectTest(){
		player.disconnectPort();
		assertFalse(player.getPort());
	}

	@Test
	public void listenMusicTest(){
		//音楽を聞けるかのテスト
		//portが挿入されているときは成功
		player.connectPort();
		assertTrue(player.listenMusic());
		//portが挿入されていないときは失敗
		player.disconnectPort();
		assertFalse(player.listenMusic());
		}
}
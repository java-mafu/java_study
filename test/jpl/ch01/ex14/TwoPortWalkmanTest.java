package jpl.ch01.ex14;

import static org.junit.Assert.*;

import org.junit.Test;

public class TwoPortWalkmanTest {

	@Test
	public void connecttest() {
		TwoPortWalkman player = new TwoPortWalkman();
		player.connectSecondPort();
		assertTrue(player.getSecondPort());
	}

	@Test
	public void discpnnectTest(){
		TwoPortWalkman player = new TwoPortWalkman();
		player.disconnectSecondPort();
		assertFalse(player.getSecondPort());
	}

	@Test
	public void listenMusicTest(){
		TwoPortWalkman player = new TwoPortWalkman();
		//音楽を聞けるかのテスト
		//portが片方挿入されているときは成功
		player.connectPort();
		assertTrue(player.listenMusic());
		player.connectSecondPort();
		assertTrue(player.listenMusic());
		player.disconnectPort();
		assertTrue(player.listenMusic());
		//portが挿入されていないときは失敗
		player.disconnectSecondPort();
		assertFalse(player.listenMusic());
		}
}
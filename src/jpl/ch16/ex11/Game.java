package jpl.ch16.ex11;

import java.util.LinkedList;
import java.util.Queue;


/**丁半当てゲーム
 * 当たれば掛け金倍*/
public class Game {

	static Queue<String> playerList = new LinkedList<>();
	private long score;
	static String playerName[] = {"PositivePlayer",
			"NegativePlayer",
			"PositivePlayer",
			"NegativePlayer",
			"PositivePlayer",
			"NegativePlayer"
			};

	public static void main(String[] args) {
		String name;
		for(String str : playerName)
			playerList.offer(str);

		while ((name = getNextPlayer()) != null){
			try {
				PlayerLoader loader = new PlayerLoader();
				Class<? extends Player> classOf =
						loader.loadClass(name).asSubclass(Player.class);
				Player player = classOf.newInstance();
				Game game = new Game();
				player.play(game);
				game.reportScore(name);
			} catch (Exception e) {
				reportException(name, e);
			}
		}
	}

	Game(){
		score = 0;
	}

	private static void reportException(String name, Exception e) {
		System.out.println(name + " PlayLoader failed");

	}

	private void reportScore(String name) {
		System.out.println(name + " score:" + score);
	}

	private static String getNextPlayer() {
		return playerList.poll();
	}


	public long startGame(long bet, boolean expect){
		long score;

		boolean cyohan = Math.random() < 0.5 ? true : false;

		if(expect == cyohan)
			score = bet;
		else
			score = -bet;

		this.score += score;
		return score;
	}


}

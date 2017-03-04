package jpl.ch16.ex11;

public class PositivePlayer implements Player{

	@Override
	public void play(Game game) {
		long bet = 1000;
		for(int i = 0; i < 10; i++)
			if(game.startGame(bet, true) > 0)
				bet = bet*2;
	}
}

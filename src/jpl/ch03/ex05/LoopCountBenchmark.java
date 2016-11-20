package jpl.ch03.ex05;

//あらかじめセットされた回数のforloopを回した時にかかる時間を測定するベンチマーク
class LoopCountBenchmark extends Benchmark {

	private int loopcount = 0;
	private long nanotime = 0;

	@Override
	void benchmark() {

		long start = System.nanoTime();
		for(int i = 0; i < loopcount; i++){
		}
		nanotime = System.nanoTime() - start;
	}

	//loop回数をセットし，時間を図る
	final long loopCount(int loopcount){
		this.loopcount = loopcount;
		benchmark();
		return nanotime;
	}

	public static void main(String[] args) {
		int loopcount = 10000;
		long time = new LoopCountBenchmark().loopCount(loopcount);
		System.out.println(loopcount + " methods in " +
									time + "nanosecond");
	}

}

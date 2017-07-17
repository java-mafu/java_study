package jpl.ch03.ex05;

class MethodBenchmark extends Benchmark {

	@Override
	void benchmark() {
	}

	public static void main(String[] args) {
		int count = Integer.parseInt(args[0]);
		long time = new MethodBenchmark().repeat(count);
		System.out.println(count + " methods in " +
									time + "nanosecond");
	}

}

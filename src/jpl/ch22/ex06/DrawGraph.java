package jpl.ch22.ex06;

import java.util.Random;

public class DrawGraph {

	// 標準偏差
	static final double SD = 1.0;
	// グラフの最大"*"数
	static final int MAXPOINT = 40;

	public static String showGraph(double[] array) {
		return showGraph(array, 6);
	}

	public static String showGraph(double[] array, int samplingNum) {
		String str = "";
		double minValue = -3 * SD;
		int[] graphs = new int[samplingNum];
		double samplingWidth[] = new double[samplingNum];
		for (int i = 0; i < samplingNum; i++) {
			graphs[i] = 0;
			samplingWidth[i] = minValue + (SD * 6.0 / samplingNum) * i;
		}
		for (double d : array) {
			for (int i = 0; i < samplingNum - 1; i++) {
				if (samplingWidth[i] <= d && samplingWidth[i + 1] > d) {
					graphs[i]++;
					break;
				}
			}
		}
		int maxSum = 0;
		for (int i = 0; i < samplingNum; i++) {
			maxSum = Integer.max(maxSum, graphs[i]);
		}
		for (int i = 0; i < samplingNum; i++) {
			for (int j = 0; j < MAXPOINT * graphs[i] / maxSum; j++)
				str += "*";
			str += "\n";
		}
		return str;
	}

	public static void main(String[] args) {
		double[] data = new double[10000];
		Random ran = new Random();
		for (int i = 0; i < data.length; i++)
			data[i] = ran.nextGaussian();
		String result = showGraph(data, 40);
		System.out.println(result);
	}

}

package jpl.ch22.ex05;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomDiceRoll {

	// 確率分布を格納するMap
	Map<Integer, Double> distMap = new HashMap<Integer, Double>();
	Map<Integer, Double> randomMap = new HashMap<Integer, Double>();

	public RandomDiceRoll(int diceNum, int rollNum) {
		calcDiceSumDistribution(diceNum);
		randomDiceSumDistribution(diceNum, rollNum);
	}

	public String getDistMap() {
		return distMap.toString();
	}

	public String getRandomMap() {
		return randomMap.toString();
	}

	public void showResultList() {
		System.out.printf("%2s|%4s|%4s|%5s\n", "合計", "理論値", "実測値", "誤差");
		for (int key : distMap.keySet()) {
			System.out.printf("%4d|%.5f|%.5f|%.5f\n", key, distMap.get(key), randomMap.get(key), calcErr(key));
		}
	}

	public double calcErr(int key) {
		double theoreticalValue = distMap.get(key);
		double randomValue = randomMap.get(key);
		return Math.abs(theoreticalValue - randomValue) / theoreticalValue;
	}

	private void calcDiceSumDistribution(int diceNum) {
		Map<Integer, Integer> prevMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < 6; i++)
			prevMap.put(i + 1, 1);
		for (int dices = 1; dices < diceNum; dices++) {
			Map<Integer, Integer> currMap = new HashMap<Integer, Integer>();
			for (int i = 0; i < (dices + 1) * 5 + 1; i++)
				currMap.put(i + dices + 1, 0);
			for (int prevSum : prevMap.keySet()) {
				for (int i = 0; i < 6; i++) {
					int tmpSum = prevSum + i + 1;
					currMap.put(tmpSum, currMap.get(tmpSum) + prevMap.get(prevSum));
				}
			}
			prevMap = currMap;
		}
		for (int prevSum : prevMap.keySet()) {
			distMap.put(prevSum, (double) prevMap.get(prevSum) / Math.pow(6, diceNum));
		}
	}

	private void randomDiceSumDistribution(int diceNum, int rollNum) {
		Map<Integer, Integer> tmpMap = new HashMap<Integer, Integer>();
		Random ran = new Random();
		for (int i = diceNum; i < diceNum * 6 + 1; i++)
			tmpMap.put(i, 0);
		for (int i = 0; i < rollNum; i++) {
			int tmpSum = 0;
			for (int j = 0; j < diceNum; j++)
				tmpSum += ran.nextInt(6) + 1;
			tmpMap.put(tmpSum, tmpMap.get(tmpSum) + 1);
		}
		for (int tmpSum : tmpMap.keySet()) {
			randomMap.put(tmpSum, (double) tmpMap.get(tmpSum) / rollNum);
		}
	}

	public static void main(String[] args) {
		RandomDiceRoll diceCalc = new RandomDiceRoll(3, 100000);
		diceCalc.showResultList();
	}

}

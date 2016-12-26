package jpl.ch09.ex02;

public class TrueBitCounter {
	//合計の算出で，+=を使用している．ビット演算子だけでは思いつかなかった
	public static int countBit(int input){
		int result = 0;

		while(input!=0){
			result += input & 1;
			input >>>= 1;
		}
		return result;
	}
}

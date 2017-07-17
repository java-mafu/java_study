package jpl.ch22.ex03;

import java.util.BitSet;
import java.util.HashMap;

public class BitSetToHashMap {

	private HashMap<Byte, BitSet> map;
	private BitSet[] used;

	public BitSetToHashMap(String str) {
		map = new HashMap<Byte, BitSet>();
		used = new BitSet[0xFF];
		for (byte b = 0; b < Byte.MAX_VALUE; b++)
			used[b] = new BitSet();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			byte keyByte = (byte) ((c & 0xFF00) >> 8);
			used[keyByte].set(c & 0xFF);
		}
		for (byte b = 0; b < Byte.MAX_VALUE; b++)
			map.put(b, used[b]);
	}

	public String toString() {
		String desc = "[";
		for (byte b = 0; b < Byte.MAX_VALUE; b++) {
			BitSet bs = map.get(Byte.valueOf(b));
			if (bs == null)
				continue;
			for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i + 1)) {
				char c = (char) b;
				c = (char) ((c << 8) + i);
				desc += c;
			}
		}
		return desc + "]";
	}

	public static void main(String[] args) {
		String str = "テスト 777666555 ほげほげ hogehoge";
		System.out.println(new BitSetToHashMap(str));
	}

}

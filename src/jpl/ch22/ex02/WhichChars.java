package jpl.ch22.ex02;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;

public class WhichChars {
	private BitSet used = new BitSet();
	private HashSet<Character> charHashSet = new HashSet<Character>();
	public WhichChars(String str) {
		for (int i = 0; i < str.length(); i++){
			charHashSet.add(str.charAt(i));
			used.set(Character.hashCode(str.charAt(i)));
		}
	}

	public String toString() {
		String desc = "[";
		for (Iterator<Character> it = charHashSet.iterator();it.hasNext();it.remove()) {
			desc += (char) used.nextSetBit(it.next());
		}
		return desc + "]";
	}

	public static void main(String[] args){
		String str = "Testing 1 2 3";
		System.out.println(new WhichChars(str));
	}
}

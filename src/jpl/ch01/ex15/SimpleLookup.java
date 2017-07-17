package jpl.ch01.ex15;

class SimpleLookup implements Lookup {
	protected String[] names;
	protected Object[] values;

	@Override
	public Object find(String name) {
		// TODO 自動生成されたメソッド・スタブ
		for ( int i = 0; i < names.length; i++) {
			if (names[i].equals(name))
				return values[i];
		}
		return null; //見つからなかった
	}
}

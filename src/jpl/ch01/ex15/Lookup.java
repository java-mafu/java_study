package jpl.ch01.ex15;

interface Lookup {
	/*nameと関連つけられた値を返す*/
	/*そのような値がなければnullを返す*/
	Object find(String name);
}

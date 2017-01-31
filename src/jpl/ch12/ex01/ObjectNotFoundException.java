package jpl.ch12.ex01;

/**nullを返すだけだと，プログラム自体は先に進んでしまうが，
 * 例外をスローすると処理が停止するため，予期せぬ事態が起きたということが分かりやすいため，
 * 例外を使用するのが好ましい．
 * 例外に追加データを増やすのであれば，findで探索できる候補があれば，正しい処理に書き換えやすい．*/
public class ObjectNotFoundException extends Exception{
	public final String notFindName;

	public ObjectNotFoundException(String name){
		super("No LinkedList named \"" + name + "\"found");
		notFindName = name;
	}

}

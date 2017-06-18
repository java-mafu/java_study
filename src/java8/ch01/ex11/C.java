package java8.ch01.ex11;

public class C {

	public static void main(String[] args) {
		C cclass = new C();
		cclass.new Cabab().f();
		cclass.new Cabde().f();
		cclass.new Cabst().f();
		cclass.new Cdeab().f();
		cclass.new Cdede().f();
		cclass.new Cdest().f();
		cclass.new Cstab().f();
		cclass.new Cstde().f();
		cclass.new Sab().f();
		cclass.new Sde().f();
		cclass.new Sst().f();
	}

	// オーバーライドしないとエラー
	public class Cabab implements I.Iab, J.Jab {
		@Override
		public void f() {
			System.out.println("ab*ab");
		}
	}

	// オーバーライドしないとエラー
	public class Cabde implements I.Iab, J.Jde {
		@Override
		public void f() {
			System.out.println("ab*de");
		}
	}

	// abstractにするとエラーは消えるが，このままでは使用不可．
	// オーバライドすると使用できる
	public class Cabst implements I.Iab, J.Jst {
		@Override
		public void f() {
			System.out.println("ab*st");
		}
	}

	// オーバーライドしないとエラー
	public class Cdeab implements I.Ide, J.Jab {
		@Override
		public void f() {
			System.out.println("de*ab");
		}
	}

	// IとJのどちらかのデフォルトメソッドを使用するようにオーバーライドするか，
	// 単にオーバーライドしないとエラー
	public class Cdede implements I.Ide, J.Jde {

		@Override
		public void f() {
			// TODO 自動生成されたメソッド・スタブ
			I.Ide.super.f();
		}

	}

	// オーバーライドなしでもエラーなし
	// 実行するとIdeのメソッドが実行される
	public class Cdest implements I.Ide, J.Jst {

	}

	// abstractにするとエラーは消えるが，このままでは使用不可．
	// オーバライドすると使用できる
	public class Cstab implements I.Ist, J.Jab {
		@Override
		public void f() {
			System.out.println("st*ab");
		}
	}

	// オーバーライドなしでもエラーなし
	// 実行するとJdeのメソッドが実行される
	public class Cstde implements I.Ist, J.Jde {

	}

	/** コンパイルエラー */
	// public class Cstst implements I.Ist, J.Jst {
	// @Override
	// public void f() {
	// System.out.println("st*st");
	// }
	// }

	// スーパークラスが優先される
	public class Sab extends S implements I.Iab {

	}

	// オーバーライドする際に，インターフェースを指定すると優先
	// オーバーライド無しならクラス優先
	public class Sde extends S implements I.Ide {
		@Override
		public void f() {
			I.Ide.super.f();
		}
	}

	// 同上
	public class Sst extends S implements I.Ist {
		@Override
		public void f() {
			I.Ist.f();
		}
	}

}

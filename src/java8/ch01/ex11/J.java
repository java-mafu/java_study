package java8.ch01.ex11;

public interface J {

	public interface Jab {
		void f();
	}

	public interface Jde {
		default void f() {
			System.out.println("default J");
		};
	}

	public interface Jst {
		static void f() {
			System.out.println("static J");
		}
	}
}

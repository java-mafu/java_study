package java8.ch01.ex11;

public interface I {

	public interface Iab {
		void f();
	}

	public interface Ide {
		default void f() {
			System.out.println("default I");
		};
	}

	public interface Ist {
		static void f() {
			System.out.println("static I");
		}
	}
}

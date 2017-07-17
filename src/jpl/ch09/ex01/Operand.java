package jpl.ch09.ex01;

public class Operand {
	//四則演算するだけのため，メソッドなし．そのためテストコードもなし
	public static void main(String[] args) {
		double x = Double.POSITIVE_INFINITY;
		double y = Double.NEGATIVE_INFINITY;
		double z;//結果格納

		System.out.println("P:正の無限大，N:負の無限大");
		z = x + x;
		System.out.println("P+P="+z);

		z = x + y;
		System.out.println("P+N="+z);

		z = y + y;
		System.out.println("N+N="+z);

		z = x - x;
		System.out.println("P-P="+z);

		z = x - y;
		System.out.println("P-N="+z);

		z = y - x;
		System.out.println("N-P="+z);

		z = y - y;
		System.out.println("N-N="+z);

		z = x * x;
		System.out.println("P*P="+z);

		z = x * y;
		System.out.println("P*N="+z);

		z = y * y;
		System.out.println("N*N="+z);

		z = x / x;
		System.out.println("P/P="+z);

		z = x / y;
		System.out.println("P/N="+z);

		z = y / y;
		System.out.println("N/N="+z);
	}

}

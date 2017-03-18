package interpret;

public class SampleClass {
	public int a = 1;
	public int b = 2;
	public int c = 3;

	/*引数0 戻り値あり*/
	public int getA(){
		return a;
	}

	/*引数1 戻り値なし*/
	public void setA(int a){
		this.a = a;
	}

	/*引数2 戻り値なし*/
	public void setAB(int a, int b){
		this.a = a;
		this.b = b;
	}


	/*引数0 戻り値配列*/
	public int[] sapleReturnArray(){
		int[] e = {a,b,c};
		return e;
	}
}

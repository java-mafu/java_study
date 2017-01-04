package jpl.ch10.ex04;

/**
 * ch09.ex03で使用したfor文をwhile文に変更
 *
 * do-whileを使っても修正は可能である．
 *
 * for文を使う場合indexを加算していくことが多いが，
 * while文は条件を満たす間はループするという使い方が多い．
 * そのため，今回はあまり適さないと考えた．
 * また，do-while文では，必ず一度は処理が実行されるため，
 * for文から修正する場合は，indexの数に注意する必要がある．
 *
 *
 * @author fumihiro
 *
 */



public class PascalTriangle {
	private int pascalnest = 12;//ネスト．外部からいじることを考えて，定数にはしない
	private int[][] pascalsTriangle;

	//インスタンス生成時点で計算する．
	public PascalTriangle(){
		calcPascalTriangle();
	}

	public final int getNest(){
		return pascalnest;
	}

	//nestが変更されたら再計算
	public final void setNest(int nest){
		pascalnest = nest;
		calcPascalTriangle();
	}

	//特定の要素を取り出すためのゲッター(不正アクセス時は0を返す)
	public final int getPascalTriangle(int nest, int element){
		if(nest<pascalnest && element<pascalsTriangle[nest].length)
			return pascalsTriangle[nest][element];
		else
			return 0;
	}

	public final int[] getPascalTriangle(int nest){
		if(nest<pascalnest)
			return pascalsTriangle[nest];
		else
			return null;
	}

	public final int[][] getPascalTriangle(){
		return pascalsTriangle;
	}

	private void calcPascalTriangle(){
		pascalsTriangle = new int[pascalnest][];
		for(int i = 0;i< pascalsTriangle.length;i++){
			pascalsTriangle[i] = new int[i+1];
			pascalsTriangle[i][0] = 1;
			int j = 1;
			//ここを++演算子を使用したwhile文に変更したが，あまりよくなった気がしない
			while(j<i){
				pascalsTriangle[i][j]
						= pascalsTriangle[i-1][j-1] + pascalsTriangle[i-1][j];
				j++;
			}
			pascalsTriangle[i][pascalsTriangle[i].length-1] = 1;
		}
	}

	public void triangleOutput(){
		for(int i= 0; i< pascalsTriangle.length; i++){
			for(int j = 0; j < pascalsTriangle[i].length;j++){
				System.out.print(pascalsTriangle[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		PascalTriangle pt = new PascalTriangle();
		pt.triangleOutput();
		pt.setNest(15);
		pt.triangleOutput();
	}

}

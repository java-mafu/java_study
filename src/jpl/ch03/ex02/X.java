package jpl.ch03.ex02;

class X {
	protected int xMask = 0x00ff;
	protected int fullMask;

	public X(){
		System.out.printf("Xのフィールド初期化(x,full) = (0x%04x, 0x%04x)%n", xMask, fullMask);
		fullMask = xMask;
		System.out.printf("Xのコンストラクタ実行(x,full) = (0x%04x, 0x%04x)%n", xMask, fullMask);
	}

	public int mask(int orig){
		return (orig & fullMask);
	}

}

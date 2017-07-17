package jpl.ch03.ex02;

class Y extends X{
	protected int yMask = 0xff00;

	public Y(){
		System.out.printf("Yのフィールド初期化(x,y,full) = (0x%04x, 0x%04x, 0x%04x)%n", xMask, yMask, fullMask);
		fullMask |= yMask;
		System.out.printf("Yのコンストラクタ実行(x,y,full) = (0x%04x, 0x%04x, 0x%04x)%n", xMask, yMask, fullMask);

	}

	public static void main(String[] args) {
		Y yObject = new Y();
	}
}

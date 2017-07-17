package jpl.ch04.ex01;

interface EnergySource {
	//空ならtrueが帰る関数
	boolean empty();
	void supply(int addenergy);
}

package jpl.ch05.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BankAccountTest {

	@Test
	public void testDeposit() {
		long money = 100;
		BankAccount ba = new BankAccount();
		ba.deposit(money);
		assertThat(ba.getBalace(), equalTo(money));
		assertThat(ba.getAct().getAct(), equalTo("deposit"));
		assertThat(ba.getAct().getAmount(), equalTo(money));
	}

	@Test
	public void testWithdraw() {
		long money = 100;
		BankAccount ba = new BankAccount();
		ba.withdraw(money);
		assertThat(ba.getBalace(), equalTo(0-money));
		assertThat(ba.getAct().getAct(), equalTo("withdraw"));
		assertThat(ba.getAct().getAmount(), equalTo(money));
	}

}

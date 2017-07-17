package jpl.ch24.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class GlobalHelloTest {
	@Test
	public void en_AUTest() {
		Locale.setDefault(new Locale("en", "AU"));
		ResourceBundle res = ResourceBundle.getBundle("jpl.ch24.ex01.GlobalRes");
		String msg = res.getString(GlobalRes.GOODBYE);
		assertThat(msg, equalTo("Goodbye"));
		msg = res.getString(GlobalRes.HELLO);
		assertThat(msg, equalTo("G'day"));
	}

	@Test
	public void en_CANADATest() {
		Locale.setDefault(new Locale("en", "CANADA"));
		ResourceBundle res = ResourceBundle.getBundle("jpl.ch24.ex01.GlobalRes");
		String msg = res.getString(GlobalRes.GOODBYE);
		assertThat(msg, equalTo("CanadaGoodbye"));
		msg = res.getString(GlobalRes.HELLO);
		assertThat(msg, equalTo("CanadaHello"));
	}

	@Test
	public void enTest() {
		Locale.setDefault(new Locale("en"));
		ResourceBundle res = ResourceBundle.getBundle("jpl.ch24.ex01.GlobalRes");
		String msg = res.getString(GlobalRes.GOODBYE);
		assertThat(msg, equalTo("Goodbye"));
		msg = res.getString(GlobalRes.HELLO);
		assertThat(msg, equalTo("Hello"));
	}

	@Test
	public void frTest() {
		Locale.setDefault(new Locale("fr"));
		ResourceBundle res = ResourceBundle.getBundle("jpl.ch24.ex01.GlobalRes");
		String msg = res.getString(GlobalRes.GOODBYE);
		assertThat(msg, equalTo("AuRevoir"));
		msg = res.getString(GlobalRes.HELLO);
		assertThat(msg, equalTo("Bonjour"));
	}

	@Test
	public void Test() {
		Locale.setDefault(Locale.JAPANESE);
		ResourceBundle res = ResourceBundle.getBundle("jpl.ch24.ex01.GlobalRes");
		String msg = res.getString(GlobalRes.GOODBYE);
		assertThat(msg, equalTo("Ciao"));
		msg = res.getString(GlobalRes.HELLO);
		assertThat(msg, equalTo("Ciao"));
	}
}

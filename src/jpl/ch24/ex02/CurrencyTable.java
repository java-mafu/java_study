package jpl.ch24.ex02;

import java.util.Currency;
import java.util.Locale;

public class CurrencyTable {

	public static void main(String[] args) {
		String[] localeName = { "JP", "GB", "AU", "IN", "fi", "US" };
		Locale[] locales = new Locale[6];
			locales[0] = new Locale("jp", "JP");
			locales[1] = new Locale("en", "GB");
			locales[2] = new Locale("en", "AU");
			locales[3] = new Locale("en", "IN");
			locales[4] = new Locale("fi", "FI");
			locales[5] = new Locale("en", "US");
			System.out.println("\t\tJP\tGB\tAU\tIN\tfr\tUS");
		System.out.println("------------------------------------------");
		for (int i = 0; i < 6; i++) {
			System.out.print("in" + localeName[i] + "|\t");
			for (int j = 0; j < 6; j++) {
				System.out.print(Currency.getInstance(locales[i]).getSymbol(locales[j])+"\t");
			}
			System.out.println();
			System.out.println("------------------------------------------");
		}

	}

}

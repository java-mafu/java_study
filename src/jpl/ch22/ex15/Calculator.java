package jpl.ch22.ex15;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

public class Calculator {

	public static void main(String[] args) throws Exception {

		System.out.println("ポーランド記法で計算式を入力してください（メソッドは名前だけでOK）");
		Scanner in = new Scanner(System.in);
		String[] inputs = in.nextLine().split(" ");
		System.out.println(analysisStrings(inputs));
	}
	
	public static Double analysisStrings(String[] inputs) throws Exception{
		StackArrayList<Double> nums = new StackArrayList<Double>();
		for (String str : inputs) {
			Double result;
			// 数字ならListに追加
			try {
				nums.push(Double.parseDouble(str));
			} catch (NumberFormatException e) {
				// 数字じゃなかった
				result = calculation(nums, str);
				if(result == null)
					System.out.println("式がおかしいです");
				try {
					nums.push(result);
				} catch (NumberFormatException e2) {
					// 計算結果がおかしい
					break;
				}
			}
		}
		return nums.pop();
	}

	public static Double calculation(StackArrayList<Double> nums, String str) throws Exception {
		double result = 0;
		if (nums.empty())
			return null;
		switch (str) {
		case "+":
			return nums.pop() + nums.pop();
		case "-":
			return nums.pop() - nums.pop();
		case "*":
			return nums.pop() * nums.pop();
		case "/":
			return nums.pop() / nums.pop();
		default:
			try {
				result = Double.parseDouble(searchMethod(str, nums).toString());
				return result;
			} catch (EmptyStackException e0) {
				return null;
			}
		}
	}

	private static Object searchMethod(String str, StackArrayList<Double> nums) throws Exception {
		Class<?>[] cd = { double.class, double.class };
		Class<?>[] cl = { long.class, long.class };

		Method method;
		Object[] objs = new Object[2];
		try {
			method = Math.class.getMethod(str, double.class);
			return method.invoke(null, nums.pop());
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// double型引数1個では見つからなかった
		}
		try {
			method = Math.class.getMethod(str, cd);
			for (int i = 0; i < objs.length; i++)
				objs[i] = nums.pop();
			return method.invoke(null, objs);
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// double型引数2個では見つからなかった
		}
		try {
			method = Math.class.getMethod(str, long.class);
			return method.invoke(null, (long)(double)nums.pop());
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// long型引数1個では見つからなかった
		}
		try {
			method = Math.class.getMethod(str, cl);
			for (int i = 0; i < objs.length; i++){
				objs[i] = (long)(double)nums.pop();				
			}
			return method.invoke(null, objs);
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// long型引数2個でも見つからなかった
		}
		throw new Exception();
	}

}

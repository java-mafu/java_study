package jpl.ch16.ex05;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

class ClassContents {

	public static void main(String[] args) {
		try{
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			printMembers(c.getFields());
			printMembers(c.getConstructors());
			printMembers(c.getMethods());
			printPrivateMembers(c.getDeclaredFields());
			printPrivateMembers(c.getDeclaredConstructors());
			printPrivateMembers(c.getDeclaredMethods());
			printAnnotation(c.getAnnotations());
		} catch(ClassNotFoundException e){
			System.out.println("unnown class: " + args[0]);
		}
	}

	private static void printMembers(Member[] mems){
		for (Member m : mems){
			if (m.getDeclaringClass() == Object.class)
				continue;
			String decl = m.toString();
			System.out.print(" ");
			System.out.println(strip(decl, "java.lang."));
		}
	}

	private static void printPrivateMembers(Member[] mems){
		ArrayList<Member> privateMems = new ArrayList<Member>();;
		for(Member m : mems){
			if (m.getDeclaringClass() == Object.class
					|| m.getModifiers() == Modifier.PUBLIC)
				continue;
			privateMems.add(m);
		}
		Member[] newMem = new Member[privateMems.size()];
		for(int i = 0; i < newMem.length; i++){
			newMem[i] = privateMems.get(i);
		}
		printMembers(newMem);
	}

	public static void printAnnotation(Annotation[] annotations){
			for(int i = 0; i < annotations.length; i++){
		String str = annotations[i].toString();
			System.out.print(" ");
			System.out.println(strip(str, "java.lang."));
			}
	}

	private static String strip(String str, String remain){
		return str.replaceAll(remain, "");
	}

}

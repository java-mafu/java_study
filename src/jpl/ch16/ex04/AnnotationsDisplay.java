package jpl.ch16.ex04;

import java.lang.annotation.Annotation;

class AnnotationsDisplay {

	public static String[] getAnnotationsString(Class c){
		Annotation[] annotations = c.getAnnotations();
		String strs[] = new String[annotations.length];
			for(int i = 0; i < annotations.length; i++){
				strs[i] = annotations[i].toString();
			}
			return strs;
	}

	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			for(String s : AnnotationsDisplay.getAnnotationsString(c))
				System.out.println(s);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}

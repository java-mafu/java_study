package jpl.ch13.ex04;

import java.util.ArrayList;

public class MyMap {

	public static ArrayList<Object> readTypeValueString(String str){
		String[] arrayString =  str.split("\n");
		ArrayList<Object> arrayList = new ArrayList<Object>();
		for(int i = 0; i < arrayString.length; i++){
			String[] typeValue = arrayString[i].split(" ");
			switch(typeValue[0]){
			case "Integer":
				arrayList.add(Integer.parseInt(typeValue[1]));
				break;
			case "Long":
				arrayList.add(Long.parseLong(typeValue[1]));
				break;
			case "Byte":
				arrayList.add(Byte.parseByte(typeValue[1]));
				break;
			case "Short":
				arrayList.add(Short.parseShort(typeValue[1]));
				break;
			case "Character":
				arrayList.add(typeValue[1].charAt(0));
				break;
			case "Float":
				arrayList.add(Float.parseFloat(typeValue[1]));
				break;
			case "Double":
				arrayList.add(Double.parseDouble(typeValue[1]));
				break;
			case "Boolean":
				arrayList.add(Boolean.parseBoolean(typeValue[1]));
				break;
				default:
					arrayList.add("Error");
					break;
			}
		}
		return arrayList;
	}

}



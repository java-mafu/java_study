package jpl.ch14.ex02;

public enum PrintJob{

	COPY("copy"),
	SCAN("scan");

	String str;
	PrintJob(String str){
		this.str = str;
	}
	
	@Override
	public String toString(){
		return str;
	}
}

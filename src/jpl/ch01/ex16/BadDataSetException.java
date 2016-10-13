package jpl.ch01.ex16;

import java.io.IOException;

@SuppressWarnings("serial")
class BadDataSetException extends Exception {
    private String file;
    private Exception e;
    public BadDataSetException(String file, Exception e) {
        this.file = file;
        this.e = e;
        printErr();
    }
    public String getFile() {
        return file;
    }

    public Exception getException() {
    	return e;
    }

    private void printErr(){
    	if (e instanceof IOException){
    		System.out.println(file + "はありません");
    	}
    }
}

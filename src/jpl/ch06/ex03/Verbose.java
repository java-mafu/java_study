package jpl.ch06.ex03;

public enum Verbose {
	SILENT,
	TERSE,
	NORMAL,
	VERBOSE;

	Verbose vrb;
	void setVerbose(Verbose level){
		vrb = level;
	};
	Verbose getVerbosity(){
		return vrb;
	};
}

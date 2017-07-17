package java8.ch03.ex01;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DelayLogger {

	static String logName = DelayLogger.class.getName();

	// sample code
	public static void info(Logger logger, Supplier<String> message) {
		if (logger.isLoggable(Level.INFO))
			logger.info(message.get());
	}

	// ex01 code
	public static void logIf(Logger logger, Level level, Supplier<Boolean> b, Supplier<String> msg) {
		if (logger.isLoggable(level) && b.get())
			logger.info(msg);
	}

	public static void main(String[] args) {
		int x = 1;
		int y = 2;
		Logger log = Logger.getLogger(logName);

		//logが出ることの確認
		logIf(log, Level.SEVERE, ()->x==1, () -> "x:"+x+" y:"+y);
		//logが出ないことの確認
		logIf(log, Level.SEVERE, ()->x==2, () -> "x:"+x+" y:"+y);
	}

}

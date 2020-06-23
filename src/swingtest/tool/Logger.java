package swingtest.tool;

import javax.swing.*;
import java.time.LocalTime;

public class Logger {

	private Logger(){}

	private static JTextArea logArea;

	public static Logger getInstance(){
		return LoggerFactory.INSTANCE;
	}

	public static class LoggerFactory{
		public static Logger INSTANCE = new Logger();
	}

	public static void setLogArea(final JTextArea textArea){
		logArea = textArea;
	}

	// public method
	public void log(String message){
		String str = "[" + LocalTime.now() + "]" + message;

		if(logArea != null){
			logArea.append(str + '\n');
		}else{
			System.out.println(str);
		}
	}
}

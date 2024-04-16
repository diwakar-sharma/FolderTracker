package my.diwakar;
import java.io.*;

public class Logger {

	public static void println(String logfilepath, String log ) {
		append(new File(logfilepath), log);
	}

	public static void append(File aFile, String content) {
		try {
			PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream(aFile, true)));
			p.println(content);
			p.close();

		} catch (Exception e) {
			e.printStackTrace();
//			System.err.println(aFile);
		}
	}
}

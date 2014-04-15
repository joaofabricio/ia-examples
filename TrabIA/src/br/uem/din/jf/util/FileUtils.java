package br.uem.din.jf.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtils {

	public static String getFileContents(File file) {
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		
			StringBuilder sb = new StringBuilder();
			
			while(scanner.hasNext()) {
				sb.append(scanner.next());
			}
			
			return sb.toString();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}
			
}

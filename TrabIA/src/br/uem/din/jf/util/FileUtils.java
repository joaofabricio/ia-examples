package br.uem.din.jf.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	public static String getFileContents(File file) {
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
			
}

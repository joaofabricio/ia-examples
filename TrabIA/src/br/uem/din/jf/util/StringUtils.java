package br.uem.din.jf.util;

public class StringUtils {
	
	public static int countOcurrrences(String str, String strToFind) {
		int occurrences = 0;
		int lastIndex = str.indexOf(strToFind);

		while (lastIndex >= 0) {
			lastIndex = str.indexOf(strToFind, lastIndex+1);
			occurrences++;
		}
		
		return occurrences;
	}
	
	public static int countMoreOcurrrences(String str, String... strsToFind) {
		int count = 0;
		for (String find : strsToFind) {
			count += countOcurrrences(str, find);
		}
		return count;
	}
	
}

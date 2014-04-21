package br.uem.din.jf.numbrix;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class NumbrixProperties extends Properties {

	private static final long serialVersionUID = 1L;
	private static final String PROPERTIES_FILE = "exec.properties";
	private static NumbrixProperties instance = new NumbrixProperties();
	
	private NumbrixProperties() {
		try {
			load(new FileReader(PROPERTIES_FILE));
		} catch (IOException e) {
			System.err.println("Problemas ao tentar abrir o arquivo "+PROPERTIES_FILE);
			throw new RuntimeException(e.getMessage());
		}
	}

	public static Integer getRowsNumber() {
		return Integer.parseInt(instance.getProperty("numbrixRows"));
	}

	public static Integer getColsNumber() {
		return Integer.parseInt(instance.getProperty("numbrixCols"));
	}
}

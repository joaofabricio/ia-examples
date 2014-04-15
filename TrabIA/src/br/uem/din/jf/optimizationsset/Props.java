package br.uem.din.jf.optimizationsset;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Props {

	private static final String PROPERTIES_FILE = "exec.properties";
	private static Props instance = new Props();
	private Properties properties = new Properties();
	
	private Props() {
		try {
			properties.load(new FileReader(PROPERTIES_FILE));
		} catch (IOException e) {
			System.err.println("Problemas ao tentar abrir o arquivo "+PROPERTIES_FILE);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String getSource() {
		return properties.getProperty("source");
	}
	
	public String getOut() {
		return properties.getProperty("out");
	}
	
	public String getCompiler() {
		return properties.getProperty("compiler");
	}
	
	public static Props getInstance() {
		return instance;
	}

	public Integer getInitialAdaptation() {
		return Integer.parseInt(properties.getProperty("initialAdaptation"));
	}

	public int getPushWeight() {
		return Integer.parseInt(properties.getProperty("pushWeight"));
	}

	public int getPopWeight() {
		return Integer.parseInt(properties.getProperty("popWeight"));
	}

	public int getJmpWeight() {
		return Integer.parseInt(properties.getProperty("jmpWeight"));
	}

	public int getSizeWeight() {
		return Integer.parseInt(properties.getProperty("sizeWeight"));
	}

}

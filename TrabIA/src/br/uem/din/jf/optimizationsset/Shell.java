package br.uem.din.jf.optimizationsset;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import br.uem.din.jf.util.FileUtils;

public class Shell {

	private static final String WHITESPACE = " ";

	public static String invoke(String command) {
//		System.out.println(command);
		try {
			Process exec = Runtime.getRuntime().exec(command);
			exec.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

			StringBuffer output = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			return output.toString();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static String getAssembly(String[] optimizations) {
		
		String out = mountCall(optimizations, "-S");
		
		return FileUtils.getFileContents(new File(out));
		
	}

	private static String mountCall(String[] optimizations, String... params) {
		Props props = Props.getInstance();
		String compiler = props.getCompiler();
		String source = props.getSource();
		String out = props.getOut();
		
		StringBuffer sb = new StringBuffer();
		sb.append(compiler)
		  .append(WHITESPACE)
		  .append("-o")
		  .append(WHITESPACE)
		  .append(out)
		  .append(WHITESPACE)
		  .append(source);
		
		if (optimizations != null) {
			for (String optimization : optimizations) {
				sb.append(WHITESPACE)
				  .append(optimization);
			}
		}

		if (params != null) {
			for (String param : params) {
				sb.append(WHITESPACE)
				  .append(param);
			}
		}
		invoke(sb.toString());
		return out;
	}
	
	public static Long getExecutionTime(String[] optimizations) {
		String outFile = mountCall(optimizations);
		Long t1 = Calendar.getInstance().getTimeInMillis();
		invoke("./"+outFile);
		Long t2 = Calendar.getInstance().getTimeInMillis();
		return t2 - t1;
	}
	
}

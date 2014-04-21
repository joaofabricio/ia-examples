package br.uem.din.jf.csp;

import java.io.File;

import br.uem.din.jf.numbrix.NumbrixUtils;
import br.uem.din.jf.util.FileUtils;

public class MainCSP {
	
	public static void main(String[] args) {
		CSP csp = new CSP();
		
		CSPSolution initialSolution = getInitialSolution();
		CSPSolution finalSolution = csp.execute(initialSolution);
		
		System.out.println(finalSolution);
	}

	private static CSPSolution getInitialSolution() {
		String fileContents = FileUtils.getFileContents(new File("numbrix.txt"));
		Map extractedCSPMap = NumbrixUtils.extractCSPMap(fileContents);
		NumbrixSolution n =  new NumbrixSolution(extractedCSPMap);
		return n;
	}

}

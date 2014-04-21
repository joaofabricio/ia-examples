package br.uem.din.jf.csp;

import java.io.File;
import java.util.Collection;

import br.uem.din.jf.numbrix.NumbrixUtils;
import br.uem.din.jf.util.FileUtils;

public class MainCSP {
	
	public static void main(String[] args) {
		CSP csp = new CSP();
		
		CSPSolution initialSolution = getInitialSolution();
		CSPSolution finalSolution = csp.execute(initialSolution, initialSolution.getFixeds());
		
		System.out.println(finalSolution);
	}

	private static CSPSolution getInitialSolution() {
		String fileContents = FileUtils.getFileContents(new File("numbrix.txt"));
		Collection<Integer>[][] extractedCSPMap = NumbrixUtils.extractCSPMap(fileContents);
		NumbrixSolutionCSP n =  new NumbrixSolutionCSP(extractedCSPMap);
		return n;
	}

}

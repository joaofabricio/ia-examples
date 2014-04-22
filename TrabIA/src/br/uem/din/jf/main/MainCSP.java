package br.uem.din.jf.main;

import java.io.File;

import br.uem.din.jf.csp.CSP;
import br.uem.din.jf.csp.CSPSolution;
import br.uem.din.jf.csp.NumbrixSolution;
import br.uem.din.jf.numbrix.Map;
import br.uem.din.jf.numbrix.NumbrixUtils;
import br.uem.din.jf.util.FileUtils;

public class MainCSP {
	
	public static void main(String[] args) {
		CSP csp = new CSP();
		
		CSPSolution initialSolution = getInitialSolution(args[0]);
		CSPSolution finalSolution = csp.execute(initialSolution);
		
		System.out.println(finalSolution);
	}

	private static CSPSolution getInitialSolution(String fileName) {
		String fileContents = FileUtils.getFileContents(new File(fileName));
		Map extractedCSPMap = NumbrixUtils.extractMap(fileContents);
		NumbrixSolution n =  new NumbrixSolution(extractedCSPMap);
		return n;
	}

}

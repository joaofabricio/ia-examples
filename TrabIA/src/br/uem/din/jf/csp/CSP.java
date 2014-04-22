package br.uem.din.jf.csp;

import java.util.Collection;

public class CSP {
	
	public CSPSolution execute(CSPSolution actualSolution) {
		System.out.println(actualSolution);
		if (actualSolution.consistent()) 
			return actualSolution;
		
		Collection<CSPSolution> nexts = actualSolution.nexts();
		
		for (CSPSolution cspSolution : nexts) {
			CSPSolution execution = execute(cspSolution);
			if (execution != null)
				return execution;
		}
		
		return null;
		
	}

}

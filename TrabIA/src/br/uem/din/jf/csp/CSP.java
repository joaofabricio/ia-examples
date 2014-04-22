package br.uem.din.jf.csp;

import java.util.Collection;

public class CSP {
	
	private static int nodes = 0;
	
	public CSPSolution execute(CSPSolution actualSolution) {
		nodes++;
		System.out.println(actualSolution);
		if (actualSolution.consistent()) {
			System.out.println("Nós visitados: "+nodes);
			return actualSolution;
		}
		
		Collection<CSPSolution> nexts = actualSolution.nexts();
		
		for (CSPSolution cspSolution : nexts) {
			CSPSolution execution = execute(cspSolution);
			if (execution != null)
				return execution;
		}
		
		return null;
		
	}

}

package br.uem.din.jf.csp;

import java.util.HashSet;
import java.util.Set;

import br.uem.din.jf.numbrix.Pair;

public class CSP {
	
	public CSPSolution execute(CSPSolution initialSolution, Set<Pair> fixeds) {
		return recursiveExecution(initialSolution.populate(fixeds), initialSolution);
	}

	private Set<Pair> initialPossiblyInconsistents() {
		Set<Pair> set = new HashSet<>();
		for (int i = 0; i < NumbrixSolutionCSP.MAX_ROWS; i++) {
			for (int j = 0; j < NumbrixSolutionCSP.MAX_COLS; j++) {
				Pair p = new Pair(i, j);
				set.add(p);
			}
		}
		return set;
	}

	private CSPSolution recursiveExecution(Set<Pair> possiblyInconsistents, 
										CSPSolution solution) {
		System.out.println("--");
		
		while (!possiblyInconsistents.isEmpty()) {
			
			Pair pair = possiblyInconsistents.iterator().next();
			
			System.out.println(pair);
			
			if (!solution.changeRange(pair)) {
				possiblyInconsistents.addAll(pair.adjacent());
			}
			possiblyInconsistents.remove(pair);
			
		}
		
		return solution;
		
	}

}

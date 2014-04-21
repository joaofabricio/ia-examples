package br.uem.din.jf.csp;

import java.util.Collection;
import java.util.Stack;

public class CSP {
	
	public CSPSolution execute(CSPSolution actualSolution) {
		Stack<CSPSolution> solutions = new Stack<>();
		solutions.push(actualSolution);
		
		while(!actualSolution.consistent()) {
			Collection<CSPSolution> nexts = actualSolution.nexts();
			solutions.addAll(nexts);
			
			System.out.println(actualSolution);
			actualSolution = solutions.pop();
		}
		
		
		return actualSolution;
	}

}

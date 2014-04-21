package br.uem.din.jf.csp;

import java.util.Collection;


public abstract class CSPSolution {
	
	public abstract boolean consistent();

	public abstract Collection<CSPSolution> nexts();


}

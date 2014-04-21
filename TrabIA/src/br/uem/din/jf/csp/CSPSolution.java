package br.uem.din.jf.csp;

import java.util.Set;

import br.uem.din.jf.numbrix.Pair;

public abstract class CSPSolution {

	public abstract boolean changeRange(Pair pair);

	public abstract Set<Pair> populate(Set<Pair> fixeds);

	public abstract Set<Pair> getFixeds();

}

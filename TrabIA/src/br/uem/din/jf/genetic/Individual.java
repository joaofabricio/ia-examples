package br.uem.din.jf.genetic;

import java.util.List;

public abstract class Individual<T extends Individual<T>>
									implements Comparable<T> {

	public abstract int getAdaptation();

	public abstract void mutate();

	public abstract boolean hasBestSolution();

	public abstract List<T> reproduction(T y);

	@Override
	public int compareTo(T o) {
		return (int) (o.getAdaptation() - getAdaptation());
	}
}

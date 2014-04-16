package br.uem.din.jf.astar;

import java.util.List;

public abstract class SearchSpace<T extends SearchSpace<T>> implements Comparable<T> {
	
	public abstract int getG();
	
	public abstract List<T> sons();
	
	public abstract boolean hasBestSolution();

	@Override
	public int compareTo(T o) {
		return (int) (o.getG() - getG());
	}
}

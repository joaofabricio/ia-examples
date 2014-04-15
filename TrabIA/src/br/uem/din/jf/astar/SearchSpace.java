package br.uem.din.jf.astar;

import java.util.List;

public abstract class SearchSpace<T> implements Comparable<T> {
	
	public abstract int getG();
	
	public abstract List<T> sons();
	
	public abstract boolean hasBestSolution();
}

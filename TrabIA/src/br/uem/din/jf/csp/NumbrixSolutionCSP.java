package br.uem.din.jf.csp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.uem.din.jf.numbrix.NumbrixProperties;
import br.uem.din.jf.numbrix.Pair;

public class NumbrixSolutionCSP extends CSPSolution {

	
	public static final Integer MAX_COLS = NumbrixProperties.getColsNumber();

	public static final Integer MAX_ROWS = NumbrixProperties.getRowsNumber();

	public static final int MAX_VALUE = MAX_COLS * MAX_ROWS;

	private Collection<Integer>[][] map;
	
	public NumbrixSolutionCSP(Collection<Integer>[][] map) {
		this.map = map;
	}

	@Override
	public boolean changeRange(Pair pair) {
		
		Collection<Integer> list = getRange(pair);
		
//		boolean consistency = true;
		Collection<Integer> listToRemove = new ArrayList<>();
		if (list.size() > 1) {
			for (Integer rangeVariable : list) {
				if (checkVariable(rangeVariable, pair)) {
					listToRemove.add(rangeVariable);
				}
			}
		}
		
		list.removeAll(listToRemove);
		return listToRemove.size() == 0;
	}

	private boolean checkVariable(Integer rangeVariable, Pair pair) {
		boolean inc = checkBelonging(rangeVariable+1, pair.adjacent());
		boolean dec = checkBelonging(rangeVariable-1, pair.adjacent());
//		if (!inc || !dec) {
//			Collection<Integer> range = getRange(pair);
//			range.remove(rangeVariable);
//			return rangeVariable;
//		}
		return inc && dec;
	}

	private boolean checkBelonging(int var, Collection<Pair> adjacent) {
		boolean belonging = false;
		for (Pair pair : adjacent) {
			belonging |= getRange(pair).contains(var);
		}
		return belonging;
	}

	private Collection<Integer> getRange(Pair pair) {
		return map[pair.getX()][pair.getY()];
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != null)
					sb.append(map[i][j]);
				else
					sb.append("--");
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public Set<Pair> populate(Set<Pair> fixeds) {
		Set<Pair> set = new HashSet<>();
//		for (int i = 0; i < MAX_ROWS; i++) {
//			for (int j = 0; j < MAX_COLS; j++) {
		for (Pair pair : fixeds) {
			
//				Pair pair = new Pair(i, j);
			Collection<Integer> list = getRange(pair);
//			if (!list.isEmpty()) {
				for (Integer value : list) {
					populateAdjacent(pair, value);
					set.addAll(pair.adjacent());
				}
//			}
		}
//		}
		return set;
	}

	private void populateAdjacent(Pair pair, Integer value) {
		// TODO Auto-generated method stub
		for (Pair pos : pair.adjacent()) {
			if (value > 1)
				getRange(pos).add(value-1);
			if (value < MAX_VALUE)
				getRange(pos).add(value+1);
		}
		
	}

	@Override
	public Set<Pair> getFixeds() {
		Set<Pair> set = new HashSet<>();
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLS; j++) {
				if (!getRange(new Pair(i,j)).isEmpty()) {
					set.add(new Pair(i,j));
				}
			}
		}
		return set;
	}
}

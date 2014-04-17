package br.uem.din.jf.genetic.numbrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.uem.din.jf.genetic.Individual;

public class NumbrixSolution extends Individual<NumbrixSolution> {
	
	private static final int MAX_SWAPS = 15;
	private Map map;
	
	public NumbrixSolution(Map map) {
		this.map = new Map(map);
	}

	@Override
	public int getAdaptation() {
		return NumbrixProperties.getColsNumber() * 
			   NumbrixProperties.getRowsNumber() -
			   map.corrections();
	}

	@Override
	public void mutate() {
		Random random = new Random();
		
		int swaps = random.nextInt(MAX_SWAPS) + 1;
		
		for (int i = 0; i < swaps; i++) {
			Pair incorrection = map.getIncorrection(i);
//			Pair source = new Pair(random.nextInt(Map.MAX_ROWS), random.nextInt(Map.MAX_COLS));
			Pair dest = new Pair(random.nextInt(Map.MAX_ROWS), random.nextInt(Map.MAX_COLS));
			map.swap(incorrection, dest);
		}
		
		
	}

	@Override
	public boolean hasBestSolution() {
		return getAdaptation() == Map.MAX_VALUE;
	}

	@Override
	public List<NumbrixSolution> reproduction(NumbrixSolution y) {
		List<NumbrixSolution> newSolutions = new ArrayList<>();
		
		List<Map> nexts = map.cross(y.map);
		for (Map map : nexts) {
			newSolutions.add(new NumbrixSolution(map));
		}
		
		return newSolutions;
//		return new Solution(this.map);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("[");
		sb.append(getClass().getName())
		  .append(":")
		  .append(getAdaptation())
		  .append("\n")
		  .append(map)
		  .append("]");
		return sb.toString();
	}
}

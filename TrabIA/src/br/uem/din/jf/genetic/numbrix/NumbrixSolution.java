package br.uem.din.jf.genetic.numbrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.uem.din.jf.genetic.Individual;
import br.uem.din.jf.numbrix.NumbrixProperties;
import br.uem.din.jf.numbrix.Pair;

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
			   map.getIncorrections();
	}

	@Override
	public void mutate() {
		Random random = new Random();
		
		int inc = random.nextInt(map.getIncorrections()) + 1;
		
//		for (int i = 0; i < swaps; i++) {
			Pair incorrection = map.getIncorrection(inc);
//			Pair source = new Pair(random.nextInt(Map.MAX_ROWS), random.nextInt(Map.MAX_COLS));
			inc = random.nextInt(map.getIncorrections()) + 1;
			Pair dest = map.getIncorrection(inc);
			map.swap(incorrection, dest);
//		}
		
		
	}

	@Override
	public boolean hasBestSolution() {
		return getAdaptation() == Map.MAX_VALUE;
	}

	@Override
	public List<NumbrixSolution> reproduction(NumbrixSolution y) {
		List<NumbrixSolution> newSolutions = new ArrayList<>();
		
		Random random = new Random();
		int incorrections = map.getIncorrections();
		
//		int swaps = random.nextInt(MAX_SWAPS) + 1;
		
		Map newMap = new Map(map);
		for (int i = 0; i < (incorrections/2); i++) {
			Pair source = map.getIncorrection(i);
			Pair dest = map.getIncorrection(incorrections/2 + random.nextInt(incorrections));
//			Pair source = new Pair(random.nextInt(Map.MAX_ROWS), random.nextInt(Map.MAX_COLS));
//			Pair dest = new Pair(random.nextInt(Map.MAX_ROWS), random.nextInt(Map.MAX_COLS));
			newMap.swap(source, dest);
		}
		newSolutions.add(new NumbrixSolution(newMap));
		
		return newSolutions;
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

package br.uem.din.jf.genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.uem.din.jf.numbrix.Map;
import br.uem.din.jf.numbrix.Pair;

public class NumbrixSolution extends Individual<NumbrixSolution> {
	
	private Map map;
	
	public NumbrixSolution(Map map) {
		this.map = new Map(map);
	}

	@Override
	public int getAdaptation() {
		return Map.MAX_VALUE -
			   map.getIncorrections();
	}

	@Override
	public void mutate() {
		Random random = new Random();
		
		int inc = random.nextInt(map.getIncorrections()) + 1;
		
		Pair incorrection = map.getIncorrection(inc);
		inc = random.nextInt(map.getIncorrections()) + 1;
		Pair dest = map.getIncorrection(inc);
		map.swap(incorrection, dest);
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NumbrixSolution other = (NumbrixSolution) obj;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		return true;
	}

	@Override
	public boolean hasBestSolution() {
		return getAdaptation() == Map.MAX_VALUE;
	}

	@Override
	public List<NumbrixSolution> reproduction(NumbrixSolution y) {
		List<NumbrixSolution> newSolutions = new ArrayList<>();
		
		List<Map> nexts = map.nexts();
		
		for (Map map : nexts) {
			NumbrixSolution numbrixSolution = new NumbrixSolution(map);
			newSolutions.add(numbrixSolution);
		}
//		int swaps = random.nextInt(MAX_SWAPS) + 1;
		
//		Map newMap = new Map(map);
//		for (int i = 0; i < (incorrections/2); i++) {
//			Pair source = map.getIncorrection(i);
//			Pair dest = map.getIncorrection(incorrections/2 + random.nextInt(incorrections));
////			Pair source = new Pair(random.nextInt(Map.MAX_ROWS), random.nextInt(Map.MAX_COLS));
////			Pair dest = new Pair(random.nextInt(Map.MAX_ROWS), random.nextInt(Map.MAX_COLS));
//			newMap.swap(source, dest);
//		}
//		newSolutions.add(new NumbrixSolution(newMap));
		
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

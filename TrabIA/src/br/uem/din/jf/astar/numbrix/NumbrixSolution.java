package br.uem.din.jf.astar.numbrix;

import java.util.ArrayList;
import java.util.List;

import br.uem.din.jf.astar.SearchSpace;
import br.uem.din.jf.astar.numbrix.Map;

public class NumbrixSolution extends SearchSpace<NumbrixSolution> {

	private Map map;

	public NumbrixSolution(Map map) {
		this.map = new Map(map);
	}

	@Override
	public int getG() {
		return Map.MAX_VALUE - map.corrections();
	}

	@Override
	public List<NumbrixSolution> sons() {
		List<NumbrixSolution> newSolutions = new ArrayList<>();
		
		List<Map> nexts = map.nexts();
		for (Map map : nexts) {
			newSolutions.add(new NumbrixSolution(map));
		}
		
		return newSolutions;
	}

	@Override
	public boolean hasBestSolution() {
		return getG() == Map.MAX_VALUE && map.getSmallestValueNotUsed() > Map.MAX_VALUE;
	}

	@Override
	public String toString() {
		return map.toString();
	}

}

package br.uem.din.jf.csp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.uem.din.jf.numbrix.Map;

public class NumbrixSolution extends CSPSolution {

	private Map map;

	public NumbrixSolution(Map map) {
		this.map = map;
	}

	@Override
	public boolean consistent() {
		return map.getIncorrections() == 0;
	}

	@Override
	public Collection<CSPSolution> nexts() {
		List<CSPSolution> list = new ArrayList<>();
		List<Map> nextMaps = map.nexts();
		for (Map nextMap : nextMaps) {
			CSPSolution next = new NumbrixSolution(nextMap);
			list.add(next);
		}
		return list;
	}
	
	@Override
	public String toString() {
		return map.toString();
	}

}

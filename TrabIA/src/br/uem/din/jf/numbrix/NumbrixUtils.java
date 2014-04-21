package br.uem.din.jf.numbrix;

import java.util.ArrayList;
import java.util.List;

import br.uem.din.jf.genetic.numbrix.Map;

public class NumbrixUtils {

	public static Map extractGeneticMap(String numbrixStr) {
		Integer[][] map = new Integer[NumbrixProperties.getRowsNumber()][NumbrixProperties.getColsNumber()];
		
		List<Pair> fixedPositions = new ArrayList<>();
		String[] lines = numbrixStr.split("\\n");
		int i = 0;
		for (String line : lines) {
			String[] nums = line.split("\\s");
			int j = 0;
			for (String num : nums) {
				int number = Integer.parseInt(num);
				if (number != 0) {
					map[i][j] = number;
					fixedPositions.add(new Pair(i, j));
				}
				j++;
			}
			i++;
		}
		return new Map(map, fixedPositions);
	}

	public static br.uem.din.jf.astar.numbrix.Map extractAstarMap(String numbrixStr) {
		Integer[][] map = new Integer[NumbrixProperties.getRowsNumber()][NumbrixProperties.getColsNumber()];
		
		List<Pair> fixedPositions = new ArrayList<>();
		String[] lines = numbrixStr.split("\\n");
		int i = 0;
		for (String line : lines) {
			String[] nums = line.split("\\s");
			int j = 0;
			for (String num : nums) {
				int number = Integer.parseInt(num);
				if (number != 0) {
					map[i][j] = number;
					fixedPositions.add(new Pair(i, j));
				}
				j++;
			}
			i++;
		}
		return new br.uem.din.jf.astar.numbrix.Map(map, fixedPositions);
	}

	public static br.uem.din.jf.csp.Map extractCSPMap(String numbrixStr) {
		Integer[][] map = new Integer[NumbrixProperties.getRowsNumber()][NumbrixProperties.getColsNumber()];
		
		List<Pair> fixedPositions = new ArrayList<>();
		String[] lines = numbrixStr.split("\\n");
		int i = 0;
		for (String line : lines) {
			String[] nums = line.split("\\s");
			int j = 0;
			for (String num : nums) {
				int number = Integer.parseInt(num);
				if (number != 0) {
					map[i][j] = number;
					fixedPositions.add(new Pair(i, j));
				}
				j++;
			}
			i++;
		}
		return new br.uem.din.jf.csp.Map(map, fixedPositions);
	}

}

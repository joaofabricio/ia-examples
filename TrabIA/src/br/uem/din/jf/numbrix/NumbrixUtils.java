package br.uem.din.jf.numbrix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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

	@SuppressWarnings("unchecked")
	public static Collection<Integer>[][] extractCSPMap(String numbrixStr) {
		
		Collection<Integer>[][] map = new HashSet[NumbrixProperties.getRowsNumber()][NumbrixProperties.getColsNumber()];
		
		String[] lines = numbrixStr.split("\\n");
		int i = 0;
		for (String line : lines) {
			String[] nums = line.split("\\s");
			int j = 0;
			for (String num : nums) {
				map[i][j] = new HashSet<Integer>();
				int number = Integer.parseInt(num);
				if (number != 0) {
					map[i][j].add(number);
				} else {
//					map[i][j].addAll(inRange(NumbrixProperties.getRowsNumber()*NumbrixProperties.getColsNumber()));
				}
				j++;
			}
			i++;
		}
		return map;
	}

	private static Collection<Integer> inRange(Integer maxValue) {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < maxValue; i++) {
			list.add(i);
		}
		return list;
	}
}

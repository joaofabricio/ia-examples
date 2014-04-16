package br.uem.din.jf.genetic.numbrix;

import java.util.ArrayList;
import java.util.List;

public class NumbrixUtils {

	public static Map extractMap(String numbrixStr) {
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

}

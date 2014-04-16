package br.uem.din.jf.genetic.numbrix;

import java.util.List;


public class Map {

	public static final Integer MAX_COLS = NumbrixProperties.getColsNumber();

	public static final Integer MAX_ROWS = NumbrixProperties.getRowsNumber();

	public static final int MAX_VALUE = MAX_COLS * MAX_ROWS;

	private Integer[][] map;

	private List<Pair> fixedPositions;

	public Map(Integer[][] map, List<Pair> fixedPositions) {
		this.map = map;
		this.fixedPositions = fixedPositions;
	}
	
	public Map(Map map) {
		this.map = cloneMap(map.map);
		this.fixedPositions = map.fixedPositions;
	}

	private Integer[][] cloneMap(Integer[][] map) {
		Integer[][] ret = new Integer[MAX_ROWS][MAX_COLS];
		for (int x = 0; x < map.length; x++) {
			ret[x] = map[x].clone();
		}
		return ret;
	}

	public int corrections() {
		int faults = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (!fixed(i, j) && !checkCorrect(i,j))
					faults++;
			}
		}
		return faults;
	}

	private boolean fixed(int i, int j) {
		return fixed(new Pair(i, j));
	}

	private boolean checkCorrect(int i, int j) {
		Integer value = map[i][j];
		if (value == null)
			return false;
		int points = 0;
		if (i != 0)
			points += check(value, i-1, j)? 1 : 0;
		if (i != MAX_ROWS-1)
			points += check(value, i+1, j)? 1 : 0;
		if (j != 0)
			points += check(value, i, j-1)? 1 : 0;
		if (j != MAX_COLS-1)
			points += check(value, i, j+1)? 1 : 0;
		
		return points == 2 ||
				((value == 1 || value == MAX_VALUE) && points == 1);
	}

	private boolean check(Integer integer, int i, int j) {
		return integer == map[i][j]-1 || integer == map[i][j]+1;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				sb.append(String.format("%02d", map[i][j]));
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public Integer getSmallestValueNotUsed() {
		Integer i = 0;
		while (hasValue(i)) {
			i++;
		}
		return i;
	}

	public boolean hasValue(Integer value) {
		return getPosition(value) != null;
	}

	public Pair getFirstUnfilled() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == null)
					return new Pair(i, j);
			}
		}
		return null;
	}

	public void put(Integer value, Pair position) {
		if (value != null && hasValue(value)) {
			throw new RuntimeException("Já existe no mapa o valor "+value);
		}
		map[position.getX()][position.getY()] = value;
	}

	public void swap(Pair source, Pair dest) {
		if (source == null || dest == null ||
				fixed(source) || fixed(dest) || source.equals(dest))
			return;
		Integer valueDest = getValue(dest);
		Integer valueSource = getValue(source);
		put(null, dest);
		put(null, source);
		put(valueSource, dest);
		put(valueDest, source);
	}

	private boolean fixed(Pair pair) {
		return fixedPositions.contains(pair);
	}

	private Integer getValue(Pair pair) {
		return map[pair.getX()][pair.getY()];
	}

	public Map cross(Map other) {
		Map newMap = new Map(this);
		for (int i = 0; i <= corrections(); i++) {
			Pair posSource = getIncorrection(i);
			Pair posDest = other.getIncorrection(i);
			newMap.swap(posSource, posDest);
		}
		return newMap;
	}

	private Pair getIncorrection(int k) {
		int count = 0;
		for (int i = 0; i < map.length && count <= k; i++) {
			for (int j = 0; j < map[i].length && count <= k; j++) {
				Pair pair = new Pair(i, j);
				if (!check(getValue(pair), i, j)) {
					if (count++ == k)
						return pair; 
				}
			}
		}
		return null;
	}

	private Pair getPosition(Integer value) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (value == getValue(new Pair(i, j)))
					return new Pair(i, j);
			}
		}
		return null;
	}
}

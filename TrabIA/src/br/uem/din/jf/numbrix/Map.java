package br.uem.din.jf.numbrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.uem.din.jf.numbrix.NumbrixProperties;
import br.uem.din.jf.numbrix.Pair;

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

	public int getIncorrections() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fixedPositions == null) ? 0 : fixedPositions.hashCode());
		result = prime * result + Arrays.hashCode(map);
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
		Map other = (Map) obj;
		if (fixedPositions == null) {
			if (other.fixedPositions != null)
				return false;
		} else if (!fixedPositions.equals(other.fixedPositions))
			return false;
		if (!Arrays.deepEquals(map, other.map))
			return false;
		return true;
	}

	private boolean check(Integer value, int i, int j) {
		return map[i][j] != null && (value == map[i][j]-1 || value == map[i][j]+1);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != null)
					sb.append(String.format("%02d", map[i][j]));
				else
					sb.append("--");
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public Integer getSmallestValueNotUsed() {
		Integer i = 1;
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

	public boolean put(Integer value, Pair position) {
		if (value != null && hasValue(value)) {
			throw new RuntimeException("Já existe no mapa o valor "+value);
//			return false;
		}
		if (position.getX() < 0 || position.getX() >= MAX_ROWS ||
			position.getY() < 0 || position.getY() >= MAX_COLS ||
			getValue(position) != null)
			return false;
		
		map[position.getX()][position.getY()] = value;
		return true;
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

	private Pair getPosition(Integer value) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (value == getValue(new Pair(i, j)))
					return new Pair(i, j);
			}
		}
		return null;
	}

	public List<Map> nexts() {
		List<Map> list = new ArrayList<>();
		Integer value = getSmallestValueNotUsed();
		Pair pos = getPosition(value-1);

		boolean nextIsfixed = fixed(getPosition(value+1));
		
		Map up = new Map(this);
		if (up.putIndex(pos.up()))
			list.add(up);

		Map down = new Map(this);
		if (down.putIndex(pos.down()))
			list.add(down);
		
		Map left = new Map(this);
		if (left.putIndex(pos.left()))
			list.add(left);
		
		Map right = new Map(this);
		if (right.putIndex(pos.right()))
			list.add(right);
		
		if (nextIsfixed && list.size() > 0) {
				
			List<Map> toRemove = new ArrayList<>();
			for (Map map : list) {
				if (!map.checkCorrect(map.getPosition(value))) {
					toRemove.add(map);
				}
			}
			list.removeAll(toRemove);
		}
		
		return list;
	}

	private boolean checkCorrect(Pair position) {
		return checkCorrect(position.getX(), position.getY());
	}

	private boolean putIndex(Pair position) {
		return put(getSmallestValueNotUsed(), position);
	}

	public int nulls() {
		int n = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (getValue(new Pair(i, j))==null)
					n++;
			}
		}
		return n;
	}

	public Pair getIncorrection(int inc) {
		int faults = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (!fixed(i, j) && !checkCorrect(i,j))
					if (++faults == inc)
						return new Pair(i, j);
			}
		}
		return null;
	}
}

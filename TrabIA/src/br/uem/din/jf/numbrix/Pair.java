package br.uem.din.jf.numbrix;

import java.util.ArrayList;
import java.util.Collection;

import br.uem.din.jf.csp.Map;

public class Pair {

	private int x;
	private int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Pair other = (Pair) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public Pair up() {
		return new Pair(x-1, y);
	}

	public Pair down() {
		return new Pair(x+1, y);
	}

	public Pair left() {
		return new Pair(x, y-1);
	}

	public Pair right() {
		return new Pair(x, y+1);
	}
	
	public Collection<Pair> adjacent() {
		Collection<Pair> list = new ArrayList<>();
		if (x!=0)
			list.add(up());
		if (x!=Map.MAX_ROWS-1)
			list.add(down());
		if (y!=0)
			list.add(left());
		if (y!=Map.MAX_COLS-1)
			list.add(right());
		return list;
	}
	
	@Override
	public String toString() {
		return "Pair("+x+", "+y+")";
	}
}

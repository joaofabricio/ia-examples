package br.uem.din.jf.astar;

import java.util.AbstractQueue;
import java.util.List;
import java.util.PriorityQueue;

public class AStar<T extends SearchSpace<T>> {
	
	PriorityQueue<T> A = new PriorityQueue<T>();
	PriorityQueue<T> F = new PriorityQueue<T>();

	public T execute(T initial) {

		A.add(initial);

		int count = 1;
		while (A.size() != 0) {
			T v = A.remove();
			F.add(v);
			System.out.println(v);
			if (v.hasBestSolution()) {
				System.out.println("Nós produzidos: "+count);
				System.out.println("Nós visitados: "+F.size());
				return v;//OK
			}
			
			List<T> G = v.sons();
			
			for (T m : G) {
				boolean visited = A.contains(m) || F.contains(m);
				if (!visited || (m.getG() < getGEqual(A, m))) {
					A.add(m);
					count++;
					if (F.contains(m))
						F.remove(m);
				}
			}
			
		}
		
		return null;
		
	}
	

	private int getGEqual(AbstractQueue<T> a, T m) {
		for (T T : a) {
			if (T.equals(m)) {
				return T.getG();
			}
		}
		return 0;
	}

}

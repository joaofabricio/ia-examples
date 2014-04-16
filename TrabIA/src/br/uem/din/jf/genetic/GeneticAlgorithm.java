package br.uem.din.jf.genetic;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class GeneticAlgorithm<T extends Individual<T>> {

	private Collection<T> population;
	private int maxGenerations = 500000000;

	public GeneticAlgorithm(PriorityQueue<T> initialPopulation) {
		if (initialPopulation.size() < 2) {
			throw new RuntimeException("A população inicial deve ter pelo menos 2 indivíduos");
		}
		this.population = initialPopulation;
	}
	
	public T execute() {
		
		int generation = 0;
		while (generation++ < maxGenerations &&
				!hasTheSolution()) {
			T x = selection();
			T y = selection();
			List<T> sonsXY = reproduction(x, y);
			List<T> sonsYX = reproduction(y, x);
			if (propability()) {
				for (T yx : sonsYX) {
					yx.mutate();
				}
				for (T xy : sonsYX) {
					xy.mutate();
				}
			}
			
			population.addAll(sonsXY);
			population.addAll(sonsYX);
			
			System.out.println("generation: "+generation+" ------- best: "+ getBest().getAdaptation());
			System.out.println(getBest());
			removeWorsts();
		}
		
		
		return getBest();
	}

	private void removeWorsts() {
		
		Iterator<T> iterator = population.iterator();
		Object worst = null;
		while (iterator.hasNext()) {
			worst = iterator.next();
		}
		population.remove(worst);
		
	}

	private T getBest() {
		return population.iterator().next();
	}

	private boolean propability() {
		double rand = Math.random();
		return rand < 0.6;
	}

	private List<T> reproduction(T x, T y) {
		return x.reproduction(y);
	}

	private T selection() {
		Random r = new Random();
		int n = r.nextInt(population.size());
		
		int i = 0;
		Iterator<T> iterator = population.iterator();
		while (iterator.hasNext()) {
			T next = iterator.next();
			if (i++ == n) {
				return next;
			}
		}
		return null;
	}

	private boolean hasTheSolution() {
		return getBest().hasBestSolution();
	}

}

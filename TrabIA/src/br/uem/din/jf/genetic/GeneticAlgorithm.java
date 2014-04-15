package br.uem.din.jf.genetic;

import java.util.Collection;
import java.util.Iterator;

public class GeneticAlgorithm<T extends Individual<T>> {

	private Collection<T> population;
	private int maxGenerations = 500;

	public GeneticAlgorithm(Collection<T> initialPopulation) {
		if (initialPopulation.size() < 2) {
			throw new RuntimeException("A população inicial deve ter pelo menos 2 indivíduos");
		}
		this.population = initialPopulation;
	}
	
	public T execute() {
		
		int generation = 0;
		while (generation++ < maxGenerations &&
				!hasTheSolution()) {
			T x = selection(null);
			T y = selection(x);
			T sonXY = reproduction(x, y);
			T sonYX = reproduction(y, x);
			if (propability()) {
				sonXY.mutate();
				sonYX.mutate();
			}
			
			population.add(sonXY);
			population.add(sonYX);
			
			System.out.println("generation: "+generation+" ------- best: "+ getBest().getAdaptation()+" ------ found: "+sonXY.getAdaptation());
			System.out.println("generation: "+generation+" ------- best: "+ getBest().getAdaptation()+" ------ found: "+sonYX.getAdaptation());
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

	private T reproduction(T x, T y) {
		return x.crossover(y);
	}

	private T selection(T selected) {
		Iterator<T> iterator = population.iterator();
		while (iterator.hasNext()) {
			T next = iterator.next();
			if (!next.equals(selected)) {
				return next;
			}
		}
		return null;
	}

	private boolean hasTheSolution() {
		return getBest().hasBestSolution();
	}

}

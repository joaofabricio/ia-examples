package br.uem.din.jf.genetic;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class GeneticAlgorithm<T extends Individual<T>> {

//	private static final int MAX_STAGNATION_TO_MUTATE = 50;
	private Collection<T> population;
	private int maxGenerations = 999999;
	private int maxStagnation = 99999;
	private int stagnation = 0;

	public GeneticAlgorithm(PriorityQueue<T> initialPopulation) {
		if (initialPopulation.size() < 2) {
			throw new RuntimeException("A população inicial deve ter pelo menos 2 indivíduos");
		}
		this.population = initialPopulation;
	}
	
	public T execute() {
		
		int generation = 0;
		T lastBest = null;
		while (generation++ < maxGenerations &&
				stagnation < maxStagnation &&
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
			
			T best = getBest();
			if (best.equals(lastBest))
				stagnation++;
			else
				stagnation = 0;
			System.out.println("generation: "+generation+" stagnation: "+stagnation+" ------- best: "+ best.getAdaptation());
//			System.out.println("generated: "+ sonsXY.get(0).getAdaptation());
			System.out.println(population.size());
			System.out.println(best);
			removeWorsts();
			lastBest = best;
		}
		
		if (!getBest().hasBestSolution()) {
			System.out.println("fail!");
		}
		
		return getBest();
	}

	private void removeWorsts() {
		
		Iterator<T> iterator = population.iterator();
		Object worst = null;
		int i = 0;
		while (i<2) {//iterator.hasNext()) {
			worst = iterator.next();
			i++;
		}
		population.remove(worst);
		
	}

	private T getBest() {
		return population.iterator().next();
	}

	private boolean propability() {
		double rand = Math.random();
		return rand < stagnation / maxStagnation;
//		return false;
	}

	private List<T> reproduction(T x, T y) {
		return x.reproduction(y);
	}

	private T selection() {
		Random r = new Random();
		int n = r.nextInt(Math.min(10, population.size()));
		
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

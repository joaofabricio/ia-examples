package br.uem.din.jf.genetic;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class GeneticAlgorithm<T extends Individual<T>> {

	private PriorityQueue<T> population;
	private int maxGenerations = 2000000;
	private int maxStagnation = 350000;
	private int stagnation = 0;
	private int generation = 0;

	public GeneticAlgorithm(PriorityQueue<T> initialPopulation) {
		if (initialPopulation.size() < 2) {
			throw new RuntimeException("A população inicial deve ter pelo menos 2 indivíduos");
		}
		this.population = initialPopulation;
	}
	
	public T execute() {
		
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
			
			addToPopulation(sonsXY);
			addToPopulation(sonsYX);
//			population.addAll(sonsXY);
//			population.addAll(sonsYX);
			
			T best = getBest();
			if (best.equals(lastBest))
				stagnation++;
			else
				stagnation = 0;
			System.out.println("generation: "+generation+" stagnation: "+stagnation+" ------- best: "+ best.getAdaptation());
//			System.out.println("generated: "+ sonsXY.get(0).getAdaptation());
			System.out.println(best);
			removeWorsts();
			lastBest = best;
		}
		
		if (!getBest().hasBestSolution()) {
			System.out.println("fail!");
		}
		
		return getBest();
	}

	private void addToPopulation(List<T> toAdd) {
		for (T t : toAdd) {
			if (!population.contains(t))
				population.add(t);
		}
	}

	private void removeWorsts() {
		
//		Iterator<T> iterator = population.iterator();
//		Object worst = null;
//		int i = 0;
//		while (i<2) {//iterator.hasNext()) {
//			worst = iterator.next();
//			i++;
//		}
//		population.remove(worst);
		
	}

	private T getBest() {
		Iterator<T> iterator = population.iterator();
		return iterator.next();
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
		int n = r.nextInt(Math.min((stagnation+1), population.size()));
		
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

package br.uem.din.jf.optimizationsset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import br.uem.din.jf.genetic.GeneticAlgorithm;

public class MainOptimizationsSet {

	public static void main(String[] args) {
		Random r = new Random();
		Collection<OptimizationSet> initialPopulation = new PriorityQueue<OptimizationSet>();
		for (int t = 0; t < 2; t++) {
			int optAmount = r.nextInt(30) + 1;
			List<String> optSet = new ArrayList<>();
			for (int i = 0; i < optAmount; i++) {
				int optPos = r.nextInt(Optimizations.TOTAL_OPTIMIZATIONS.length);
				optSet.add(Optimizations.TOTAL_OPTIMIZATIONS[optPos]);
			}
			initialPopulation.add(new OptimizationSet(optSet.toArray(new String[0])));
		}
		
		initialPopulation.add(new OptimizationSet(Optimizations.TOTAL_OPTIMIZATIONS));
		initialPopulation.add(new OptimizationSet(Optimizations.O1));
		
		GeneticAlgorithm<OptimizationSet> genetic = new GeneticAlgorithm<OptimizationSet>(initialPopulation);
		
		OptimizationSet finalSet = genetic.execute();
		

		System.out.println("---------------------------------");
		System.out.println("Best:");
		System.out.println();
		System.out.println(finalSet);
	}
	
}

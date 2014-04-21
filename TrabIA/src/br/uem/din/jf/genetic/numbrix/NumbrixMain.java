package br.uem.din.jf.genetic.numbrix;

import java.io.File;
import java.util.PriorityQueue;

import br.uem.din.jf.genetic.GeneticAlgorithm;
import br.uem.din.jf.numbrix.NumbrixUtils;
import br.uem.din.jf.numbrix.Pair;
import br.uem.din.jf.util.FileUtils;

public class NumbrixMain {

	public static void main(String[] args) {
		String numbrixStr = FileUtils.getFileContents(new File("numbrix.txt"));
		
		Map initialMap = NumbrixUtils.extractGeneticMap(numbrixStr);
		
		Map ascMap = new Map(initialMap);
		fillAsc(ascMap);
		
		Map descMap = new Map(initialMap);
		fillDesc(descMap);
		
		PriorityQueue<NumbrixSolution> population = new PriorityQueue<>();
		population.add(new NumbrixSolution(ascMap));
		population.add(new NumbrixSolution(descMap));

		GeneticAlgorithm<NumbrixSolution> genetic = new GeneticAlgorithm<>(population);
		
		NumbrixSolution finalSolution = genetic.execute();
		
		System.out.println(finalSolution);
	}

	private static void fillDesc(Map map) {
		for (int i = Map.MAX_VALUE; i >= 1; i--) {
			if (!map.hasValue(i)) {
				Pair p = map.getFirstUnfilled();
				if (p == null)
					return;
				map.put(i, p);
			}
			
		}
	}

	private static void fillAsc(Map map) {
		for (int i = 1; i <= Map.MAX_VALUE; i++) {
			if (!map.hasValue(i)) {
				Pair p = map.getFirstUnfilled();
				if (p == null)
					return;
				map.put(i, p);
			}
			
		}
	}
	
}

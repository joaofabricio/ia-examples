package br.uem.din.jf.main;

import java.io.File;
import java.util.List;
import java.util.PriorityQueue;

import br.uem.din.jf.genetic.GeneticAlgorithm;
import br.uem.din.jf.genetic.NumbrixSolution;
import br.uem.din.jf.numbrix.Map;
import br.uem.din.jf.numbrix.NumbrixUtils;
import br.uem.din.jf.util.FileUtils;

public class NumbrixMainGenetic {

	public static void main(String[] args) {
		String numbrixStr = FileUtils.getFileContents(new File(args[0]));
		
		Map initialMap = NumbrixUtils.extractMap(numbrixStr);
		
		PriorityQueue<NumbrixSolution> population = new PriorityQueue<NumbrixSolution>();
		population.add(new NumbrixSolution(initialMap));
		List<Map> nexts = initialMap.nexts();
		for (Map map : nexts) {
			NumbrixSolution numbrixSolution = new NumbrixSolution(map);
			population.add(numbrixSolution);
		}

		GeneticAlgorithm<NumbrixSolution> genetic = new GeneticAlgorithm<>(population);
		
		NumbrixSolution finalSolution = genetic.execute();
		
		System.out.println(finalSolution);
	}

}

package br.uem.din.jf.genetic.chars;

import java.io.IOException;
import java.util.PriorityQueue;

import br.uem.din.jf.genetic.GeneticAlgorithm;

public class MainChars {

	public static void main(String[] args) throws IOException {
		PriorityQueue<Caracteres> initialPopulation = new PriorityQueue<>();
		
		Caracteres e = new Caracteres("Alain maadfasdfasr");
		initialPopulation.add(e);
		e = new Caracteres("Aadsfjaklsdj");
		initialPopulation.add(e);
		
		
		GeneticAlgorithm<Caracteres> ga = new GeneticAlgorithm<Caracteres>(initialPopulation);
		
		Caracteres execute = ga.execute();
		
		System.out.println("Melhor: "+ ((Caracteres)execute).getFound());
	}
	
}

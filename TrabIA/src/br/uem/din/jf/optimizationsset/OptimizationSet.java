package br.uem.din.jf.optimizationsset;

import static br.uem.din.jf.optimizationsset.AssemblyHeuristics.adaptationMeasure;
import static br.uem.din.jf.optimizationsset.Optimizations.TOTAL_OPTIMIZATIONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.uem.din.jf.genetic.Individual;

public class OptimizationSet extends Individual<OptimizationSet> {
	
	private String[] optimizations;
	private int adaptation;

	public OptimizationSet(String[] optimizations) {
		if (optimizations == null || optimizations.length == 0)
			throw new RuntimeException("Não é permitido instanciar um conjunto de otimizações vazio");
		
		this.optimizations = optimizations;
		
		adaptation = Integer.parseInt(""+adaptationMeasure(optimizations));
	}

	@Override
	public int getAdaptation() {
		return adaptation;
	}

	@Override
	public void mutate() {
		Random r = new Random();
		int qtdMudanca = r.nextInt(3);
		
		for (int i = 0; i < qtdMudanca; i++) {
			int posF = (int) (Math.random() * optimizations.length);
			int posC = (int) (Math.random() * TOTAL_OPTIMIZATIONS.length);
			optimizations[posF] = TOTAL_OPTIMIZATIONS[posC];
		}
		
		int qtdAdd = r.nextInt(2);
		
		for (int i = 0; i < qtdAdd; i++) {
			int posC = (int) (Math.random() * TOTAL_OPTIMIZATIONS.length);
			String[] newOptimizations = new String[optimizations.length + 1];
			for (int j = 0; j < newOptimizations.length-1; j++) {
				newOptimizations[j] = optimizations[j];
			}
			newOptimizations[newOptimizations.length-1] = TOTAL_OPTIMIZATIONS[posC];
			optimizations = newOptimizations;
		}
		
		int qtdRem = optimizations.length>1? 1 - qtdAdd : 0;
		
		for (int i = 0; i < qtdRem; i++) {
			String[] newOptimizations = new String[optimizations.length - 1];
			for (int j = 0; j < newOptimizations.length; j++) {
				newOptimizations[j] = optimizations[j];
			}
		}
		
		adaptation = Integer.parseInt(""+adaptationMeasure(optimizations));
	}

	@Override
	public boolean hasBestSolution() {
		return false;
	}

	@Override
	public OptimizationSet crossover(OptimizationSet y) {
		
		Random r = new Random();
		
		int index = (optimizations.length/2) -2;
		int corte1 = r.nextInt(index>-1? index + 1 : 1);
		int corte2 = r.nextInt(index> 0? index : 1) + optimizations.length/2;

		List<String> newOptimizations = new ArrayList<>();
		for (int i = 0; i < corte1; i++) {
			newOptimizations.add(optimizations[i]);
		}
		if (y.getOptimizations().length > corte1) {
			for (int i = corte1; i < corte2; i++) {
				if (i < y.getOptimizations().length)
					newOptimizations.add(y.getOptimizations()[i]);
			}
		}
		for (int i = corte2; i < optimizations.length; i++) {
			newOptimizations.add(optimizations[i]);
		}
		
		String[] sonOptimizations = newOptimizations.toArray(new String[0]);
		return new OptimizationSet(sonOptimizations);
	}

	private String[] getOptimizations() {
		return optimizations;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (String optimization : optimizations) {
			sb.append(" ").append(optimization);
		}
		return sb.toString();
	}

}

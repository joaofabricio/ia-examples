package br.uem.din.jf.genetic.chars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.uem.din.jf.genetic.Individual;

public class Caracteres extends Individual<Caracteres> {
	
	public static String SOLUTION = "Olá Mundo";
	
	public static String CHARS = "!,.:;?áÁãÃâÂõÕôÔóÓéêÉÊíQWERTYUIOPASDFGHJKLÇZXCVBNMqwertyuiopasdfghjklçzxcvbnm1234567890 ";
	
	public Caracteres(String found) {
		this.found = found;
	}
	
	private String found;

	@Override
	public int getAdaptation() {
		int adaptation = 0;
		for (int i = 0; i < SOLUTION.length(); i++) {
			if (found.length() > i && 
				found.charAt(i) == SOLUTION.charAt(i))
				adaptation++;
		}
		int difLength = Math.abs(found.length() - SOLUTION.length());
		adaptation -= difLength;
		return adaptation;
	}

	@Override
	public void mutate() {
		Random r = new Random();
		int qtdMudanca = r.nextInt(3);
		
		for (int i = 0; i < qtdMudanca; i++) {
			int posF = (int) (Math.random() * found.length());
			int posC = (int) (Math.random() * CHARS.length());
			found = found.substring(0, posF) +
					CHARS.substring(posC, posC+1) +
					found.substring(posF+1, found.length());
		}
		
		int qtdAdd = r.nextInt(2);
		
		for (int i = 0; i < qtdAdd; i++) {
			int posC = (int) (Math.random() * CHARS.length());
			found += CHARS.substring(posC, posC+1);
		}
		
		int qtdRem = r.nextInt(2);
		
		for (int i = 0; i < qtdRem; i++) {
			found = found.substring(0, found.length()-1);
		}
	}
	
	@Override
	public List<Caracteres> reproduction(Caracteres y) {
		Random r = new Random();
		
		int corte1 = r.nextInt((this.getFound().length()/2) -2 + 1);
		int corte2 = r.nextInt((this.getFound().length()/2) -2) + this.getFound().length()/2;
		
		String newFound = this.getFound().substring(0, corte1); 
		newFound += corte2<=y.getFound().length()? y.getFound().substring(corte1, corte2) : y.getFound();
		newFound += this.getFound().substring(corte2, this.getFound().length());
		
		List<Caracteres> list = new ArrayList<>();
		list.add(new Caracteres(newFound));
		return list;
		
		
	}

	public String getFound() {
		return found;
	}

	@Override
	public boolean hasBestSolution() {
		return getFound().equals(SOLUTION);
	}
	
	@Override
	public String toString() {
		return found;
	}

}

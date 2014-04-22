package br.uem.din.jf.main;

import java.io.File;

import br.uem.din.jf.astar.AStar;
import br.uem.din.jf.astar.NumbrixSolution;
import br.uem.din.jf.numbrix.Map;
import br.uem.din.jf.numbrix.NumbrixUtils;
import br.uem.din.jf.util.FileUtils;

public class NumbrixMainAstar {

	public static void main(String[] args) {
		AStar<NumbrixSolution> astar = new AStar<>();

		String numbrixStr = FileUtils.getFileContents(new File(args[0]));
		Map initialMap = NumbrixUtils.extractMap(numbrixStr);
		NumbrixSolution finalSolution = astar.execute(new NumbrixSolution(initialMap));
		
		System.out.println(finalSolution);
	}

}

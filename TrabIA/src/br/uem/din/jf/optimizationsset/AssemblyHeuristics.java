package br.uem.din.jf.optimizationsset;

import br.uem.din.jf.util.StringUtils;

public class AssemblyHeuristics {
	
	public static final Integer INITIAL_ADAPTATION = Props.getInstance().getInitialAdaptation();
	private static final int PUSH_MULT = Props.getInstance().getPushWeight();
	private static final int POP_MULT = Props.getInstance().getPopWeight();
	private static final int JMP_MULT = Props.getInstance().getJmpWeight();
	private static final int SIZE_WEIGHT = Props.getInstance().getSizeWeight();;

	public static int adaptationMeasure(String[] optimizations) {
		
		String assembly = Shell.getAssembly(optimizations);
		
		int pushWeight = StringUtils.countOcurrrences(assembly, "push") * PUSH_MULT;
		int popWeight = StringUtils.countOcurrrences(assembly, "pop") * POP_MULT;
		int jmpWeight = StringUtils.countMoreOcurrrences(assembly, "jmp", "jl", "je", "jne", "jg") 
							* JMP_MULT;
		int sizeWeight = assembly.length() * SIZE_WEIGHT;
		return INITIAL_ADAPTATION - pushWeight - popWeight - jmpWeight - sizeWeight;
	}

}

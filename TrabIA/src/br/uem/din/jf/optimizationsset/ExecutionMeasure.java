package br.uem.din.jf.optimizationsset;

public class ExecutionMeasure {
	
	public static final Integer INITIAL_ADAPTATION = Props.getInstance().getInitialAdaptation();

	public static Long adaptationMeasure(String[] optimizations) {
		
		return INITIAL_ADAPTATION - Shell.getExecutionTime(optimizations);
	}

}

package by.vsu.mf.amm.sta.console.visualizer;

import by.vsu.mf.amm.sta.method.Method;
import by.vsu.mf.amm.sta.problem.Problem;

public interface ProblemVisualizer<P extends Problem, M extends Method<P>> {
	void output(P problem, M method);
}

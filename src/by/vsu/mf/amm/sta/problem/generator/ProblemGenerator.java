package by.vsu.mf.amm.sta.problem.generator;

import by.vsu.mf.amm.sta.method.Method;
import by.vsu.mf.amm.sta.problem.Problem;

public interface ProblemGenerator<P extends Problem, M extends Method<P>> {
	void generate(P problem, M method);
}

package by.vsu.mf.amm.sta.method;

import java.util.ArrayList;
import java.util.List;

import by.vsu.mf.amm.sta.Entity;
import by.vsu.mf.amm.sta.exception.operation.OperationException;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.problem.Problem;

abstract public class Method<P extends Problem> extends Entity {
	private P problem;
	private boolean solutionFinished;

	private P startProblem;
	private List<Operation<P, Method<P>>> history = new ArrayList<>();

	public P getProblem() {
		return problem;
	}

	public void setProblem(P problem) {
		this.problem = problem;
		this.startProblem = problem;
	}

	public boolean isSolutionFinished() {
		return solutionFinished;
	}

	@SuppressWarnings("unchecked")
	public P getStartProblem() {
		return (P)startProblem.clone();
	}

	@SuppressWarnings("unchecked")
	public List<Operation<P, Method<P>>> getHistory() {
		List<Operation<P, Method<P>>> copy = new ArrayList<>();
		for(Operation<P, Method<P>> operation : history) {
			copy.add((Operation<P, Method<P>>)operation.clone());
		}
		return copy;
	}

	public void setSolutionFinished(boolean solutionFinished) {
		this.solutionFinished = solutionFinished;
	}

	public void execute(Operation<P, Method<P>> operation) throws OperationException {
		operation.setMethod(this);
		operation.execute();
		history.add(operation);
	}
}

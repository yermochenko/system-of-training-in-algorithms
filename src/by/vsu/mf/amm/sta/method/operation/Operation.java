package by.vsu.mf.amm.sta.method.operation;

import by.vsu.mf.amm.sta.Entity;
import by.vsu.mf.amm.sta.exception.operation.OperationException;
import by.vsu.mf.amm.sta.method.Method;
import by.vsu.mf.amm.sta.problem.Problem;

abstract public class Operation<P extends Problem, M extends Method<P>> extends Entity {
	private M method;

	public M getMethod() {
		return method;
	}

	public void setMethod(M method) {
		this.method = method;
	}

	abstract public void execute() throws OperationException;
}

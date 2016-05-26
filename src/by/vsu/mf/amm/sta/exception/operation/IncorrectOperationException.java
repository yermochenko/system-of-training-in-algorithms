package by.vsu.mf.amm.sta.exception.operation;

import by.vsu.mf.amm.sta.method.operation.Operation;

public class IncorrectOperationException extends OperationException {
	public IncorrectOperationException(Operation<?, ?> operation) {
		super(operation);
	}
}

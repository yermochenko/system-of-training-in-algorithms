package by.vsu.mf.amm.sta.exception.operation;

import by.vsu.mf.amm.sta.method.operation.Operation;

public class OperationException extends Exception {
	private Operation<?, ?> operation;

	public OperationException(Operation<?, ?> operation) {
		this.operation = operation;
	}

	public OperationException(Operation<?, ?> operation, String message, Throwable exception) {
		super(message, exception);
		this.operation = operation;
	}

	public OperationException(Operation<?, ?> operation, String message) {
		super(message);
		this.operation = operation;
	}

	public OperationException(Operation<?, ?> operation, Throwable exception) {
		super(exception);
		this.operation = operation;
	}

	public Operation<?, ?> getOperation() {
		return operation;
	}
}

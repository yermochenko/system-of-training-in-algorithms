package by.vsu.mf.amm.sta.exception.operation;

import by.vsu.mf.amm.sta.method.operation.Operation;

public class IncorrectOperandOperationException extends OperationException {
	private int index;

	public IncorrectOperandOperationException(Operation<?, ?> operation, int index) {
		super(operation);
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}

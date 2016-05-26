package by.vsu.mf.amm.sta.exception.operation;

import by.vsu.mf.amm.sta.method.operation.Operation;

public class MatrixSizeException extends OperationException {
	private IndexType indexType;
	private int value;
	private int correctValue;

	public MatrixSizeException(Operation<?, ?> operation, IndexType indexType, int value, int correctValue) {
		super(operation);
		this.indexType = indexType;
		this.value = value;
		this.correctValue = correctValue;
	}

	public IndexType getIndexType() {
		return indexType;
	}

	public int getValue() {
		return value;
	}

	public int getCorrectValue() {
		return correctValue;
	}

	public enum IndexType {
		ROW, COLUMN;
	}
}

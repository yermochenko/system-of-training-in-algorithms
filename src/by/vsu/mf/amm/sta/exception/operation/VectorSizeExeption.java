package by.vsu.mf.amm.sta.exception.operation;

import by.vsu.mf.amm.sta.exception.operation.MatrixSizeException.IndexType;
import by.vsu.mf.amm.sta.method.operation.Operation;

public class VectorSizeExeption extends OperationException {

    private int value;
    private int correctValue;

    public VectorSizeExeption(Operation<?, ?> operation, int value, int correctValue) {
        super(operation);
        this.value = value;
        this.correctValue = correctValue;

    }

    public int getValue() {
        return value;
    }

    public int getCorrectValue() {
        return correctValue;
    }

}

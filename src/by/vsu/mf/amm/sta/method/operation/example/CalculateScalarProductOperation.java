package by.vsu.mf.amm.sta.method.operation.example;

import by.vsu.mf.amm.sta.exception.operation.IncorrectOperandOperationException;
import by.vsu.mf.amm.sta.exception.operation.IncorrectOperationException;
import by.vsu.mf.amm.sta.exception.operation.OperationException;
import by.vsu.mf.amm.sta.method.example.MatrixMultiplicationMethod;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.problem.example.MatrixMultiplicationProblem;

public class CalculateScalarProductOperation extends Operation<MatrixMultiplicationProblem, MatrixMultiplicationMethod> {
	private VectorFromMatrixType firstVectorFromMatrixType;
	private int firstVectorIndex;
	private VectorFromMatrixType secondVectorFromMatrixType;
	private int secondVectorIndex;
	private int resultRowIndex;
	private int resultColumnIndex;

	@Override
	public void execute() throws OperationException {
		MatrixMultiplicationMethod method = getMethod();
		MatrixMultiplicationProblem problem = method.getProblem();
		if(problem.getResult() != null && !problem.isSolved()) {
			if(firstVectorFromMatrixType == VectorFromMatrixType.ROW && firstVectorIndex == method.getCurrentRowIndex() && firstVectorIndex == resultRowIndex) {
				if(secondVectorFromMatrixType == VectorFromMatrixType.COLUMN && secondVectorIndex == method.getCurrentColumnIndex() && secondVectorIndex == resultColumnIndex) {
					double value = 0;
					for(int index = 0; index < problem.getFirstOperand().columnsQuantity(); index++) {
						value += problem.getFirstOperand().get(resultRowIndex, index) * problem.getSecondOperand().get(index, resultColumnIndex);
					}
					problem.getResult().set(resultRowIndex, resultColumnIndex, value);
					method.setCurrentColumnIndex(method.getCurrentColumnIndex() + 1);
					if(method.getCurrentColumnIndex() == problem.getResult().columnsQuantity()) {
						method.setCurrentColumnIndex(0);
						method.setCurrentRowIndex(method.getCurrentRowIndex() + 1);
						if(method.getCurrentRowIndex() == problem.getResult().rowsQuantity()) {
							method.setSolutionFinished(true);
							problem.setSolved(true);
						}
					}
				} else {
					throw new IncorrectOperandOperationException(this, 2);
				}
			} else {
				throw new IncorrectOperandOperationException(this, 1);
			}
		} else {
			throw new IncorrectOperationException(this);
		}
	}

	public void setFirstVectorFromMatrixType(VectorFromMatrixType firstVectorFromMatrixType) {
		this.firstVectorFromMatrixType = firstVectorFromMatrixType;
	}

	public void setFirstVectorIndex(int firstVectorIndex) {
		this.firstVectorIndex = firstVectorIndex;
	}

	public void setSecondVectorFromMatrixType(VectorFromMatrixType secondVectorFromMatrixType) {
		this.secondVectorFromMatrixType = secondVectorFromMatrixType;
	}

	public void setSecondVectorIndex(int secondVectorIndex) {
		this.secondVectorIndex = secondVectorIndex;
	}

	public void setResultRowIndex(int resultRowIndex) {
		this.resultRowIndex = resultRowIndex;
	}

	public void setResultColumnIndex(int resultColumnIndex) {
		this.resultColumnIndex = resultColumnIndex;
	}

	public static enum VectorFromMatrixType {
		ROW, COLUMN;
	}
}

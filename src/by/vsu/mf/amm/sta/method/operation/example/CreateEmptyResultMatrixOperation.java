package by.vsu.mf.amm.sta.method.operation.example;

import by.vsu.mf.amm.sta.exception.operation.IncorrectOperationException;
import by.vsu.mf.amm.sta.exception.operation.MatrixSizeException;
import by.vsu.mf.amm.sta.exception.operation.OperationException;
import by.vsu.mf.amm.sta.exception.operation.MatrixSizeException.IndexType;
import by.vsu.mf.amm.sta.math.object.UsualMatrix;
import by.vsu.mf.amm.sta.method.example.MatrixMultiplicationMethod;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.problem.example.MatrixMultiplicationProblem;

public class CreateEmptyResultMatrixOperation extends Operation<MatrixMultiplicationProblem, MatrixMultiplicationMethod> {
	private int rowsQuantity;
	private int columnsQuantity;

	public void setRowsQuantity(int rowsQuantity) {
		this.rowsQuantity = rowsQuantity;
	}

	public void setColumnsQuantity(int columnsQuantity) {
		this.columnsQuantity = columnsQuantity;
	}

	@Override
	public void execute() throws OperationException {
		MatrixMultiplicationMethod method = getMethod();
		MatrixMultiplicationProblem problem = method.getProblem();
		if(problem.getResult() == null) {
			if(problem.getFirstOperand().columnsQuantity() == problem.getSecondOperand().rowsQuantity()) {
				if(rowsQuantity == problem.getFirstOperand().rowsQuantity() && columnsQuantity == problem.getSecondOperand().columnsQuantity()) {
					problem.setResult(new UsualMatrix(rowsQuantity, columnsQuantity));
					method.setCurrentRowIndex(0);
					method.setCurrentColumnIndex(0);
				} else {
					if(rowsQuantity != problem.getFirstOperand().rowsQuantity()) {
						throw new MatrixSizeException(this, IndexType.ROW, rowsQuantity, problem.getFirstOperand().rowsQuantity());
					}
					if(columnsQuantity != problem.getSecondOperand().columnsQuantity()) {
						throw new MatrixSizeException(this, IndexType.COLUMN, columnsQuantity, problem.getSecondOperand().columnsQuantity());
					}
				}
			} else {
				if(rowsQuantity != 0) {
					throw new MatrixSizeException(this, IndexType.ROW, rowsQuantity, 0);
				}
				if(columnsQuantity != 0) {
					throw new MatrixSizeException(this, IndexType.COLUMN, columnsQuantity, 0);
				}
				method.setSolutionFinished(true);
			}
		} else {
			throw new IncorrectOperationException(this);
		}
	}
}

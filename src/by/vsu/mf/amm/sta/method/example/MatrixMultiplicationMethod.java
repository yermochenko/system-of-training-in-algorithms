package by.vsu.mf.amm.sta.method.example;

import by.vsu.mf.amm.sta.method.Method;
import by.vsu.mf.amm.sta.problem.example.MatrixMultiplicationProblem;

public class MatrixMultiplicationMethod extends Method<MatrixMultiplicationProblem> {
	private Integer currentRowIndex;
	private Integer currentColumnIndex;

	public Integer getCurrentRowIndex() {
		return currentRowIndex;
	}

	public void setCurrentRowIndex(Integer currentRowIndex) {
		this.currentRowIndex = currentRowIndex;
	}

	public Integer getCurrentColumnIndex() {
		return currentColumnIndex;
	}

	public void setCurrentColumnIndex(Integer currentColumnIndex) {
		this.currentColumnIndex = currentColumnIndex;
	}
}

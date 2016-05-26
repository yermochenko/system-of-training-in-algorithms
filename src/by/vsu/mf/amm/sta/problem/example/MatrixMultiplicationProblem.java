package by.vsu.mf.amm.sta.problem.example;

import by.vsu.mf.amm.sta.math.object.Matrix;
import by.vsu.mf.amm.sta.math.object.MutableMatrix;
import by.vsu.mf.amm.sta.problem.Problem;

public class MatrixMultiplicationProblem extends Problem {
	private Matrix firstOperand;
	private Matrix secondOperand;
	private MutableMatrix result;

	public Matrix getFirstOperand() {
		return firstOperand;
	}

	public void setFirstOperand(Matrix firstOperand) {
		this.firstOperand = firstOperand;
	}

	public Matrix getSecondOperand() {
		return secondOperand;
	}

	public void setSecondOperand(Matrix secondOperand) {
		this.secondOperand = secondOperand;
	}

	public MutableMatrix getResult() {
		return result;
	}

	public void setResult(MutableMatrix result) {
		this.result = result;
	}
}

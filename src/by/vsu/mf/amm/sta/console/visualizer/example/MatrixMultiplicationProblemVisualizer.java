package by.vsu.mf.amm.sta.console.visualizer.example;

import by.vsu.mf.amm.sta.console.visualizer.ProblemVisualizer;
import by.vsu.mf.amm.sta.math.object.Matrix;
import by.vsu.mf.amm.sta.method.example.MatrixMultiplicationMethod;
import by.vsu.mf.amm.sta.problem.example.MatrixMultiplicationProblem;

public class MatrixMultiplicationProblemVisualizer implements ProblemVisualizer<MatrixMultiplicationProblem, MatrixMultiplicationMethod> {
	@Override
	public void output(MatrixMultiplicationProblem problem, MatrixMultiplicationMethod method) {
		Matrix firstOperand = problem.getFirstOperand();
		Matrix secondOperand = problem.getSecondOperand();
		Matrix result = problem.getResult();
		System.out.print("Умножение марицы ");
		output(firstOperand);
		System.out.print("на матрицу ");
		output(secondOperand);
		if(result != null) {
			System.out.print("Текущая матрица-результата ");
			output(result);
		}
	}

	private void output(Matrix matrix) {
		System.out.println(matrix.rowsQuantity() + " на " + matrix.columnsQuantity() + " элементов:");
		for(int i = 0; i < matrix.rowsQuantity(); i++) {
			for(int j = 0; j < matrix.columnsQuantity(); j++) {
				System.out.print(matrix.get(i, j) + "\t");
			}
			System.out.println();
		}
	}
}

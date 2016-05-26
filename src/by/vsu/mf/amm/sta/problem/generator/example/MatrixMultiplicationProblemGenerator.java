package by.vsu.mf.amm.sta.problem.generator.example;

import by.vsu.mf.amm.sta.math.object.Matrix;
import by.vsu.mf.amm.sta.math.object.UsualMatrix;
import by.vsu.mf.amm.sta.math.random.Random;
import by.vsu.mf.amm.sta.method.example.MatrixMultiplicationMethod;
import by.vsu.mf.amm.sta.problem.example.MatrixMultiplicationProblem;
import by.vsu.mf.amm.sta.problem.generator.ProblemGenerator;

/**
 * Генерирует две матрицы для задачи перемножения двух матриц.
 * При этом с заданной вероятностью размеры матриц будут согласованны по размеру для перемножения
 * @author yermochenko
 */
public class MatrixMultiplicationProblemGenerator implements ProblemGenerator<MatrixMultiplicationProblem, MatrixMultiplicationMethod> {
	private double probability = 1;
	private int minSize = 3;
	private int maxSize = 5;
	private double minValue = -10;
	private double maxValue = 10;
	private int possibleValuesQuantity = 40;

	@Override
	public void generate(MatrixMultiplicationProblem problem, MatrixMultiplicationMethod method) {
		if(minSize > 0) {
			if(minSize <= maxSize) {
				Random random = new Random();
				int firstMatrixRowsQuantity;
				int firstMatrixColumnsQuantity;
				int secondMatrixRowsQuantity;
				int secondMatrixColumnsQuantity;
				firstMatrixRowsQuantity = random.nextInt(minSize, maxSize);
				firstMatrixColumnsQuantity = secondMatrixRowsQuantity = random.nextInt(minSize, maxSize);
				secondMatrixColumnsQuantity = random.nextInt(minSize, maxSize);
				if(random.nextDouble() < 1 - probability) {
					int delta = Math.max(maxSize - firstMatrixColumnsQuantity, firstMatrixColumnsQuantity - minSize);
					if(random.nextBoolean()) {
						firstMatrixColumnsQuantity += delta;
					} else {
						secondMatrixRowsQuantity += delta;
					}
				}
				problem.setFirstOperand(generateMatrix(random, firstMatrixRowsQuantity, firstMatrixColumnsQuantity));
				problem.setSecondOperand(generateMatrix(random, secondMatrixRowsQuantity, secondMatrixColumnsQuantity));
			} else {
				throw new IllegalStateException("maxSize can't be less than minSize");
			}
		} else {
			throw new IllegalStateException("minSize is to be positive");
		}
	}

	private Matrix generateMatrix(Random random, int rowsQuantity, int columnsQuantity) {
		UsualMatrix matrix = new UsualMatrix(rowsQuantity, columnsQuantity);
		double delta = (maxValue - minValue) / possibleValuesQuantity;
		for(int i = 0; i < rowsQuantity; i++) {
			for(int j = 0; j < columnsQuantity; j++) {
				matrix.set(i, j, minValue + delta * random.nextInt(possibleValuesQuantity + 1));
			}
		}
		return matrix;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public int getPossibleValuesQuantity() {
		return possibleValuesQuantity;
	}

	public void setPossibleValuesQuantity(int possibleValuesQuantity) {
		this.possibleValuesQuantity = possibleValuesQuantity;
	}
}

package by.vsu.mf.amm.sta.problem.generator.SystemOfLinearEquationsProblemGenerators;

import by.vsu.mf.amm.sta.math.object.Matrix;
import by.vsu.mf.amm.sta.math.object.UsualMatrix;
import by.vsu.mf.amm.sta.math.object.Vector;
import by.vsu.mf.amm.sta.math.random.Random;
import by.vsu.mf.amm.sta.method.systemOfLinearEquationsProblemMethods.seidelAlgorithm.SeidelAlgorithm;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblemSeidelMethod;
import by.vsu.mf.amm.sta.problem.generator.ProblemGenerator;

public class SystemOfLinearEquationsSeidelProblemGenerator
        implements ProblemGenerator<SystemOfLinearEquationsProblemSeidelMethod, SeidelAlgorithm> {

    private double probability = 1;
    private int minSize = 3;
    private int maxSize = 5;
    private double minValue = -10;
    private double maxValue = 10;
    private int possibleValuesQuantity = 40;

    @Override
    public void generate(SystemOfLinearEquationsProblemSeidelMethod problem, SeidelAlgorithm method) {
        if (minSize > 0) {
            if (minSize <= maxSize) {
                Random random = new Random();
                int quantity = random.nextInt(minSize, maxSize);

                problem.setMatrix(generateMatrix(random, quantity));
                problem.setFreeMembers(generateVector(random, quantity));

                if (!isDiagonallyDominantMatrix(problem.getMatrix()) && random.nextDouble() < probability) {
                    toDiagonallyDominantMatrix(random, problem.getMatrix());
                    method.setDiagonallyDominantMatrix(true);
                } else if (isDiagonallyDominantMatrix(problem.getMatrix()))
                    method.setDiagonallyDominantMatrix(true);
            } else {
                throw new IllegalStateException("maxSize can't be less than minSize");
            }
        } else {
            throw new IllegalStateException("minSize is to be positive");
        }
    }

    private Matrix generateMatrix(Random random, int quantity) {
        UsualMatrix matrix = new UsualMatrix(quantity, quantity);
        double delta = (maxValue - minValue) / possibleValuesQuantity;
        // generate main diagonal
        for (int i = 0; i < quantity; i++) {
            for (int j = 0; j < quantity; j++) {
                matrix.set(i, j, minValue + delta * random.nextInt(possibleValuesQuantity + 1));
            }
        }

        return matrix;
    }

    private Vector generateVector(Random random, int quantity) {
        Vector vector = new Vector(quantity);
        double delta = (maxValue - minValue) / possibleValuesQuantity;

        for (int i = 0; i < quantity; i++) {
            vector.set(i, minValue + delta * random.nextInt(possibleValuesQuantity + 1));
        }

        return vector;
    }

    private boolean isDiagonallyDominantMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.rowsQuantity(); i++) {
            double sum = 0;
            for (int j = 0; j < matrix.columnsQuantity(); j++) {
                if (i != j)
                    sum += Math.abs(matrix.get(i, j));
            }
            if (matrix.get(i, i) <= sum)
                return false;
        }

        return true;

    }

    private void toDiagonallyDominantMatrix(Random random, Matrix matrix) {
        while (!isDiagonallyDominantMatrix(matrix)) {
            for (int i = 0; i < matrix.rowsQuantity(); i++) {
                ((UsualMatrix) matrix).set(i, i, minValue + random.nextInt(possibleValuesQuantity + 1));
            }
        }

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

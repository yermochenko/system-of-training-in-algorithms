package by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.seidelAlgorithmOperations;

import by.vsu.mf.amm.sta.exception.operation.IncorrectOperationException;
import by.vsu.mf.amm.sta.exception.operation.OperationException;
import by.vsu.mf.amm.sta.method.systemOfLinearEquationsProblemMethods.seidelAlgorithm.SeidelAlgorithm;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblemSeidelMethod;
import by.vsu.mf.amm.sta.math.object.UsualMatrix;
import by.vsu.mf.amm.sta.math.object.Vector;

public class CalculateSeidelMethodOperation
        extends Operation<SystemOfLinearEquationsProblemSeidelMethod, SeidelAlgorithm> {

    private int accuracy;

    public int setAccuracy(int accuracy) {
        return this.accuracy = accuracy;
    }

    @Override
    public void execute() throws OperationException {
        SeidelAlgorithm method = getMethod();
        SystemOfLinearEquationsProblemSeidelMethod problem = method.getProblem();

        if (problem.getMatrix() != null && problem.getFreeMembers() != null) {

            method.setAccuracy(accuracy);
            method.setQuantity(problem.getMatrix().rowsQuantity());
            findSeidelMethod(problem, method);
            method.setSolutionFinished(true);
            problem.setSolved(true);
        } else {
            throw new IncorrectOperationException(this);
        }
    }

    private void findSeidelMethod(SystemOfLinearEquationsProblemSeidelMethod problem, SeidelAlgorithm method) {

        method.createMatrixB();
        method.createVectorAppoximate();
        method.createVectorG();

        Vector temp = new Vector(problem.getMatrix().rowsQuantity());
        for (int i = 0; i < problem.getMatrix().rowsQuantity(); i++) {
            for (int j = 0; j < problem.getMatrix().rowsQuantity(); j++) {
                if (i == j) {
                    method.getMatrixB().set(i, j, 0);
                } else {
                    method.getMatrixB().set(i, j, -problem.getMatrix().get(i, j) / problem.getMatrix().get(i, i));
                }
            }
            method.getVectorG().set(i, problem.getFreeMembers().get(i) / problem.getMatrix().get(i, i));
            method.getApproximateVector().set(i, method.getVectorG().get(i));
        }

        int k = 0;
        double tmp = 1;
        double accuracy = 1 / Math.pow(10, method.getAccuracy());
        do {
            if (k == problem.getMatrix().rowsQuantity()) {
                tmp = max(problem.getResult(), temp, problem.getMatrix().rowsQuantity());
                k = 0;
            } else {
                problem.getResult().set(k, sum(method.getMatrixB(), method.getApproximateVector(), method.getVectorG(),
                        k, problem.getMatrix().rowsQuantity()));
                temp.set(k, method.getApproximateVector().get(k));
                method.getApproximateVector().set(k, problem.getResult().get(k));
                k++;
            }
        } while (accuracy < tmp);

    }

    private double max(Vector freeMembers, Vector approximateVector, int currentRowIndex) {
        double max = 1;
        for (int i = 0; i < currentRowIndex; i++) {
            double tmp;
            tmp = Math.abs(freeMembers.get(i) - approximateVector.get(i));
            if (tmp < max)
                max = tmp;
        }
        return max;
    }

    private double sum(UsualMatrix matrixB, Vector ApproximateVector, Vector vectorG, int k, int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += matrixB.get(k, i) * ApproximateVector.get(i);
        }
        return sum + vectorG.get(k);
    }
}

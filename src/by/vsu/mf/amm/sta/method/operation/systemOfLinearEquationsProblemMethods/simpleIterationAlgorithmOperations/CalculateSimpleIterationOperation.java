package by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.simpleIterationAlgorithmOperations;

import by.vsu.mf.amm.sta.exception.operation.IncorrectOperationException;
import by.vsu.mf.amm.sta.exception.operation.OperationException;
import by.vsu.mf.amm.sta.math.object.Vector;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.method.systemOfLinearEquationsProblemMethods.simpleIteration.SimpleIteration;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblemSimpleIterationMethod;

public class CalculateSimpleIterationOperation
        extends Operation<SystemOfLinearEquationsProblemSimpleIterationMethod, SimpleIteration> {

    private int accuracy;

    public int setAccuracy(int accuracy) {
        return this.accuracy = accuracy;
    }

    @Override
    public void execute() throws OperationException {
        SimpleIteration method = getMethod();
        SystemOfLinearEquationsProblemSimpleIterationMethod problem = method.getProblem();

        if (problem.getMatrix() != null && problem.getFreeMembers() != null) {

            method.setAccuracy(accuracy);
            method.setQuantity(problem.getMatrix().rowsQuantity());
            findSimpleIterationMethod(problem, method);
            method.setSolutionFinished(true);
            problem.setSolved(true);
        } else {
            throw new IncorrectOperationException(this);
        }
    }

    private void findSimpleIterationMethod(SystemOfLinearEquationsProblemSimpleIterationMethod problem, SimpleIteration method) {

        method.createMatrixB();
        method.createVectorAppoximate();
        method.createVectorG();

        for (int i = 0; i < problem.getMatrix().rowsQuantity(); i++) {
            for (int j = 0; j < problem.getMatrix().rowsQuantity(); j++) {
                if (i == j) {
                    method.getMatrixB().set(i, j, 0);
                } else {
                    method.getMatrixB().set(i, j, -problem.getMatrix().get(i, j) / problem.getMatrix().get(i, i));
                }
            }
            method.getVectorG().set(i, problem.getFreeMembers().get(i) / problem.getMatrix().get(i, i));
        }

        double maX = 0;
        double accuracy = 1 / Math.pow(10, method.getAccuracy());
        for (int i = 0; i < problem.getMatrix().rowsQuantity(); i++) {
            method.getApproximateVector().set(i, method.getVectorG().get(i));
        }
        do {

            double summ = 0;
            for (int i = 0; i < problem.getMatrix().rowsQuantity(); i++) {
                for (int j = 0; j < problem.getMatrix().rowsQuantity(); j++) {
                    summ += method.getMatrixB().get(i, j) * method.getApproximateVector().get(j);
                }
                problem.getResult().set(i, summ + method.getVectorG().get(i));
                summ = 0;
            }
            maX = max(method.getApproximateVector(), problem.getResult(), problem.getMatrix().rowsQuantity());
            for (int i = 0; i < problem.getMatrix().rowsQuantity(); i++) {
                method.getApproximateVector().set(i, problem.getResult().get(i));
            }
        } while (maX > accuracy);

    }
    
    public double max(Vector X1, Vector X, int quantity) {
        double max = 1;        
        for (int i = 0; i < quantity; i++) {
            double tmp;
            tmp = Math.abs(X1.get(i) - X.get(i));
            if (tmp < max) {
                max = tmp;
            }
        }
        return max;
    }
}

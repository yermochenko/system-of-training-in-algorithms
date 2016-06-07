package by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.tridiagonalMatrixAlgorithmOperations;

import by.vsu.mf.amm.sta.exception.operation.IncorrectOperandOperationException;
import by.vsu.mf.amm.sta.exception.operation.IncorrectOperationException;
import by.vsu.mf.amm.sta.exception.operation.OperationException;
import by.vsu.mf.amm.sta.method.systemOfLinearEquationsProblemMethods.TridiagonalMatrixAlgorithm;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblem;

public class CalculateMethodCoefficientsOperation extends Operation<SystemOfLinearEquationsProblem, TridiagonalMatrixAlgorithm> {

    private int coefficientsIndex;

    @Override
    public void execute() throws OperationException {

        TridiagonalMatrixAlgorithm method = getMethod();
        SystemOfLinearEquationsProblem problem = method.getProblem();

        if (method.getMethodCoefficientsA() != null && method.getMethodCoefficientsB() != null
                && !method.isCoefficientsFound()) {
            if (coefficientsIndex == method.getCurrentCoefficientA()) {
                method.getMethodCoefficientsA().set(coefficientsIndex, findCoefficientA(problem, method));
                method.getMethodCoefficientsB().set(coefficientsIndex, findCoefficientB(problem, method));

                method.setCurrentCoefficientA(method.getCurrentCoefficientA() + 1);
                method.setCurrentCoefficientB(method.getCurrentCoefficientB() + 1);

                if (method.getCurrentCoefficientA() == method.getMethodCoefficientsA().getQuantity()
                        && method.getCurrentCoefficientB() == method.getMethodCoefficientsB().getQuantity()) {

                    method.setCoefficientsFound(true);
                }
            } else {
                throw new IncorrectOperandOperationException(this, 1);
            }

        } else {

            throw new IncorrectOperationException(this);
        }
    }

    public void setCoefficientsIndex(int coefficientsIndex) {
        this.coefficientsIndex = coefficientsIndex;
    }

    private double roundValue(double value) {
        return Math.rint(value * 100) / 100;
    }

    private double findCoefficientA(SystemOfLinearEquationsProblem problem, TridiagonalMatrixAlgorithm method) {
        double valueOfCoefficientA;

        if (coefficientsIndex == 0) {
            valueOfCoefficientA = -(problem.getMatrix().get(coefficientsIndex, coefficientsIndex + 1)
                    / problem.getMatrix().get(coefficientsIndex, coefficientsIndex));
        } else {
            valueOfCoefficientA = -(problem.getMatrix().get(coefficientsIndex, coefficientsIndex + 1)
                    / (problem.getMatrix().get(coefficientsIndex, coefficientsIndex)
                    + problem.getMatrix().get(coefficientsIndex, coefficientsIndex - 1)
                    * method.getMethodCoefficientsA().get(coefficientsIndex - 1)));
        }

        return roundValue(valueOfCoefficientA);
    }

    private double findCoefficientB(SystemOfLinearEquationsProblem problem, TridiagonalMatrixAlgorithm method) {
        double valueOfCoefficientB;

        if (coefficientsIndex == 0) {
            valueOfCoefficientB = problem.getFreeMembers().get(coefficientsIndex)
                    / problem.getMatrix().get(coefficientsIndex, coefficientsIndex);
        } else {
            valueOfCoefficientB = (problem.getFreeMembers().get(coefficientsIndex)
                    - problem.getMatrix().get(coefficientsIndex, coefficientsIndex - 1)
                    * method.getMethodCoefficientsB().get(coefficientsIndex))
                    / (problem.getMatrix().get(coefficientsIndex, coefficientsIndex)
                    + problem.getMatrix().get(coefficientsIndex, coefficientsIndex - 1)
                    * method.getMethodCoefficientsA().get(coefficientsIndex - 1));
        }

        return roundValue(valueOfCoefficientB);
    }
}

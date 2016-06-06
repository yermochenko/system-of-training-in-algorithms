package by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.tridiagonalMatrixAlgorithmOperations;

import by.vsu.mf.amm.sta.exception.operation.IncorrectOperandOperationException;
import by.vsu.mf.amm.sta.exception.operation.IncorrectOperationException;
import by.vsu.mf.amm.sta.exception.operation.OperationException;
import by.vsu.mf.amm.sta.method.systemOfLinearEquationsProblemMethods.TridiagonalMatrixAlgorithm;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblem;

public class CalculateResultVectorOperation extends Operation<SystemOfLinearEquationsProblem, TridiagonalMatrixAlgorithm> {

    private int index;

    @Override
    public void execute() throws OperationException {
        TridiagonalMatrixAlgorithm method = getMethod();
        SystemOfLinearEquationsProblem problem = method.getProblem();

        if (problem.getFreeMembers() != null && !problem.isSolved() && method.isCoefficientsFound()) {
            if (index == method.getCurrentUnknownX()) {

                problem.getResult().set(index, findCurrentX(problem, method));
                method.setCurrentUnknownX(method.getCurrentUnknownX() - 1);

                if (method.getCurrentUnknownX() == -1) {
                    method.setSolutionFinished(true);
                    problem.setSolved(true);
                }
            } else {
                throw new IncorrectOperandOperationException(this, 1);
            }
        } else {
            throw new IncorrectOperationException(this);
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private double roundValue(double value) {
        return Math.rint(value * 100) / 100;
    }

    private double findCurrentX(SystemOfLinearEquationsProblem problem, TridiagonalMatrixAlgorithm method) {
        double valueOfCurrentX;

        if (index == problem.getResult().getQuantity() - 1) {

            valueOfCurrentX = (problem.getFreeMembers().get(index)
                    - problem.getMatrix().get(index, index - 1) * method.getMethodCoefficientsB().get(index - 1))
                    / (problem.getMatrix().get(index, index) + problem.getMatrix().get(index, index - 1)
                    * method.getMethodCoefficientsA().get(index - 1));
        } else {
            valueOfCurrentX = method.getMethodCoefficientsA().get(index) * problem.getResult().get(index + 1)
                    + method.getMethodCoefficientsB().get(index);
        }

        return roundValue(valueOfCurrentX);
    }
}

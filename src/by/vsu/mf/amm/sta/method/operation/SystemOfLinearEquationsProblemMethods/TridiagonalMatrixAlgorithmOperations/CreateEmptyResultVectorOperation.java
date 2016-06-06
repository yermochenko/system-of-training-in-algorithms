package by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.tridiagonalMatrixAlgorithmOperations;

import by.vsu.mf.amm.sta.exception.operation.IncorrectOperationException;
import by.vsu.mf.amm.sta.exception.operation.OperationException;
import by.vsu.mf.amm.sta.exception.operation.VectorSizeExeption;
import by.vsu.mf.amm.sta.math.object.Vector;
import by.vsu.mf.amm.sta.method.systemOfLinearEquationsProblemMethods.TridiagonalMatrixAlgorithm;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblem;

public class CreateEmptyResultVectorOperation extends Operation<SystemOfLinearEquationsProblem, TridiagonalMatrixAlgorithm> {

    private int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void execute() throws OperationException {

        TridiagonalMatrixAlgorithm method = getMethod();
        SystemOfLinearEquationsProblem problem = method.getProblem();

        if (problem.getResult() == null) {
            if (method.isDiagonallyDominantMatrix()) {
                if (quantity == problem.getMatrix().rowsQuantity()) {
                    problem.setResult(new Vector(quantity));

                    method.setMethodCoefficientsA(new Vector(quantity - 1));
                    method.setMethodCoefficientsB(new Vector(quantity - 1));

                    method.setCurrentCoefficientA(0);
                    method.setCurrentCoefficientB(0);
                    method.setCurrentUnknownX(quantity - 1);
                } else {
                    throw new VectorSizeExeption(this, quantity, problem.getMatrix().rowsQuantity());
                }
            } else {
                throw new VectorSizeExeption(this, quantity, 0);
            }
        } else {
            throw new IncorrectOperationException(this);
        }

    }


}

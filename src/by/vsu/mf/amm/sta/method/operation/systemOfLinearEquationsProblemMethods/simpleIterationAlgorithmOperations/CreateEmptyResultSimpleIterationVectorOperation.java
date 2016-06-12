package by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.simpleIterationAlgorithmOperations;

import by.vsu.mf.amm.sta.exception.operation.IncorrectOperationException;
import by.vsu.mf.amm.sta.exception.operation.OperationException;
import by.vsu.mf.amm.sta.exception.operation.VectorSizeExeption;
import by.vsu.mf.amm.sta.math.object.Vector;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.method.systemOfLinearEquationsProblemMethods.simpleIteration.SimpleIteration;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblemSimpleIterationMethod;

public class CreateEmptyResultSimpleIterationVectorOperation
        extends Operation<SystemOfLinearEquationsProblemSimpleIterationMethod, SimpleIteration> {

    private int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void execute() throws OperationException {
    	SimpleIteration method = getMethod();
    	SystemOfLinearEquationsProblemSimpleIterationMethod problem = method.getProblem();

        if (problem.getResult() == null) {
            if (method.isDiagonallyDominantMatrix()) {
                if (quantity == problem.getMatrix().rowsQuantity()) {
                    problem.setResult(new Vector(quantity));

                    method.setQuantity(0);
                    method.setAccuracy(0);

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
